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
import modmuss50.mods.api.BrandingsAPI;
import modmuss50.mods.core.Command.ModmussCommand;
import modmuss50.mods.core.Logger.ModLogger;
import modmuss50.mods.core.client.BaseModGui;
import modmuss50.mods.core.client.ClientInit;
import modmuss50.mods.core.client.SourceCoreSettings;
import modmuss50.mods.core.fluid.BlankFluid;
import modmuss50.mods.core.fluid.BlankFluidH;
import modmuss50.mods.core.fluid.BlockFluid;
import modmuss50.mods.core.fluid.BlockFluidH;
import modmuss50.mods.core.mod.ModRegistry;
import modmuss50.mods.lib.mod.ISourceMod;
import modmuss50.mods.lib.multiblock.MultiblockEventHandler;
import modmuss50.mods.lib.multiblock.MultiblockServerTickHandler;
import modmuss50.mods.lib.retroGenerator.RetroactiveWorldGenerator;
import modmuss50.mods.mml.EventHandler;
import modmuss50.mods.mml.ModScanner;
import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

@Mod(modid = Core.MODID, name = Core.NAME, version = Core.VERSION, guiFactory = "modmuss50.mods.core.client.config.ConfigGuiFactory")
public class Core implements ISourceMod {

	public static final String MODID = "modmussCore";
	public static final String NAME = "Modmuss50 Core";
	public static ModLogger logger = ModLogger.getLogger(NAME);
	public static final String VERSION = "@MODVERSION@"; //This gets changed with the build script
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
		blockFluid = new BlockFluid("modmusscore:fluid").setBlockName("modmusscore:BlankFluid");
		GameRegistry.registerBlock(blockFluid, "BlankFluid");

		blankFluidH = new BlankFluidH("modmusscore:fluidH");
		FluidRegistry.registerFluid(blankFluidH);
		FluidRegistry.registerFluid(new BlankFluidH("BlankFluidH"));
		blockFluidH = new BlockFluidH("modmusscore:fluidH").setBlockName("modmusscore:BlankFluidH");
		GameRegistry.registerBlock(blockFluidH, "BlankFluidH");

		//register the reto gen here
		FMLCommonHandler.instance().bus().register(new RetroactiveWorldGenerator());
		MinecraftForge.EVENT_BUS.register(new RetroactiveWorldGenerator());

		//Register all of the multiblock things here
		MinecraftForge.EVENT_BUS.register(new MultiblockEventHandler());
		FMLCommonHandler.instance().bus().register(new MultiblockServerTickHandler());
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		blankFluid.setIcons(BlockFluid.StillIcon);
		//Inject the brandings here
		BrandingsAPI.injectBrandings();

		try {
			ModScanner.enableMods();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Mod.EventHandler
	public void onServerStarting(FMLServerStartingEvent event) {
		event.registerServerCommand(new ModmussCommand());
	}

	@Mod.EventHandler
	public void load(FMLInitializationEvent event) {
		if (FMLCommonHandler.instance().getSide().isClient())
			ClientInit.load();

		MinecraftForge.EVENT_BUS.register(new EventHandler());
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
