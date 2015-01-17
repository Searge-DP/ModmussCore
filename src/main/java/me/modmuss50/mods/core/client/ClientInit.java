/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.core.client;

import me.modmuss50.mods.lib.multiblock.MultiblockClientTickHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;

/**
 * Created by Mark on 06/07/14.
 */
public class ClientInit {

	public static void load() {
		MinecraftForge.EVENT_BUS.register(new MainMenuRenderer());
		FMLCommonHandler.instance().bus().register(new MultiblockClientTickHandler());
		MinecraftForge.EVENT_BUS.register(new BlockHighlightHandler());
	}
}
