package modmuss50.mods.core.WorldProtection;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class WorldProtectionEventHandler {



    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void playerDoStuff(PlayerInteractEvent event) {
        EntityPlayer ep = event.entityPlayer;
        World world = ep.worldObj;
        int x = event.x;
        int y = event.y;
        int z = event.z;
        if (!world.isRemote) {
         //   if (!this.doesPlayerHavePermissions(world, x, y, z, ep))
                event.setCanceled(true);
        }
    }

}
