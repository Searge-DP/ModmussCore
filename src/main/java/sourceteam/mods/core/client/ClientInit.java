package sourceteam.mods.core.client;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;
import sourceteam.mods.core.Core;
import sourceteam.mods.core.mod.ModRegistry;

/**
 * Created by Mark on 06/07/14.
 */
public class ClientInit {
    public static void load()
    {

            MinecraftForge.EVENT_BUS.register(new MainMenuRenderer());
    }

    public static void preInt()
    {



    }




}
