/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.core.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.github.asyncronous.toast.Toaster;
import me.modmuss50.core.FmlLoadingCore;
import me.modmuss50.core.Core;
import me.modmuss50.core.mod.ModRegistry;
import me.modmuss50.lib.config.ConfigHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.TextureStitchEvent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainMenuRenderer {

	public static final int BUTTON_ID = 405;

	public static boolean hasLoaded = false;

	@SubscribeEvent()
	public void onGuiInit(GuiScreenEvent.InitGuiEvent.Post evt) {

		if (!hasLoaded && ConfigHandler.getBoolean("showLoadedMessage")) {
			try {
				Toaster.instance().pop("Minecraft has now loaded", new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/assets/modmusscore/toast/icons/error.png"))));
			} catch (IOException e) {
				e.printStackTrace();
			}
			hasLoaded = true;
		}

		if (!ConfigHandler.getBoolean("mainMenuTweaks"))
			return;
		if (evt.gui instanceof GuiMainMenu) {
			System.out.println("Time to load in seconds" + FmlLoadingCore.stopwatch.elapsed(TimeUnit.SECONDS));
			System.out.println("Time to load in mili" + (Long)FmlLoadingCore.stopwatch.elapsed(TimeUnit.MILLISECONDS) /10);
			List<GuiButton> buttonList = evt.buttonList;

			for (GuiButton button : buttonList) {
				if (button.id == BUTTON_ID) {
					return;
				}
			}
			GuiButton button = new GuiButton(BUTTON_ID, 1, 1, 45, 20, "Mods");
			buttonList.add(button);
		}


	}

	@SubscribeEvent
	public void onActionPerformed(GuiScreenEvent.ActionPerformedEvent evt) {
		if (!ConfigHandler.getBoolean("mainMenuTweaks"))
			return;
		if (evt.gui instanceof GuiMainMenu) {
			if (evt.button.id == BUTTON_ID) {
				Minecraft.getMinecraft().displayGuiScreen(new GuSourceList(new GuiMainMenu()));
			}
		}
	}

	@SubscribeEvent
	public void drawScreen(GuiScreenEvent.DrawScreenEvent.Post event) {
		if (!ConfigHandler.getBoolean("mainMenuTweaks"))
			return;
		if (event.gui instanceof GuiMainMenu) {
			event.gui.drawString(Minecraft.getMinecraft().fontRenderer, "Loaded mods: " + ModRegistry.mods.size(), 1, 25, 16777215);

			for (int i = 0; i < ModRegistry.mods.size(); i++) {
				event.gui.drawString(Minecraft.getMinecraft().fontRenderer, ModRegistry.mods.get(i).modName(), 11, 35 + (i * 10), 16777215);
			}
		}
	}


	//This fixes some weird issues with the animated texture api
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void textureHook(TextureStitchEvent.Post event) {
		if (event.map.getTextureType() == 0) {
			Core.blankFluid.setIcons(Core.blockFluid.getBlockTextureFromSide(1));
			Core.blankFluidH.setIcons(Core.blockFluidH.getBlockTextureFromSide(1));
		}
	}


}
