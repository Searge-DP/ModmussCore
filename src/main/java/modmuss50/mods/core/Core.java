package modmuss50.mods.core;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import modmuss50.mods.core.Logger.ModLogger;
import net.minecraft.block.Block;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import modmuss50.mods.core.client.BaseModGui;
import modmuss50.mods.core.client.ClientInit;
import modmuss50.mods.core.client.SourceCoreSettings;
import modmuss50.mods.core.fluid.BlankFluid;
import modmuss50.mods.core.fluid.BlankFluidH;
import modmuss50.mods.core.fluid.BlockFluid;
import modmuss50.mods.core.fluid.BlockFluidH;
import modmuss50.mods.core.mod.ModRegistry;
import modmuss50.mods.lib.mod.ISourceMod;

@Mod(modid = Core.MODID, name = Core.NAME, version = Core.VERSION, guiFactory = "modmuss50.mods.core.client.config.ConfigGuiFactory")
public class Core implements ISourceMod {

    public static final String MODID = "modmusscore";
    public static final String NAME = "Modmuss Core";
    public static final String VERSION = "1.0";

    public ModLogger logger = ModLogger.getLogger(NAME);


    public static Fluid blankFluid;
    public static Block blockFluid;
    public static Fluid blankFluidH;
    public static Block blockFluidH;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModRegistry.registerMod(this);
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        blankFluid = new BlankFluid("modmusscore:fluid");
        FluidRegistry.registerFluid(blankFluid);
        FluidRegistry.registerFluid(new BlankFluid("BlankFluid"));
        blockFluid = new BlockFluid("modmusscore:fluid").setBlockName("sourcecore:BlankFluid");
        GameRegistry.registerBlock(blockFluid, "BlankFluid");


        blankFluidH = new BlankFluidH("modmusscore:fluidH");
        FluidRegistry.registerFluid(blankFluidH);
        FluidRegistry.registerFluid(new BlankFluidH("BlankFluidH"));
        blockFluidH = new BlockFluidH("modmusscore:fluidH").setBlockName("sourcecore:BlankFluidH");
        GameRegistry.registerBlock(blockFluidH, "BlankFluidH");


    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        blankFluid.setIcons(BlockFluid.StillIcon);
    }

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {

    }

    @Mod.EventHandler
    public void load(FMLInitializationEvent event) {
        if (FMLCommonHandler.instance().getSide().isClient())
            ClientInit.load();
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
        return "1.7.10";
    }
}
