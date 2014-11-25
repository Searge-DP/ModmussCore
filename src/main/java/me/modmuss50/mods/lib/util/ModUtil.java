/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.lib.util;

import cpw.mods.fml.common.Loader;

public class ModUtil {

	public boolean isModLoaded(String modid) {
		return Loader.isModLoaded(modid);
	}
}
