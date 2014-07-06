package sourceteam.mods.core;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import sourceteam.mods.core.Logger.SourceLogger;
import sourceteam.mods.core.client.BaseModGui;
import sourceteam.mods.core.client.ClientInit;
import sourceteam.mods.core.client.SourceCoreSettings;
import sourceteam.mods.core.fluid.BlankFluid;
import sourceteam.mods.core.fluid.BlankFluidH;
import sourceteam.mods.core.fluid.BlockFluid;
import sourceteam.mods.core.fluid.BlockFluidH;
import sourceteam.mods.core.mod.ModRegistry;
import sourceteam.mods.lib.mod.ISourceMod;

@Mod(modid = Core.MODID, name = Core.NAME, version = Core.VERSION)
public class Core implements ISourceMod {

	public static final String	MODID	= "sourcecore";
	public static final String	NAME	= "Source Core";
	public static final String	VERSION	= "1.0";

    public SourceLogger logger = SourceLogger.getLogger(NAME);

    public Configuration configuration;

    public  boolean mainMenuButton = true;

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
        blankFluid = new BlankFluid("sourcecore:fluid");
        FluidRegistry.registerFluid(blankFluid);
        FluidRegistry.registerFluid(new BlankFluid("BlankFluid"));
        blockFluid = new BlockFluid("sourcecore:fluid").setBlockName("sourcecore:BlankFluid");
        GameRegistry.registerBlock(blockFluid, "BlankFluid");


        blankFluidH = new BlankFluidH("sourcecore:fluidH");
        FluidRegistry.registerFluid(blankFluidH);
        FluidRegistry.registerFluid(new BlankFluidH("BlankFluidH"));
        blockFluidH = new BlockFluidH("sourcecore:fluidH").setBlockName("sourcecore:BlankFluidH");
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
