package modmuss50.mods.mml;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.world.WorldEvent;

public class EventHandler {

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onWorldLoad(WorldEvent.Load evt) {
		System.out.println("LOADIGN WOLFOIFHFIOUHF");
				ModScanner.loadMods();
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onWorldUnload(WorldEvent.Load evt) {
		try {
			ModScanner.disableMods();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
