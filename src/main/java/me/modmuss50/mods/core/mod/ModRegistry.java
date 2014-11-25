/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.core.mod;

import me.modmuss50.mods.lib.mod.ISourceMod;

import java.util.ArrayList;

public class ModRegistry {

	public static ArrayList<ISourceMod> mods;
	public static boolean hasInted = false;

	public static void registerMod(ISourceMod classFile) {
		if (!hasInted) {
			mods = new ArrayList<ISourceMod>();
			hasInted = true;
		}
		mods.add(classFile);
	}

	public static void removeMod(String modid) {
		if (!hasInted) {
			mods = new ArrayList<ISourceMod>();
			hasInted = true;
		}
		for (int i = 0; i < mods.size(); i++) {
			if (mods.get(i).modId().equals(modid)) {
				mods.remove(i);
			}
		}
	}


	//If you use this MAKE SURE you have a null check!
	public ISourceMod getMod(String modid) {
		if (!hasInted) {
			mods = new ArrayList<ISourceMod>();
			hasInted = true;
		}
		for (int i = 0; i < mods.size(); i++) {
			if (mods.get(i).modId().equals(modid)) {
				return mods.get(i);
			}
		}
		return null;
	}

}
