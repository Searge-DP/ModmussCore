package modmuss50.mods.core;


import cpw.mods.fml.relauncher.FMLInjectionData;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import modmuss50.mods.mml.ModScanner;
import net.minecraft.launchwrapper.LaunchClassLoader;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Map;

@IFMLLoadingPlugin.MCVersion("1.7.10")
public class FmlLoadingCore implements IFMLLoadingPlugin {


	public static File mcDir;

	//This is the earliest place I can get code to run without jar patching.
	public FmlLoadingCore() {
		if(mcDir != null)
			return;

		mcDir = (File) FMLInjectionData.data()[6];

		//mml = Modmuss Mod Loader :)
		System.out.println("Starting to mml");
		//Look for jars in a folder and load them :)
		ModScanner.loadMods(new File(mcDir, ModScanner.modsFolderName));
	}

	@Override
	public String[] getASMTransformerClass() {
		return new String[]{"modmuss50.mods.core.Transformer"};
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
