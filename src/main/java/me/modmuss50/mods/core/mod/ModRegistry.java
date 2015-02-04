/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.core.mod;

import me.modmuss50.mods.lib.mod.IMod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ModRegistry {

	public static ArrayList<IMod> mods;
	public static boolean hasInted = false;

	public static void registerMod(IMod classFile) {
		if (!hasInted) {
			mods = new ArrayList<IMod>();
			hasInted = true;
		}
		mods.add(classFile);

		//This sorts all the mods
		Collections.sort(mods, new Comparator<IMod>() {
			public int compare(IMod mod1, IMod mod2) {
				return mod1.modName().compareTo(mod2.modName());
			}
		});
	}


	//Not sure why you want to do this?
	public static boolean removeMod(String modid) {
		if (!hasInted) {
			mods = new ArrayList<IMod>();
			hasInted = true;
		}
		for (int i = 0; i < mods.size(); i++) {
			if (mods.get(i).modId().equals(modid)) {
				mods.remove(i);
				return true;
			}
		}
		return false;
	}


	//If you use this MAKE SURE you have a null check!
	public IMod getMod(String modid) {
		if (!hasInted) {
			mods = new ArrayList<IMod>();
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
