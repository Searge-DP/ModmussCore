package modmuss50.mods.core.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;

import java.util.List;

public class GuiMainMenuHandler {


    public static final int BUTTON_ID = 405;

    public static void initGui(GuiScreen gui, List<GuiButton> buttonList)
    {
        for (GuiButton button : buttonList)
        {
            if (button.id == BUTTON_ID)
            {
                return;
            }
        }
        GuiButton button = new GuiButton(BUTTON_ID, gui.width / 2 - 124, gui.height / 4 + 96, "Modmuss50 Settings");
        buttonList.add(button);
    }

    public static void onActionPerformed(GuiButton gui)
    {
        if (gui.id == BUTTON_ID)
        {
            Minecraft.getMinecraft().displayGuiScreen(new GuiModmuss50List(new GuiMainMenu()));
        }
    }
}
