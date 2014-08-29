package modmuss50.mods.core.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import modmuss50.mods.core.ConfigurationHandler;
import modmuss50.mods.core.Core;
import modmuss50.mods.core.mod.ModRegistry;

import java.util.List;

public class MainMenuRenderer {

    public static final int BUTTON_ID = 405;

    @SubscribeEvent()
    public void onGuiInit(GuiScreenEvent.InitGuiEvent.Post evt) {
        if (!ConfigurationHandler.mainMenuOvelay)
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
        if (!ConfigurationHandler.mainMenuOvelay)
            return;
        if (evt.gui instanceof GuiMainMenu) {
            if (evt.button.id == BUTTON_ID) {
                Minecraft.getMinecraft().displayGuiScreen(new GuSourceList(new GuiMainMenu()));
            }
        }
    }

    @SubscribeEvent
    public void drawScreen(GuiScreenEvent.DrawScreenEvent.Post event) {
        if (!ConfigurationHandler.mainMenuOvelay)
            return;
        if (event.gui instanceof GuiMainMenu) {
            event.gui.drawString(Minecraft.getMinecraft().fontRenderer, "Loaded mods: " + ModRegistry.mods.size(), 1, 25, 16777215);

            for (int i = 0; i < ModRegistry.mods.size(); i++) {
                event.gui.drawString(Minecraft.getMinecraft().fontRenderer, ModRegistry.mods.get(i).modName(), 11, 35 + (i * 10), 16777215);
            }
        }
    }


    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void textureHook(TextureStitchEvent.Post event) {
        if (event.map.getTextureType() == 0) {
            Core.blankFluid.setIcons(Core.blockFluid.getBlockTextureFromSide(1));
            Core.blankFluidH.setIcons(Core.blockFluidH.getBlockTextureFromSide(1));
        }
    }


}
