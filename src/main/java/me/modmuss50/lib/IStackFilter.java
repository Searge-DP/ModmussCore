/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.lib;

import net.minecraft.item.ItemStack;

public interface IStackFilter {

	public boolean matches(ItemStack stack);
}
