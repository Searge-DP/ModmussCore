package modmuss50.mods.core.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;

/**
 * Created with IntelliJ IDEA.
 * User: Mark
 * Date: 04/04/14
 * Time: 13:14
 */
public class GuiTicker {

    @SubscribeEvent
    public void TestTickEvent(TickEvent.ClientTickEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        GuiScreen tempscreen = null;

        if(mc.currentScreen instanceof GuiOptions)
        {

        }else
        {
            tempscreen = mc.currentScreen;
        }


        //  System.out.println("hum?");
        if (mc.currentScreen instanceof GuiOptions) {
            //           if(tempscreen != null)
            {
                mc.displayGuiScreen(new GuiNewSettings(tempscreen, mc.gameSettings));
            }
        }
    }



}