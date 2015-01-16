/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.lib;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class StackHelper {

	private static StackHelper instance;

	protected StackHelper() {
	}

	public static StackHelper instance() {
		if (instance == null) {
			instance = new StackHelper();
		}
		return instance;
	}

	public static void setInstance(StackHelper inst) {
		instance = inst;
	}

	/* STACK MERGING */

	/**
	 * Checks if two ItemStacks are identical enough to be merged
	 *
	 * @param stack1 - The first stack
	 * @param stack2 - The second stack
	 * @return true if stacks can be merged, false otherwise
	 */
	public boolean canStacksMerge(ItemStack stack1, ItemStack stack2) {
		if (stack1 == null || stack2 == null)
			return false;
		if (!stack1.isItemEqual(stack2))
			return false;
		if (!ItemStack.areItemStackTagsEqual(stack1, stack2))
			return false;
		return true;

	}

	/**
	 * Merges mergeSource into mergeTarget
	 *
	 * @param mergeSource - The stack to merge into mergeTarget, this stack is not
	 *                    modified
	 * @param mergeTarget - The target merge, this stack is modified if doMerge is set
	 * @param doMerge     - To actually do the merge
	 * @return The number of items that was successfully merged.
	 */
	public int mergeStacks(ItemStack mergeSource, ItemStack mergeTarget, boolean doMerge) {
		if (!canStacksMerge(mergeSource, mergeTarget))
			return 0;
		int mergeCount = Math.min(mergeTarget.getMaxStackSize() - mergeTarget.stackSize, mergeSource.stackSize);
		if (mergeCount < 1)
			return 0;
		if (doMerge) {
			mergeTarget.stackSize += mergeCount;
		}
		return mergeCount;
	}

	/* ITEM COMPARISONS */

	/**
	 * Compares item id, damage and NBT. Accepts wildcard damage. Ignores damage
	 * entirely if the item doesn't have subtypes.
	 *
	 * @param base       The stack to compare to.
	 * @param comparison The stack to compare.
	 * @return true if id, damage and NBT match.
	 */
	public boolean isMatchingItem(ItemStack base, ItemStack comparison) {
		return isMatchingItem(base, comparison, true, true);
	}

	/**
	 * Compares item id, and optionally damage and NBT. Accepts wildcard damage.
	 * Ignores damage entirely if the item doesn't have subtypes.
	 *
	 * @param a           ItemStack
	 * @param b           ItemStack
	 * @param matchDamage
	 * @param matchNBT
	 * @return true if matches
	 */
	public boolean isMatchingItem(final ItemStack a, final ItemStack b, final boolean matchDamage, final boolean matchNBT) {
		if (a == null || b == null) {
			return false;
		}
		if (a.getItem() != b.getItem()) {
			return false;
		}
		if (matchDamage && a.getHasSubtypes()) {
			if (!isWildcard(a) && !isWildcard(b)) {
				if (a.getItemDamage() != b.getItemDamage()) {
					return false;
				}
			}
		}
		if (matchNBT) {
			if (a.hasTagCompound() && !a.getTagCompound().equals(b.getTagCompound())) {
				return false;
			}
		}
		return true;
	}

	public boolean isWildcard(ItemStack stack) {
		return isWildcard(stack.getItemDamage());
	}

	public boolean isWildcard(int damage) {
		return damage == -1 || damage == OreDictionary.WILDCARD_VALUE;
	}
}
