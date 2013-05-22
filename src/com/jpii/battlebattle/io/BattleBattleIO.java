/*
 * Copyright (C) 2012 JPII and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.jpii.battlebattle.io;

import java.io.File;

import com.jpii.battlebattle.BattleBattle;
import com.jpii.battlebattle.util.FileUtils;

public class BattleBattleIO {
	
	private static SettingsIO settings;
	private static boolean inited = false;
	
	/**
	 * Starts the BattleBattleIO service.
	 */
	public static void run() {
		inited = true;
		if (isFirstRun()) {
			BattleBattle.getDebugger().printInfo("Writing default config file");
			
			settings = new SettingsIO(getSettingsPath());
			boolean res = true;
			
			// TODO: Create defaults here
			
			if (!res)
				BattleBattle.getDebugger().printError("failed to write initial attributes");
		}
		else {
			BattleBattle.getDebugger().printInfo("Loading config file");	
			try {
				settings = new SettingsIO(getSettingsPath());
			} catch (Exception e) { 
				BattleBattle.getDebugger().printError("Error while reading config file");
			}
		}
		settings.refresh();
	}
	
	/**
	 * Gets a specific attribute from the BattleBattle settings file.
	 * @param name The name of the attribute to get. Should only contain alpha-numeric characters.
	 * @return The value of the attribute (if any).
	 */
	public static String getAttribute(String name) {
		if (!inited)
			run();
		return settings.readAttribute(name);
	}
	
	/**
	 * Gets a specific attribute from the BattleBattle settings file.
	 * @param a The name and value store of the attribute to get. Should only contain alpha-numeric characters.
	 * @return The value of the attribute (if any).
	 */
	public static String getAttribute(SettingsAttribute a) {
		if (!inited)
			run();
		return settings.readAttribute(a);
	}
	
	/**
	 * Saves an attribute to the BattleBattle settings file.
	 * @param attribute The attribute to save to the file.
	 */
	public static void saveAttribute(SettingsAttribute attribute) {
		if (!inited)
			run();
		settings.setAttribute(attribute);
	}
	
	/**
	 * Saves an attribute to the BattleBattle settings file.
	 * @param name The name to save to the file.
	 * @param value The value of the attribute.
	 */
	public static void saveAttribute(String name, String value) {
		if (!inited)
			run();
		settings.setAttribute(new SettingsAttribute(name,value));
	}
	
	/**
	 * Determines if this is the first time the game has run.
	 * @return
	 */
	public static boolean isFirstRun() {
		return !new File(getSettingsPath()).exists();
	}
	
	/**
	 * Gets the path of the settings file.
	 * @return
	 */
	public static String getSettingsPath() {
		return (FileUtils.getSavingDirectory().getAbsolutePath()+"\\settings.ini");
	}
}