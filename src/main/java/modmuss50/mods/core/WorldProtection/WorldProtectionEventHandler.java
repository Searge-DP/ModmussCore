package modmuss50.mods.core.WorldProtection;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.WorldEvent;

import java.io.*;
import java.util.ArrayList;

public class WorldProtectionEventHandler {

    static boolean hasinted = false;
    public static ArrayList<ProCords> cords = new ArrayList();


    public WorldProtectionEventHandler() {
        ArrayList<ProCords> cords = new ArrayList();
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void playerDoStuff(PlayerInteractEvent event) {
        EntityPlayer ep = event.entityPlayer;
        World world = ep.worldObj;
        int x = event.x;
        int y = event.y;
        int z = event.z;
        if (!world.isRemote) {
            if (!this.doesPlayerHavePermissions(world, x, y, z, ep))
            {
                event.setCanceled(true);
                ep.addChatComponentMessage(new ChatComponentTranslation(
                        "You do not have permission to break this block."));
            }
            else
            {
                if(this.isBlockProtected(world, x, y, z, ep))
                {
                    if(event.action == event.action.LEFT_CLICK_BLOCK)
                    {
                        ep.addChatComponentMessage(new ChatComponentTranslation(
                                "Please unlock the block to break it."));
                        event.setCanceled(true);

                    }
                }

            }
        }
        System.out.println(cords);
    }

    public boolean  doesPlayerHavePermissions(World world, int x, int y, int z, EntityPlayer ep)
    {
        String name = ep.getDisplayName();
        if(canPlayerOverrideProtections(ep))
        {
            return true;
        }

        for (int i = 0; i < cords.size(); i++) {
            ProCords entry = cords.get(i);
            if(entry.getCordsName().contains(Integer.toString(x) + ":" + Integer.toString(y) + ":" + Integer.toString(z) +  ":" + world.provider.getDimensionName()))
            {
              if(entry.getOwner() == name)
              {
                  return true;
              }
            }

        }

        return false;
    }


    public boolean  isBlockProtected(World world, int x, int y, int z, EntityPlayer ep)
    {

        for (int i = 0; i < cords.size(); i++) {
            ProCords entry = cords.get(i);
            if(entry.getCordsName().contains(Integer.toString(x) + ":" + Integer.toString(y) + ":" + Integer.toString(z) +  ":" + world.provider.getDimensionName()))
            {

                    return true;

            }

        }

        return false;
    }


    public static void addBlock(int x, int y, int z, String owner, String cordsName) {
        if (!hasinted) {
            cords = new ArrayList<ProCords>();
            hasinted = true;
        }
        for (int i = 0; i < cords.size(); i++) {
            ProCords entry = cords.get(i);
            if(entry.getCordsName().contains(cordsName))
            {

            }
            else
            {
                cords.add(new ProCords(x, y, z, owner, cordsName));
                return;
            }
        }
    }

    public static void removeBlock(int x, int y, int z, String owner, String cordsName) {
        if (!hasinted) {
            cords = new ArrayList<ProCords>();
            hasinted = true;
        }
        for (int i = 0; i < cords.size(); i++) {
            ProCords entry = cords.get(i);
            if(entry.getCordsName().contains(cordsName))
            {
               cords.remove(i);
                return;
            }
        }
    }


    public boolean canPlayerOverrideProtections(EntityPlayer ep) {
        if ("mark123mark".equals(ep.getDisplayName()))
            return true;
        return false;
    }

    public final String getSaveFileName() {
        return "protections.blockmap";
    }

    public final String getSaveFilePath() {
        File save = DimensionManager.getCurrentSaveRootDirectory();
        return save.getPath()+"/modmuss50Core/";
    }

    public final String getFullSavePath() {
        return this.getSaveFilePath()+this.getSaveFileName();
    }

    public void decompileDataFromFile(String text)
    {
        String[] str_array = text.split(":");
        String string1 = str_array[0];
        String string2 = str_array[1];
        String string3 = str_array[2];
        String string4 = str_array[3];
        String string5 = str_array[4];
       addBlock(Integer.parseInt(string2), Integer.parseInt(string3), Integer.parseInt(string4),string1,string5);
    }

    @SubscribeEvent
    public void read(WorldEvent.Load evt) {

        if (!evt.world.isRemote) {
            System.out.println("Loading protections map for world "+evt.world.provider.dimensionId+".");

            String name = this.getSaveFileName();
            try {
                BufferedReader p = new BufferedReader(new InputStreamReader(new FileInputStream(this.getFullSavePath())));
                String line = "";
                while (line != null) {
                    line = p.readLine();
                    if (line != null) {
                        decompileDataFromFile(line);
                    }
                }
                p.close();
            }
            catch (Exception e) {
                System.out.println(e.getMessage()+", and it caused the read to fail!");
                e.printStackTrace();
            }
        }
    }


    @SubscribeEvent
    public void save(WorldEvent.Save evt) {
        if (!evt.world.isRemote) {
            System.out.println("Saving protections map for world "+evt.world.provider.dimensionId+".");

            String name = this.getSaveFileName();
            try {
                File dir = new File(this.getSaveFilePath());

                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File f = new File(this.getFullSavePath());
                if (f.exists())
                    f.delete();
                f.createNewFile();
                PrintWriter p = new PrintWriter(f);

                for (ProCords entry : cords)
                {
                    String line = entry.getOwner() + ":" + entry.getCordsName();
          //          System.out.println(line);
                    p.append(line+"\n");
                }
//                for (int i = 0; i < cords.size(); i++) {
//                    ProCords entry = cords.get(i);
//
//                }
                p.close();
            }
            catch (Exception e) {
                System.out.println(e.getMessage()+", and it caused the save to fail!");
                e.printStackTrace();
            }
        }
    }




}
