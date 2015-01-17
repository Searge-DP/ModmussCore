/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.lib;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

/**
 * Created with IntelliJ IDEA. User: Mark Date: 02/04/14 Time: 15:30
 */
public class KeyHandler {

	public KeyBinding key = new KeyBinding("Transcraft Flight", Keyboard.KEY_F, "key.categories.inventory");

	public KeyHandler() {
		ClientRegistry.registerKeyBinding(key);
	}

	@SideOnly(value = Side.CLIENT)
	@SubscribeEvent
	public void playerTick(TickEvent.PlayerTickEvent event) {
		if (event.side == Side.SERVER)
			return;
		if (event.phase == TickEvent.Phase.START) {
			if (key.isKeyDown() && FMLClientHandler.instance().getClient().inGameHasFocus) {

			}
		}
	}
}
