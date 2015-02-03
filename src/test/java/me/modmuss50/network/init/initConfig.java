/*
 * I am using this to show how the class replacer works.
 */
package me.modmuss50.network.init;

import me.modmuss50.mods.core.client.BaseModGui;
import me.modmuss50.mods.core.mod.ModRegistry;
import me.modmuss50.mods.lib.mod.ISourceMod;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class initConfig {
    private static Configuration config;

    private static void initBlocks() {
        ModRegistry.registerMod(new ISourceMod() {
            @Override
            public BaseModGui settingsScreen() {
                return null;
            }

            @Override
            public String modId() {
                return "TEST";
            }

            @Override
            public String modName() {
                return "THIS IS A CLASS REPLACEMENT TEST";
            }

            @Override
            public String modVersion() {
                return "2";
            }

            @Override
            public String recomenedMinecraftVeriosion() {
                return "1.8";
            }
        });
    }

    private static void initItems() {

    }

    private static void initGuis() {

    }

    private static void initEnergy() {

    }

    public static void loadConfig(File file) {
        config = new Configuration(file);

        initBlocks();
        initItems();
        initGuis();
        initEnergy();
    }
}
