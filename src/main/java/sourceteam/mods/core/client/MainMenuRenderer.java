package sourceteam.mods.core.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiScreenEvent;
import sourceteam.mods.core.mod.ModRegistry;

import java.util.List;

public class MainMenuRenderer {

    public static final int BUTTON_ID = 405;

    @SubscribeEvent()
    public void onGuiInit(GuiScreenEvent.InitGuiEvent.Post evt)
    {
        if (evt.gui instanceof GuiMainMenu)
        {
            List<GuiButton> buttonList = evt.buttonList;

            for (GuiButton button : buttonList)
            {
                if (button.id == BUTTON_ID)
                {
                    return;
                }
            }
            GuiButton button = new GuiButton(BUTTON_ID, 1, 1, 75, 20, "Source Mods");
            buttonList.add(button);
        }
    }

    @SubscribeEvent
    public void onActionPerformed(GuiScreenEvent.ActionPerformedEvent evt)
    {
        if (evt.gui instanceof GuiMainMenu)
        {
            if (evt.button.id == BUTTON_ID)
            {
                Minecraft.getMinecraft().displayGuiScreen(new GuSourceList(new GuiMainMenu()));
            }
        }
    }

    @SubscribeEvent
    public void drawScreen(GuiScreenEvent.DrawScreenEvent.Post event)
    {
        if(event.gui instanceof GuiMainMenu)
        {
            event.gui.drawString(Minecraft.getMinecraft().fontRenderer, "Loaded mods: " + ModRegistry.mods.size(), 1, 25 , 16777215);

            for (int i = 0; i < ModRegistry.mods.size(); i++) {
                event.gui.drawString(Minecraft.getMinecraft().fontRenderer, ModRegistry.mods.get(i).modName(), 11, 35 + (i *10) , 16777215);
            }

        }
    }
}
