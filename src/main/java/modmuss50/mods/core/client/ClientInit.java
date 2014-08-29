package modmuss50.mods.core.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraftforge.common.MinecraftForge;
import modmuss50.mods.lib.api.client.RenderAnimatedBlock;

/**
 * Created by Mark on 06/07/14.
 */
public class ClientInit {

    public static int render;

    public static void load() {
        render = RenderingRegistry.getNextAvailableRenderId();
        MinecraftForge.EVENT_BUS.register(new MainMenuRenderer());
        RenderingRegistry.registerBlockHandler(render, new RenderAnimatedBlock());
    }

    public static void preInt() {


    }


}
