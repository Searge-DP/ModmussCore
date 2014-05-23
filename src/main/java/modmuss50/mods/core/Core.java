package modmuss50.mods.core;

import modmuss50.mods.core.client.BaseModGui;
import modmuss50.mods.core.client.GuiTicker;
import modmuss50.mods.core.client.Modmuss50CoreSettings;
import modmuss50.mods.core.mod.ForgeIMod;
import modmuss50.mods.core.mod.ModRegistry;
import modmuss50.mods.lib.mod.IModmussMod;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = Core.MODID, name = Core.NAME, version = Core.VERSION, useMetadata = false)
public class Core implements IModmussMod {


    public static final String MODID = "modmuss50core";
    public static final String NAME = "Modmuss50 Core";
    public static final String VERSION = "1.0";


	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
        ModRegistry.registerMod(this);
        ModRegistry.registerMod(new ForgeIMod());

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

    @SideOnly(Side.CLIENT)
	@Override
	public BaseModGui settingsScreen() {
		return new Modmuss50CoreSettings();
	}

    @Override
    public String modId() {
        return MODID;
    }

    @Override
    public String modName() {
        return NAME;
    }

    @Override
    public String modVersion() {
        return VERSION;
    }
}
