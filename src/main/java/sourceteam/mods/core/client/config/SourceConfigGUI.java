package sourceteam.mods.core.client.config;

import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import sourceteam.mods.core.ConfigurationHandler;
import sourceteam.mods.core.Core;


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
