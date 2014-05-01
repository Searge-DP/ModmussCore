package modmuss50.mods.core;

import modmuss50.mods.core.client.GuiTicker;
import modmuss50.mods.lib.mod.IModmussMod;
import net.minecraft.client.gui.GuiScreen;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid = "Mod50-Core", name = "Modmuss50 Core", version = "000", useMetadata = false)
public class Core implements IModmussMod {

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {

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
	public GuiScreen settingsScreen() {
		return null;
	}
}
