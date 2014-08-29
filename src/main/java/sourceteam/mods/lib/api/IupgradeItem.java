package sourceteam.mods.lib.api;

import net.minecraft.item.ItemStack;

/**
 * Created by Mark on 28/08/2014.
 */
public interface IupgradeItem {


    public boolean canStackEnterChest(ItemStack itemStack);

    //Return false for while list
    public boolean blackList();
}
