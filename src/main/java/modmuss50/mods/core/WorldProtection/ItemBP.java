package modmuss50.mods.core.WorldProtection;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class ItemBP extends Item {

    public ItemBP() {
        super();
        maxStackSize = 1;
        setCreativeTab(CreativeTabs.tabMisc);
        setUnlocalizedName("BlockProtector");
    }

    @Override
    public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float xOffset, float yOffset, float zOffSet){

        if(!world.isRemote)
        {
            if(!player.isSneaking())
            {
                WorldProtectionEventHandler.addBlock(x, y, z,player.getDisplayName(),(Integer.toString(x) + ":" + Integer.toString(y) +  ":" + Integer.toString(z) +  ":" + world.provider.getDimensionName()) );
                player.addChatComponentMessage(new ChatComponentTranslation(
                        "This Block has been protected!"));
                return true;
            }
            else
            {
                WorldProtectionEventHandler.removeBlock(x, y, z,player.getDisplayName(),(Integer.toString(x) + ":" + Integer.toString(y) +  ":" +Integer.toString(z) +  ":" + world.provider.getDimensionName()  ) );
                player.addChatComponentMessage(new ChatComponentTranslation(
                        "Removed block from the protection list"));
                return true;
            }
        }
      return false;
    }



}
