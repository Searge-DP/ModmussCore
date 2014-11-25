package me.modmuss50.mods.lib;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.settings.KeyBinding;
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
			if (key.getIsKeyPressed() && FMLClientHandler.instance().getClient().inGameHasFocus) {

			}
		}
	}
}
