package modmuss50.mods.core.client.config;

import cpw.mods.fml.client.config.GuiConfig;
import modmuss50.mods.core.ConfigurationHandler;
import modmuss50.mods.core.Core;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;


public class SourceConfigGUI extends GuiConfig {
	public SourceConfigGUI(GuiScreen parent) {
		super(parent,
				new ConfigElement(ConfigurationHandler.configuration.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
				Core.MODID,
				false,
				false,
				GuiConfig.getAbridgedConfigPath(ConfigurationHandler.configuration.toString()));
	}
}
