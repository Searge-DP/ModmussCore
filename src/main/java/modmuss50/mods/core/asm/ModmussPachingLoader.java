package modmuss50.mods.core.asm;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

import java.io.File;
import java.util.Map;

public class ModmussPachingLoader implements IFMLLoadingPlugin
{
    public static File location;

    @Deprecated
    public String[] getLibraryRequestClass()
    {
        return null;
    }

    public String[] getASMTransformerClass()
    {
        return new String[] { ModmussTransformer.class.getName() };
    }

    public String getModContainerClass()
    {
        return ModmussDummyContainer.class.getName();
    }

    public String getSetupClass()
    {
        return null;
    }

    public void injectData(Map<String, Object> data)
    {
        location = (File)data.get("coremodLocation");
    }

    @Override
    public String getAccessTransformerClass() {
        return "modmuss50.mods.core.asm.ModmussTransformer";
    }
}
