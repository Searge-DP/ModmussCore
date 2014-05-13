package modmuss50.mods.core.mod;

import modmuss50.mods.lib.mod.IModmussMod;

import java.util.ArrayList;

public class ModRegistry {

    public static ArrayList<IModmussMod> mods;
    public static boolean hasInted = false;


    public static void registerMod(IModmussMod classFile)
    {
        if (!hasInted) {
            mods = new ArrayList<IModmussMod>();
            hasInted = true;
        }
      mods.add(classFile);
    }
}
