package com.jpii.battlebattle.data;

public enum GameType {
	
	/**
	 * Game only found locally.
	 */
	LOCAL,
	
	/**
	 * Game either available to install from the server
	 * or a game which has been installed and has been
	 * verified to be listed on the server as well.
	 */
	ONLINE,
	
	/**
	 * Unknown game type.
	 */
	OTHER
}
