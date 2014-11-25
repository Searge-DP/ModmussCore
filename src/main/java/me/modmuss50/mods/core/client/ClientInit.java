/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.core.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import me.modmuss50.mods.lib.multiblock.MultiblockClientTickHandler;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by Mark on 06/07/14.
 */
public class ClientInit {

	public static int render;

	public static void load() {
		render = RenderingRegistry.getNextAvailableRenderId();
		MinecraftForge.EVENT_BUS.register(new MainMenuRenderer());
		FMLCommonHandler.instance().bus().register(new MultiblockClientTickHandler());
	}

	public static void preInt() {


	}


}
