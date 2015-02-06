package me.modmuss50.mods.mml;


import me.modmuss50.mods.core.Transformer;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.relauncher.IFMLCallHook;
import org.apache.logging.log4j.Level;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ClassReplacer implements IFMLCallHook {

	public File coremodLocation;

	@Override
	public void injectData(Map<String, Object> data) {
		if (data.containsKey("coremodLocation")) {
			coremodLocation = (File) data.get("coremodLocation");
		}
	}

	@Override
	public Void call() throws Exception {
		log("ModmussCore looking for patches to load...");
		File root, jarDir;
		if (coremodLocation != null) {
			root = coremodLocation.getParentFile();
			if (root != null) {
				root = root.getParentFile();
			}
		} else {
			root = new File(".").getCanonicalFile();
		}

		String sep = System.getProperty("file.separator");
		jarDir = new File(root.getCanonicalPath() + sep + "mods" + sep);
		jarDir.mkdirs();

		log("Patches directory: " + jarDir);

		File[] files = jarDir.listFiles();
		Arrays.sort(files);

		for (File file : files) {
			try {
				if (file.getName().endsWith(".zip") || file.getName().endsWith(".jar")) {
					loadJarmod(file);
				}
			} catch (Exception ex) {
				FMLLog.log(Level.FATAL, "[ModmussCore] Failed to load Patch: " + file + ": " + ex.getLocalizedMessage());
				ex.printStackTrace();
			}
		}

		log("Preloaded " + Transformer.size() + " classes");
		return null;
	}

	private void log(String msg) {
		FMLLog.log(Level.DEBUG, "[ModmussCore] " + msg);
	}

	private void loadJarmod(File file) throws IOException {
		ZipFile zipFile = new ZipFile(file);
		ZipEntry patchEntry = zipFile.getEntry("mmlPatch.info");
		ArrayList<String> filesToLoad = new ArrayList<String>();

		if (patchEntry != null) {
			log("Mod with patches found: " + file);
			InputStream input = zipFile.getInputStream(patchEntry);
			BufferedReader br = new BufferedReader(new InputStreamReader(input, "UTF-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				if(!line.startsWith("#"))
					filesToLoad.add(line);
			}
			br.close();
		}
		for (Enumeration<? extends ZipEntry> entr = zipFile.entries(); entr.hasMoreElements(); ) {
			ZipEntry entry = entr.nextElement();
			String className = entry.getName().replace(".class", "").replace('/', '.');
			for (String name : filesToLoad) {
				if (name.equals(className)) {
					log("Patching : " + className);
					byte[] bytes = new byte[(int) entry.getSize()];
					DataInputStream dataInputStream = new DataInputStream(zipFile.getInputStream(entry));
					dataInputStream.readFully(bytes);

					Transformer.put(className, bytes, file.getName());
					dataInputStream.close();
				}
			}

		}
		zipFile.close();
	}
}