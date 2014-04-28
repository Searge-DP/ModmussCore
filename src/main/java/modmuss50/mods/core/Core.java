package modmuss50.mods.core;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import modmuss50.mods.core.WorldProtection.ItemBP;
import modmuss50.mods.core.WorldProtection.WorldProtectionEventHandler;
import modmuss50.mods.core.client.GuiTicker;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = "Mod50-Core", name = "Modmuss50 Core", version = "000", useMetadata = false)
public class Core {


    public static boolean WorldProctection = false;

    public static Item BlockPlacer;





    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
       if(WorldProctection)
       {
           MinecraftForge.EVENT_BUS.register(new WorldProtectionEventHandler());
           BlockPlacer = new ItemBP()
                   .setUnlocalizedName("Mod50-Core:BlockProtector")
                   .setTextureName("Mod50-Core:BlockProtector");
           GameRegistry.registerItem(BlockPlacer, "Mod50-Core:BlockProtector");
       }



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
     //   System.out.println("##################################ADDED TICK###########################################################");
    }




}
