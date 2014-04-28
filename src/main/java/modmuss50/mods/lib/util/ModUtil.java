package modmuss50.mods.lib.util;

import cpw.mods.fml.common.Loader;

public class ModUtil {


    public boolean isModLoaded(String modid)
    {
      return Loader.isModLoaded(modid);
    }
}
