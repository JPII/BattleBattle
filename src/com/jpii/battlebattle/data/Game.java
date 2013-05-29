package com.jpii.battlebattle.data;

import java.io.File;

import com.jpii.battlebattle.util.FileUtils;

public class Game {
	
	private String name, id, versionCode, versionReadable, description, downloadUrl;
	private GameType gameType;
	
	public Game(String name, String id, String versionCode, String versionReadable, String description, String downloadUrl) {
		this.name = name;
		this.id = id;
		this.versionCode = versionCode;
		this.versionReadable = versionReadable;
		this.description = description;
		this.downloadUrl = downloadUrl;
		gameType = GameType.OTHER;
		
		createDirectory();
	}
	
	public Game(String name, String id, String versionCode, String versionReadable, String description, String downloadUrl, GameType gameType) {
		this.name = name;
		this.id = id;
		this.versionCode = versionCode;
		this.versionReadable = versionReadable;
		this.description = description;
		this.downloadUrl = downloadUrl;
		this.gameType = gameType;
		
		createDirectory();
	}
	
	public Game(String[] values) {
		this.name = values[0];
		this.id = values[1];
		this.versionCode = values[2];
		this.versionReadable = values[3];
		this.description = values[4];
		this.downloadUrl = values[5];
		gameType = GameType.OTHER;
		
		createDirectory();
	}
	
	public Game(String[] values, GameType gameType) {
		this.name = values[0];
		this.id = values[1];
		this.versionCode = values[2];
		this.versionReadable = values[3];
		this.description = values[4];
		this.downloadUrl = values[5];
		this.gameType = gameType;
		
		createDirectory();
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
	public boolean verify() {
		// TODO
		return true;
	}
	
	/**
	 * Attempts to launch the game.
	 */
	public void play() {
		// TODO
	}
	
	/**
	 * Returns name of game.
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns id of game.
	 * @return
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Returns version code of game.
	 * @return
	 */
	public String getVersionCode() {
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
	 * Returns description for game.
	 * @return
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Returns download URL for latest version.
	 * @return
	 */
	public String getDownloadUrl() {
		return downloadUrl;
	}
	
	/**
	 * Sets GameType.
	 * @param gameType
	 */
	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}
	
	/**
	 * Returns current GameType.
	 * @return
	 */
	public GameType getGameType() {
		return gameType;
	}
	
	/**
	 * Returns directory name. The directory name
	 * is only the name of the folder in which the
	 * game is stored, not the entire path.
	 * @return
	 */
	public String getDirectoryName() {
		return "." + name.toLowerCase().replace(" ", "");
	}
	
	/**
	 * Returns directory in which the game is stored.
	 * @return
	 */
	public File getDirectory() {
		return new File(FileUtils.getSavingDirectory() + "/" + getDirectoryName());
	}
	
	/**
	 * Creates directory for game install.
	 */
	private void createDirectory() {
		getDirectory().mkdir();
		
		// TODO: Create manifest file to save version number, etc.
	}
	
	/**
	 * Clears directory for game install.
	 */
	private void clearDirectory() {
		for (File f: getDirectory().listFiles()) f.delete();
	}
	
	/**
	 * Clears directory and deletes the directory.
	 */
	private void deleteDirectory() {
		clearDirectory();
		getDirectory().delete();
	}
	
	/**
	 * Attempts to backup game install.
	 */
	private void backup() {
		// TODO
	}
	
	public String toString() {
		return "[" + name + ", " + id + ", " + versionCode + ", " + versionReadable + ", " + description + ", " + downloadUrl + "," + gameType + "]";
	}
}