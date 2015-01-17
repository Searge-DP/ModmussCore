/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.lib;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

/**
 * Created by Mark on 21/04/14.
 */
public interface ITransactor {
	/**
	 * Adds an Item to the inventory.
	 *
	 * @param stack
	 * @param orientation
	 * @param doAdd
	 * @return The ItemStack, with stackSize equal to amount moved.
	 */
	ItemStack add(ItemStack stack, EnumFacing orientation, boolean doAdd);

	/**
	 * Removes and returns a single item from the inventory matching the filter.
	 *
	 * @param filter
	 * @param orientation
	 * @param doRemove
	 * @return
	 */
	ItemStack remove(IStackFilter filter, EnumFacing orientation, boolean doRemove);
}
