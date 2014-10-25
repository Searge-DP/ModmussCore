package modmuss50.mods.mml;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ModScanner {
	//This is the name of the folder that the mods should be loaded from
	public static final String modsFolderName = "mml";

	//This is the lists of mods that are loaded
	public static ArrayList<IModmussMod> modmussMods = new ArrayList<IModmussMod>();

	//loads mods from the @modsFolderName :)
	public static void loadMods(File modsDir){
		if(modsDir.exists()){
			for(File mod : modsDir.listFiles()){
				scanMod(mod);
			}
		}

	}

	private static void scanMod(File mod){
		if(!mod.getName().endsWith(".jar") && !mod.getName().endsWith(".zip")){
			return;
		}
		try{
			JarFile jar = new JarFile(mod);
			for(JarEntry entry : Collections.list(jar.entries())){
				String fileName = entry.getName();
				if(!fileName.endsWith(".class"))
					continue;
				String classname = fileName.replace('/', '.').substring(0, fileName.length() - 6);
				Class<?> clazz = Class.forName(classname);
				//This checks to see if it is imported(needs testing :( )
				if(!clazz.isAnnotationPresent(ModmussMod.class))
					continue;
				//Oh look we found a mml mod!
				ModmussMod modmussMod = clazz.getAnnotation(ModmussMod.class);

			}

		} catch (Exception e){

		}
	}
}
