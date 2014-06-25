package sourceteam.mods.core.mod;

import sourceteam.mods.lib.mod.ISourceMod;

import java.util.ArrayList;

public class ModRegistry {

	public static ArrayList<ISourceMod>	mods;
	public static boolean					hasInted	= false;

	public static void registerMod(ISourceMod classFile) {
		if (!hasInted) {
			mods = new ArrayList<ISourceMod>();
			hasInted = true;
		}
		mods.add(classFile);
	}


}
