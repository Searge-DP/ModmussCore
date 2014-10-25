package modmuss50.mods.mml;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.FMLInjectionData;
import modmuss50.mods.core.FmlLoadingCore;
import net.minecraft.launchwrapper.LaunchClassLoader;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ModScanner {
	//This is the name of the folder that the mods should be loaded from
	public static final String modsFolderName = "mml";
    private static File patchesDir;

	//This is the lists of mods that are loaded
    public static ArrayList<Class<?>> mods = new ArrayList<Class<?>>();
    public static List<Class<?>> modListeners = Lists.newArrayList();

	//loads mods from the @modsFolderName :)
	public static void loadMods(File modsDir){
        File mcDir = (File) FMLInjectionData.data()[6];
        File file = new File(mcDir, modsFolderName);

        if(!file.exists()){
            file.mkdir();
        }

        loadjars();
		if(modsDir.exists()){
			for(File mod : modsDir.listFiles()){
				scanMod(mod);
			}
		}


        enableMods();
	}

    //This here loads jar files from the patches folder. This was a test and wont be used
    public static void loadjars() {
        File mcDir = (File) FMLInjectionData.data()[6];
        patchesDir = new File(mcDir, modsFolderName);

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

                mods.add(clazz);
			}
		} catch (Exception e){

		}
	}

    public static void enableMods() throws IllegalAccessException, InstantiationException {
        for (int i = 0; i < mods.size(); i++) {

        }
        for (Class<?> clazz : modListeners)
        {
            try
            {
                IModListener mod = (IModListener) clazz.newInstance();

                mod.handle(mods.get(mod.getModID()));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

}
