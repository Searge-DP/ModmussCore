/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.core;


import cpw.mods.fml.relauncher.FMLInjectionData;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import me.modmuss50.mods.mml.ModScanner;

import java.io.File;
import java.util.Map;

@IFMLLoadingPlugin.MCVersion("1.7.10")
public class FmlLoadingCore implements IFMLLoadingPlugin {


	public static File mcDir;

	//This is the earliest place I can get code to run without jar patching.
	public FmlLoadingCore() {
		if (mcDir != null)
			return;

		mcDir = (File) FMLInjectionData.data()[6];

		//mml = Modmuss Mod Loader :)
		System.out.println("Starting to mml mods");
		//Look for jars in a folder and load them :)
		ModScanner.loadMods();
	}

	@Override
	public String[] getASMTransformerClass() {
		return new String[]{"Transformer"};
	}

	@Override
	public String getModContainerClass() {
		return null;
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {

	}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}


}
