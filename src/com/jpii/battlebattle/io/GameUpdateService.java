package com.jpii.battlebattle.io;

import java.net.URL;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.jpii.battlebattle.BattleBattle;
import com.jpii.battlebattle.data.Constants;
import com.jpii.battlebattle.data.Game;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;

public class GameUpdateService {

	public GameUpdateService() {
		parseXml();
	}

	private void parseXml() {			
		DOMParser parser = new DOMParser();

		try {
			parser.parse(new InputSource(new URL(Constants.UPDATE_URL).openStream()));
			Document doc = parser.getDocument();

			NodeList nodeList = doc.getElementsByTagName("games");
			NodeList games = nodeList.item(0).getChildNodes();

			for(int i = 0; i < games.getLength(); i++) {	                
				Node n = games.item(i);
				
				String[] gameValues = new String[7];
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
					} catch (Exception e) { }
				}
				
				try {
					if(gameValues[6].equals("0")) {
						BattleBattle.getGameDatabase().addGame(new Game(gameValues));
					}
				} catch (Exception e) { }
			}

		} catch (Exception ex) {
			BattleBattle.getDebugger().printError("GameUpdateService encountered an error while downloading data");
			ex.printStackTrace();
		}
	}
}
