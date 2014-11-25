/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.lib;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class Transactor implements ITransactor {

	public static ITransactor getTransactorFor(Object object) {

		if (object instanceof ISidedInventory)
			return new TransactorSimple((ISidedInventory) object);
		else if (object instanceof IInventory)
			return new TransactorSimple(invUtil.getInventory((IInventory) object));
		return null;
	}

	@Override
	public ItemStack add(ItemStack stack, ForgeDirection orientation, boolean doAdd) {
		ItemStack added = stack.copy();
		added.stackSize = inject(stack, orientation, doAdd);
		return added;
	}

	public abstract int inject(ItemStack stack, ForgeDirection orientation, boolean doAdd);
}
