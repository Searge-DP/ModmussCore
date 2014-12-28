package me.modmuss50.mods.mml;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.IFMLCallHook;
import me.modmuss50.mods.core.Transformer;
import org.apache.logging.log4j.Level;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;

import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class JarPatcher implements IFMLCallHook {

    public File coremodLocation;

    @Override
    public void injectData(Map<String, Object> data) {
        if (data.containsKey("coremodLocation")) {
            coremodLocation = (File) data.get("coremodLocation");
        }
    }

    @Override
    public Void call() throws Exception {
        log("ModmussCore loading...");
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
        jarDir = new File(root.getCanonicalPath() + sep + "patches" + sep);
        jarDir.mkdirs();

        log("Patches directory: " + jarDir);

        File[] files = jarDir.listFiles();
        Arrays.sort(files);

        for (File file : files) {
            log("Patch found: " + file);
            try {
                loadJarmod(file);
            } catch (Exception ex) {
                FMLLog.log(Level.FATAL, "[ModmussCore] Failed to load Patch: "+ file + ": " + ex.getLocalizedMessage());
                ex.printStackTrace();
            }
        }

        log("Preloaded "+ Transformer.size()+" classes");
        return null;
    }

    private void log(String msg) {
        FMLLog.log(Level.INFO, "[ModmussCore] " + msg); // TODO
    }

    private void loadJarmod(File file) throws ZipException, IOException {
        ZipFile zipFile = new ZipFile(file);

        for (Enumeration<? extends ZipEntry> entr = zipFile.entries(); entr.hasMoreElements();) {
            ZipEntry entry = entr.nextElement();
            String className = entry.getName().replace(".class", "").replace('/', '.');

            byte[] bytes = new byte[(int) entry.getSize()];
            DataInputStream dataInputStream = new DataInputStream(zipFile.getInputStream(entry));
            dataInputStream.readFully(bytes);

            Transformer.put(className, bytes, file.getName());
        }
    }
}
