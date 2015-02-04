/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.mml;

import com.google.common.eventbus.EventBus;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.MetadataCollection;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.versioning.ArtifactVersion;
import cpw.mods.fml.common.versioning.VersionRange;
import cpw.mods.fml.relauncher.FMLInjectionData;
import me.modmuss50.mods.core.FmlLoadingCore;
import me.modmuss50.mods.core.client.BaseModGui;
import me.modmuss50.mods.core.mod.ModRegistry;
import me.modmuss50.mods.lib.ReflectUtilities;
import me.modmuss50.mods.lib.mod.IMod;
import net.minecraft.launchwrapper.LaunchClassLoader;

import java.io.File;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ModScanner {
	//This is the name of the folder that the mods should be loaded from
	public static final String modsFolderName = "mml";
	//This is the lists of mods that are loaded
	public static ArrayList<Class<?>> mods = new ArrayList<Class<?>>();
	public static ArrayList<String> modNames = new ArrayList<String>();
	public static ArrayList<ModmussMod> modObjects = new ArrayList<ModmussMod>();
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
				modObjects.add((ModmussMod)clazz.newInstance());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void enableMods() throws Exception {
		for (Class<?> mod : mods) {
			final ModmussMod modmussMod = (ModmussMod) mod.newInstance();

			Field field = ReflectUtilities.getField(Loader.instance(), "mods");
			field.setAccessible(true);
			List<ModContainer> mods = (List<ModContainer>) field.get(Loader.instance());
			List<ModContainer> newList = new ArrayList<ModContainer>();
			boolean shouldLoad = true;
			for(ModContainer fmlMod : mods){
				if(fmlMod.getModId().equals(modmussMod.modId())){
					shouldLoad = false;
				}
			}
			newList.addAll(mods);
			if(shouldLoad){
				//This is a fake Forge Mod
				newList.add(new ModContainer() {
					@Override
					public String getModId() {
						return modmussMod.modId();
					}

					@Override
					public String getName() {
						return modmussMod.modName();
					}

					@Override
					public String getVersion() {
						return modmussMod.modVersion();
					}

					@Override
					public File getSource() {
						return null;
					}

					@Override
					public ModMetadata getMetadata() {
						ModMetadata mmd = new ModMetadata();
						mmd.name = modmussMod.modName();
						mmd.modId = modmussMod.modName();
						mmd.version = modmussMod.modName();
						return mmd;
					}

					@Override
					public void bindMetadata(MetadataCollection mc) {

					}

					@Override
					public void setEnabledState(boolean enabled) {

					}

					@Override
					public Set<ArtifactVersion> getRequirements() {
						return null;
					}

					@Override
					public List<ArtifactVersion> getDependencies() {
						return null;
					}

					@Override
					public List<ArtifactVersion> getDependants() {
						return null;
					}

					@Override
					public String getSortingRules() {
						return null;
					}

					@Override
					public boolean registerBus(EventBus bus, LoadController controller) {
						return false;
					}

					@Override
					public boolean matches(Object mod) {
						return false;
					}

					@Override
					public Object getMod() {
						return null;
					}

					@Override
					public ArtifactVersion getProcessedVersion() {
						return null;
					}

					@Override
					public boolean isImmutable() {
						return false;
					}

					@Override
					public String getDisplayVersion() {
						return modmussMod.modVersion();
					}

					@Override
					public VersionRange acceptableMinecraftVersionRange() {
						return null;
					}

					@Override
					public Certificate getSigningCertificate() {
						return null;
					}

					@Override
					public Map<String, String> getCustomModProperties() {
						return null;
					}

					@Override
					public Class<?> getCustomResourcePackClass() {
						return null;
					}

					@Override
					public Map<String, String> getSharedModDescriptor() {
						return null;
					}

					@Override
					public Disableable canBeDisabled() {
						return null;
					}

					@Override
					public String getGuiClassName() {
						return null;
					}

					@Override
					public List<String> getOwnedPackages() {
						return null;
					}
				});//This is the end of the fake forge mod!
				field.set(Loader.instance(), newList);
			}

			//This makes it also an IMod
			ModRegistry.registerMod(new IMod() {
				@Override
				public BaseModGui settingsScreen() {
					return null;
				}

				@Override
				public String modId() {
					return modmussMod.modId();
				}

				@Override
				public String modName() {
					return modmussMod.modName();
				}

				@Override
				public String modVersion() {
					return modmussMod.modVersion();
				}

				@Override
				public String recomenedMinecraftVeriosion() {
					return modmussMod.recomenedMinecraftVeriosion();
				}
			});
			System.out.println("The mod " + modmussMod.modName() + " has been registed with forge and mmc!");
			try{
				modmussMod.enable();
			} catch (Exception e){
				throw new Exception("The mod " + modmussMod.modName() + " failed to enable");
			}
			modNames.add(modmussMod.modName());
		}
	}

	public static void disableMods() throws Exception {
		for (ModmussMod mod : modObjects) {
			mod.disable();
		}
		mods.clear();
	}

	public static void startMods() throws Exception {
		for (ModmussMod mod : modObjects) {
			if(mod == null){
				mod = mod.getClass().newInstance();
			}
			try{
				mod.load();
			} catch (Exception e){
				throw new Exception("The mod " + mod.modName() + " failed to load");
			}

		}
	}
}
