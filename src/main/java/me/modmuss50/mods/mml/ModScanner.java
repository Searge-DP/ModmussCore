/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.mml;

import cpw.mods.fml.relauncher.FMLInjectionData;
import me.modmuss50.mods.core.FmlLoadingCore;
import net.minecraft.launchwrapper.LaunchClassLoader;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ModScanner {
	//This is the name of the folder that the mods should be loaded from
	public static final String modsFolderName = "mml";
	//This is the lists of mods that are loaded
	public static ArrayList<Class<?>> mods = new ArrayList<Class<?>>();
	private static File patchesDir;

	//loads mods from the @modsFolderName :)
	public static void loadMods() {

		File mcDir = (File) FMLInjectionData.data()[6];
		File file = new File(mcDir, modsFolderName);

		if (!file.exists()) {
			file.mkdir();
		}
		loadjars();

		if (file.exists()) {
			for (File mod : file.listFiles()) {
				scanMod(mod);
			}
		}

		try {
			startMods();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//This here loads jar files from the patches folder. This was a test and wont be used
	public static void loadjars() {
		File mcDir = (File) FMLInjectionData.data()[6];
		patchesDir = new File(mcDir, modsFolderName);

		if (!patchesDir.exists())
			patchesDir.mkdirs();

		for (int i = 0; i < patchesDir.listFiles().length; i++) {
			if (patchesDir.listFiles()[i].getName().endsWith(".jar")) {
				try {
					((LaunchClassLoader) FmlLoadingCore.class.getClassLoader()).addURL(new File(patchesDir, patchesDir.listFiles()[i].getName()).toURI().toURL());
				} catch (MalformedURLException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	private static void scanMod(File mod) {
		if (!mod.getName().endsWith(".jar") && !mod.getName().endsWith(".zip")) {
			return;
		}
		try {
			JarFile jar = new JarFile(mod);
			for (JarEntry entry : Collections.list(jar.entries())) {
				String fileName = entry.getName();
				if (!fileName.endsWith(".class"))
					continue;
				String classname = fileName.replace('/', '.').substring(0, fileName.length() - 6);

				Class clazz = Class.forName(classname);

				//This checks to see if it is imported(needs testing :( )
				if (clazz.isInstance(ModmussMod.class))
					continue;
				mods.add(clazz);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void enableMods() throws Exception {
		for (Class<?> mod : mods) {
			ModmussMod modmussMod = (ModmussMod) mod.newInstance();
			modmussMod.enable();
		}
	}

	public static void disableMods() throws Exception {
		for (Class<?> mod : mods) {
			ModmussMod modmussMod = (ModmussMod) mod.newInstance();
			modmussMod.disable();
		}
		mods.clear();
	}

	public static void startMods() throws Exception {
		for (Class<?> mod : mods) {
			Class cls = Class.forName(mod.getName());
			Object clsInstance = (Object) cls.newInstance();
			ModmussMod modmussMod = (ModmussMod) clsInstance;
			modmussMod.load();
		}
	}
}
