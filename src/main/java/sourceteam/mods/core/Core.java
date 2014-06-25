package sourceteam.mods.core;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.common.MinecraftForge;
import sourceteam.mods.core.client.BaseModGui;
import sourceteam.mods.core.client.MainMenuRenderer;
import sourceteam.mods.core.client.SourceCoreSettings;
import sourceteam.mods.core.mod.ModRegistry;
import sourceteam.mods.lib.mod.ISourceMod;

@Mod(modid = Core.MODID, name = Core.NAME, version = Core.VERSION)
public class Core implements ISourceMod {

	public static final String	MODID	= "sourcecore";
	public static final String	NAME	= "Source Core";
	public static final String	VERSION	= "1.0";

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ModRegistry.registerMod(this);

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
            MinecraftForge.EVENT_BUS.register(new MainMenuRenderer());
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BaseModGui settingsScreen() {
		return new SourceCoreSettings();
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

    @Override
    public String recomenedMinecraftVeriosion() {
        return "1.7.2";
    }
}
