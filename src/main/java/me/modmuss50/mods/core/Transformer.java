/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.core;


import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.fml.common.FMLLog;
import org.apache.logging.log4j.Level;

import java.util.HashMap;
import java.util.Map;


public class Transformer implements IClassTransformer {

	private static Map<String, byte[]> classBytes = new HashMap<String, byte[]>();
	private static Map<String, String> classSources = new HashMap<String, String>();

	/**
	 * Add a new jarmod class, overwriting any previous class of the same name
	 *
	 * @param className Name of class (Java source name, .-separated)
	 * @param bytes     Raw class bytes
	 * @param source    Informative description of where this class came from (filename)
	 */
	public static void put(String className, byte[] bytes, String source) {
		classBytes.put(className, bytes);
		classSources.put(className, source);
	}

	public static int size() {
		return classBytes.size();
	}

	public byte[] transform(String name, String transformedName, byte[] bytes) {

		if (classBytes.containsKey(name)) {
			FMLLog.log(Level.INFO, "[ModmussCore] Patching " + name + " from " + classSources.get(name));

			return classBytes.get(name);
		}

		return bytes;
	}

}
