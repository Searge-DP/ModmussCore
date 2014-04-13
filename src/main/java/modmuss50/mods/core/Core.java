package modmuss50.mods.core;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import modmuss50.mods.core.WorldProtection.WorldProtectionEventHandler;
import modmuss50.mods.core.client.GuiTicker;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = "Mod50-Core", name = "Modmuss50 Core", version = "000", useMetadata = false)
public class Core {

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new WorldProtectionEventHandler());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        FMLCommonHandler.instance().bus().register(new WorldProtectionEventHandler());

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
     //   System.out.println("##################################ADDED TICK###########################################################");
    }




}
