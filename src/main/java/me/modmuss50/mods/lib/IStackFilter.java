package me.modmuss50.mods.lib;

import net.minecraft.item.ItemStack;

public interface IStackFilter {

	public boolean matches(ItemStack stack);
}
