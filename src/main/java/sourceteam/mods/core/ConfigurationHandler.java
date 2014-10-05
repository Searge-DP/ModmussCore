package sourceteam.mods.core;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;
import sourceteam.mods.api.BrandingsAPI;

import java.io.File;

public class ConfigurationHandler {

    public static Configuration configuration;
    public static boolean mainMenuOvelay = false;
    public static boolean brandingsInjector = true;

    public static void init(File configFile) {
        // Create the configuration object from the given configuration file
        if (configuration == null) {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
    }

    private static void loadConfiguration() {
        mainMenuOvelay = configuration.getBoolean("Main Menu Overlay", Configuration.CATEGORY_GENERAL, false, "Should source core show the main menu overlay?");
        brandingsInjector = configuration.getBoolean("InjectBrandings", Configuration.CATEGORY_GENERAL, true, "If this is set to false the brandings api wont work");

        if (configuration.hasChanged()) {
            configuration.save();
            //Do this to reset the brandings so you dont have to restart the game for the changes to take effect
            BrandingsAPI.injectBrandings();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equalsIgnoreCase(Core.MODID)) {
            loadConfiguration();
        }
    }
}
