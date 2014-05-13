package modmuss50.mods.core;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import modmuss50.mods.core.client.BaseModGui;
import modmuss50.mods.core.client.GuiTicker;
import modmuss50.mods.core.client.Modmuss50CoreSettings;
import modmuss50.mods.core.mod.ModRegistry;
import modmuss50.mods.lib.mod.IModmussMod;

@Mod(modid = "Mod50-Core", name = "Modmuss50 Core", version = "000", useMetadata = false)
public class Core implements IModmussMod {

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
        ModRegistry.registerMod(this);
        System.out.println(this.getClass().getInterfaces());
    }

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {

	}

	@Mod.EventHandler
	public static void postInit(FMLPostInitializationEvent event) {

	}

	@Mod.EventHandler
	public void onServerStarting(FMLServerStartingEvent event) {

	}

	@Mod.EventHandler
	public void load(FMLInitializationEvent event) {
		if (FMLCommonHandler.instance().getSide().isClient()) {
			FMLCommonHandler.instance().bus().register(new GuiTicker());
		}
	}

	@Override
	public BaseModGui settingsScreen() {
		return new Modmuss50CoreSettings();
	}

    @Override
    public String modId() {
        return "Mod50-Core";

    }

    @Override
    public String modName() {
        return "Modmuss50 Core";
    }


    @Override
    public String modVersion() {
        return "000";
    }
}


