package com.jpii.battlebattle.data;

import java.util.ArrayList;

public class GameDatabase {
	
	private ArrayList<Game> games;
	
	public GameDatabase() {
		games = new ArrayList<Game>();
	}
	
	public void addGame(Game game) {
		games.add(game);
	}
	
	public ArrayList<Game> getGames() {
		return games;
	}
	
	public Game getGame(int index) {
		return games.get(index);
	}
}
