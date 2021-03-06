package com.jpii.battlebattle.io;

import java.io.File;
import java.net.URL;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.jpii.battlebattle.BattleBattle;
import com.jpii.battlebattle.data.Constants;
import com.jpii.battlebattle.data.Game;
import com.jpii.battlebattle.data.GameType;
import com.jpii.battlebattle.util.FileUtils;
import com.jpii.battlebattle.util.GameConfigUtils;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;

public class GameUpdateService {

	public GameUpdateService() {
		loadLocalGames();
		loadOnlineGames();
	}
	
	private void loadLocalGames() {
		for(File f : FileUtils.getSavingDirectory().listFiles()) {
			if(f.isDirectory()) {
				for(File gameFile : f.listFiles()) {
					if(gameFile.getName().equals("manifest.ini")) {
						String[] gameValues = new String[8];
						gameValues[0] = GameConfigUtils.getValue(gameFile, "game_name");
						gameValues[1] = GameConfigUtils.getValue(gameFile, "game_id");
						gameValues[2] = GameConfigUtils.getValue(gameFile, "version_code");
						gameValues[3] = GameConfigUtils.getValue(gameFile, "version_readable");
						gameValues[4] = GameConfigUtils.getValue(gameFile, "description");
						gameValues[5] = GameConfigUtils.getValue(gameFile, "download_url");
						gameValues[6] = "0";
						gameValues[7] = GameConfigUtils.getValue(gameFile, "hd5_hash");
						
						BattleBattle.getGameDatabase().addGame(new Game(gameValues, GameType.LOCAL));
					}
				}
			}
		}
	}

	private void loadOnlineGames() {			
		DOMParser parser = new DOMParser();

		try {
			parser.parse(new InputSource(new URL(Constants.UPDATE_URL).openStream()));
			Document doc = parser.getDocument();

			NodeList nodeList = doc.getElementsByTagName("games");
			NodeList games = nodeList.item(0).getChildNodes();

			for(int i = 0; i < games.getLength(); i++) {	                
				Node n = games.item(i);
				
				String[] gameValues = new String[8];
				for(int k = 0; k < n.getChildNodes().getLength(); k++) {
					Node node = n.getChildNodes().item(k);
					NamedNodeMap m = node.getAttributes();
					Node actualNode = node.getFirstChild();
					
					try {
						if(m.getNamedItem("name").getTextContent().equals("game_name")) {
							gameValues[0] = actualNode.getNodeValue();
						}
						
						if(m.getNamedItem("name").getTextContent().equals("game_id")) {
							gameValues[1] = actualNode.getNodeValue();
						}
						
						if(m.getNamedItem("name").getTextContent().equals("version_code")) {
							gameValues[2] = actualNode.getNodeValue();
						}
						
						if(m.getNamedItem("name").getTextContent().equals("version_readable")) {
							gameValues[3] = actualNode.getNodeValue();
						}
						
						if(m.getNamedItem("name").getTextContent().equals("description")) {
							gameValues[4] = actualNode.getNodeValue();
						}
						
						if(m.getNamedItem("name").getTextContent().equals("download_url")) {
							gameValues[5] = actualNode.getNodeValue();
						}
						
						if(m.getNamedItem("name").getTextContent().equals("hidden")) {
							gameValues[6] = actualNode.getNodeValue();
						}
						
						if(m.getNamedItem("name").getTextContent().equals("md5_hash")) {
							gameValues[7] = actualNode.getNodeValue();
						}
					} catch (Exception e) { }
				}
				
				try {
					if(gameValues[6].equals("0")) {
						BattleBattle.getGameDatabase().addGame(new Game(gameValues, GameType.ONLINE));
					}
				} catch (Exception e) { }
			}

		} catch (Exception ex) {
			BattleBattle.getDebugger().printError("GameUpdateService encountered an error while downloading data");
			ex.printStackTrace();
		}
		
		BattleBattle.getDebugger().printInfo(BattleBattle.getGameDatabase().getGames().size() + " game(s) loaded");
	}
}
