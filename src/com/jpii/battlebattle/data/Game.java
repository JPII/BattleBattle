package com.jpii.battlebattle.data;

public class Game {
	
	private String name, versionReadable, updateText, downloadUrl;
	private int versionCode;
	
	public Game(String name, int versionCode, String versionReadable, String updateText, String downloadUrl) {
		this.name = name;
		this.versionCode = versionCode;
		this.versionReadable = versionReadable;
		this.updateText = updateText;
		this.downloadUrl = downloadUrl;
	}
	
	/**
	 * Returns if game is installed. Does not attempt
	 * to install the game if it is not installed.
	 * @return
	 */
	public boolean isInstalled() {
		// TODO
		return false;
	}
	
	/**
	 * Returns if game is out of date. Does not attempt
	 * to update the game is the game is out of date.
	 * @return
	 */
	public boolean needsUpdate() {
		// TODO
		return false;
	}
	
	/**
	 * Attempts to install the game for the first time.
	 */
	public void install() {
		// TODO
	}
	
	/**
	 * Attempts to update the game to the latest version.
	 */
	public void update() {
		// TODO
	}
	
	/**
	 * Verifies game install or update. Returns if game has been
	 * validated. If the game is invalid, this does not attempt to
	 * resolve any issues.
	 * @return
	 */
	public boolean verifyGame() {
		// TODO
		return true;
	}
	
	/**
	 * Returns name of game.
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns version code of game.
	 * @return
	 */
	public int getVersionCode() {
		return versionCode;
	}
	
	/**
	 * Returns readable version of game.
	 * @return
	 */
	public String getVersionReadable() {
		return versionReadable;
	}
	
	/**
	 * Returns update text for game.
	 * @return
	 */
	public String getUpdateText() {
		return updateText;
	}
	
	/**
	 * Returns download URL for latest version.
	 * @return
	 */
	public String getDownloadUrl() {
		return downloadUrl;
	}
}