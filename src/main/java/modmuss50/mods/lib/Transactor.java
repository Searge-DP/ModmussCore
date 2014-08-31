package modmuss50.mods.lib;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import modmuss50.mods.lib.api.IinvUpgrade;

public abstract class Transactor implements ITransactor {

    @Override
    public ItemStack add(ItemStack stack, ForgeDirection orientation, boolean doAdd) {
        ItemStack added = stack.copy();
        added.stackSize = inject(stack, orientation, doAdd);
        return added;
    }

    public abstract int inject(ItemStack stack, ForgeDirection orientation, boolean doAdd);

    public static ITransactor getTransactorFor(TileEntity object) {

        if (object instanceof ISidedInventory)
            return new TransactorSimple((ISidedInventory) object);
                //TODO fix this, i may code this in network not here
//        else if (object instanceof IInventory && object instanceof IinvUpgrade)
//            return new TransactorAdvanced(invUtil.getInventory((IInventory) object), (IinvUpgrade) object);
        else if (object instanceof IInventory)
            return new TransactorSimple(invUtil.getInventory((IInventory) object));
        return null;
    }
}
