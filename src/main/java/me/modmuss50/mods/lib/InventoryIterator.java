/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.lib;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

public class InventoryIterator {

	/**
	 * Returns an Iterable object for the specified side of the inventory.
	 *
	 * @param inv
	 * @param side
	 * @return Iterable
	 */
	public static Iterable<IInvSlot> getIterable(IInventory inv, ForgeDirection side) {
		if (inv instanceof ISidedInventory) {
			return new InventoryIteratorSided((ISidedInventory) inv, side);
		}

		return new InventoryIteratorSimple(inv);
	}

	public interface IInvSlot {

		/**
		 * Returns the slot number of the underlying Inventory.
		 *
		 * @return the slot number
		 */
		int getIndex();

		boolean canPutStackInSlot(ItemStack stack);

		boolean canTakeStackFromSlot(ItemStack stack);

		ItemStack decreaseStackInSlot();

		ItemStack getStackInSlot();

		void setStackInSlot(ItemStack stack);
	}
}
