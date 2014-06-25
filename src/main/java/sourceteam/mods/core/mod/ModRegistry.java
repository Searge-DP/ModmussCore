package sourceteam.mods.core.mod;

import java.util.ArrayList;

import sourceteam.mods.lib.mod.ISourceMod;

public class ModRegistry {

	public static ArrayList<ISourceMod>	mods;
	public static boolean				hasInted	= false;

	public static void registerMod(ISourceMod classFile) {
		if (!hasInted) {
			mods = new ArrayList<ISourceMod>();
			hasInted = true;
		}
		mods.add(classFile);
	}

}
