/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.core.client;


import io.github.asyncronous.toast.Toaster;
import me.modmuss50.mods.core.Core;
import me.modmuss50.mods.core.mod.ModRegistry;
import me.modmuss50.mods.lib.config.ConfigHandler;
import me.modmuss50.mods.lib.mod.IMod;
import me.modmuss50.mods.mml.ModScanner;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainMenuRenderer {

	public static final int BUTTON_ID = 405;

	public static boolean hasLoaded = false;

	@SubscribeEvent()
	public void onGuiInit(GuiScreenEvent.InitGuiEvent.Post evt) {

		if (!hasLoaded && Core.config.getBoolean("showLoadedMessage")) {
			try {
				Toaster.instance().pop("Minecraft has now loaded", new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/assets/modmusscore/toast/icons/error.png"))));
			} catch (Exception e) {
				e.printStackTrace();
			}
			hasLoaded = true;
		}

		if (!Core.config.getBoolean("mainMenuTweaks"))
			return;
		if (evt.gui instanceof GuiMainMenu) {
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
		if (!Core.config.getBoolean("mainMenuTweaks"))
			return;
		if (evt.gui instanceof GuiMainMenu) {
			if (evt.button.id == BUTTON_ID) {
				Minecraft.getMinecraft().displayGuiScreen(new GuSourceList(new GuiMainMenu()));
			}
		}
	}

	@SubscribeEvent
	public void drawScreen(GuiScreenEvent.DrawScreenEvent.Post event) {
		if (!Core.config.getBoolean("mainMenuTweaks"))
			return;
		if (event.gui instanceof GuiMainMenu) {
			event.gui.drawString(Minecraft.getMinecraft().fontRendererObj, "Loaded mods: " + ModRegistry.mods.size(), 1, 25, 16777215);

			int h = 0;
			for(IMod iMod : ModRegistry.mods){
				if(ModScanner.modNames.contains(iMod.modName())){
					event.gui.drawString(Minecraft.getMinecraft().fontRendererObj, iMod.modName(), 11, 35 + (h * 10), Color.green.getRGB());
					h++;
				}else{
					if(ModRegistry.mods.size() <= 5){
						event.gui.drawString(Minecraft.getMinecraft().fontRendererObj, iMod.modName(), 11, 35 + (h * 10), 16777215);
						h++;
					}
				}
			}
		}
	}

	//This fixes some weird issues with the animated texture api
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void textureHook(TextureStitchEvent.Post event) {
//		if (event.map.getTextureType() == 0) {
//			Core.blankFluid.setIcons(Core.blockFluid.getBlockTextureFromSide(1));
//			Core.blankFluidH.setIcons(Core.blockFluidH.getBlockTextureFromSide(1));
//		}
	}


}
