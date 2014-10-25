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

	private File patchesDir;
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

	//This here loads jar files from the patches folder. This was a test and wont be used
	public void loadPaches() {

		File mcDir = (File) FMLInjectionData.data()[6];

		patchesDir = new File(mcDir, "patches");

		if (!patchesDir.exists())
			patchesDir.mkdirs();


		for (int i = 0; i < patchesDir.listFiles().length; i++) {

			System.out.println(patchesDir.listFiles()[i].getName());

			if (patchesDir.listFiles()[i].getName().endsWith(".jar")) {
				try {
					((LaunchClassLoader) FmlLoadingCore.class.getClassLoader()).addURL(new File(patchesDir, patchesDir.listFiles()[i].getName()).toURI().toURL());
					System.out.println("added" + patchesDir.listFiles()[i].getName() + " to class path");

				} catch (MalformedURLException e) {
					throw new RuntimeException(e);
				}
			}

		}


	}

}
