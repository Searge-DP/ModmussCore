package sourceteam.mods.core.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;
import sourceteam.mods.core.Core;
import sourceteam.mods.core.mod.ModRegistry;
import sourceteam.mods.lib.api.client.RenderAnimatedBlock;

/**
 * Created by Mark on 06/07/14.
 */
public class ClientInit {

    public static int render;

    public static void load()
    {
        render = RenderingRegistry.getNextAvailableRenderId();
        MinecraftForge.EVENT_BUS.register(new MainMenuRenderer());
        RenderingRegistry.registerBlockHandler(render, new RenderAnimatedBlock());
    }

    public static void preInt()
    {



    }




}
