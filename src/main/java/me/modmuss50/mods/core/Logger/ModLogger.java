/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.core.Logger;

import cpw.mods.fml.common.FMLCommonHandler;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ModLogger {

	public String modName;

	public String prefix = "[" + getTime() + "] [" + getSide() + "] [" + getModName() + "] ";


	public ModLogger(String modName) {
		this.modName = modName;
	}

	public static ModLogger getLogger(String modName) {
		return new ModLogger(modName);
	}

	public void log(String message) {
		System.out.println(prefix + message);
	}

	public String getSide() {
		if (FMLCommonHandler.instance().getSide().isClient()) {
			return "client";
		}

		if (FMLCommonHandler.instance().getSide().isServer()) {
			return "Server";
		}

		return "ERROR";
	}

	public String getTime() {
		return new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
	}


	public String getModName() {
		return modName;
	}

}
