/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.lib.config;

import cpw.mods.fml.relauncher.FMLInjectionData;
import me.modmuss50.mods.core.Core;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class ConfigHandler {

	public static void loadConfig(boolean reload) {
		File mcDir = (File) FMLInjectionData.data()[6];
		File configFile = new File(mcDir, "/config/modmussCore.json");
		if (!configFile.exists() || reload) {
			JSONObject obj = new JSONObject();
			obj.put("configVersion", Core.VERSION);
			obj.put("showLoadedMessage", "true");
			obj.put("mainMenuTweaks", "true");
			try {
				FileWriter file = new FileWriter(configFile);
				file.write(obj.toJSONString());
				file.flush();
				file.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (!getString("configVersion").equalsIgnoreCase(Core.VERSION)) {
			updateConfig();
		}
	}

	public static String getString(String value) {
		File mcDir = (File) FMLInjectionData.data()[6];
		File configFile = new File(mcDir, "/config/modmussCore.json");
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(configFile));
			JSONObject jsonObject = (JSONObject) obj;
			String name = (String) jsonObject.get(value);
			return name;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Boolean getBoolean(String value) {
		File mcDir = (File) FMLInjectionData.data()[6];
		File configFile = new File(mcDir, "/config/modmussCore.json");
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(configFile));
			JSONObject jsonObject = (JSONObject) obj;
			String name = (String) jsonObject.get(value);
			return name.equalsIgnoreCase("true");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int getInt(String value) {
		File mcDir = (File) FMLInjectionData.data()[6];
		File configFile = new File(mcDir, "/config/modmussCore.json");
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(configFile));
			JSONObject jsonObject = (JSONObject) obj;
			String name = (String) jsonObject.get(value);
			return Integer.parseInt(name);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static void updateConfig() {
		File mcDir = (File) FMLInjectionData.data()[6];
		File configFile = new File(mcDir, "/config/modmussCore.json");
		JSONObject obj = new JSONObject();
		obj.put("configVersion", Core.VERSION);
		if (getString("showLoadedMessage") == null) {
			obj.put("showLoadedMessage", "true");
		} else {
			obj.put("showLoadedMessage", getString("showLoadedMessage"));
		}

		if (getString("mainMenuTweaks") == null) {
			obj.put("mainMenuTweaks", "true");
		} else {
			obj.put("mainMenuTweaks", getString("mainMenuTweaks"));
		}

		try {
			FileWriter file = new FileWriter(configFile);
			file.write(obj.toJSONString());
			file.flush();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
