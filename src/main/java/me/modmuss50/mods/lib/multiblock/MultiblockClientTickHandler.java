/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.lib.multiblock;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;

public class MultiblockClientTickHandler {

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) {
		if (event.phase == TickEvent.Phase.START) {
			MultiblockRegistry.tickStart(Minecraft.getMinecraft().theWorld);
		} else if (event.phase == TickEvent.Phase.END) { //Probably could just to else, but better to be safe than sorry
			MultiblockRegistry.tickEnd(Minecraft.getMinecraft().theWorld);
		}
	}
}
