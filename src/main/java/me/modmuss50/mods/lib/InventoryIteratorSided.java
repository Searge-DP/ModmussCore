/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.lib;

import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Iterator;

public class InventoryIteratorSided implements Iterable<InventoryIterator.IInvSlot> {

	private final ISidedInventory inv;
	private final int side;

	InventoryIteratorSided(ISidedInventory inv, ForgeDirection side) {
		this.inv = inv;
		this.side = side.ordinal();
	}

	@Override
	public Iterator<InventoryIterator.IInvSlot> iterator() {
		return new Iterator<InventoryIterator.IInvSlot>() {
			int[] slots = inv.getAccessibleSlotsFromSide(side);
			int index = 0;

			@Override
			public boolean hasNext() {
				return index < slots.length;
			}

			@Override
			public InventoryIterator.IInvSlot next() {
				return new InvSlot(slots[index++]);
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException("Remove not supported.");
			}

		};
	}

	private class InvSlot implements InventoryIterator.IInvSlot {

		private int slot;

		public InvSlot(int slot) {
			this.slot = slot;
		}

		@Override
		public ItemStack getStackInSlot() {
			return inv.getStackInSlot(slot);
		}

		@Override
		public void setStackInSlot(ItemStack stack) {
			inv.setInventorySlotContents(slot, stack);
		}

		@Override
		public boolean canPutStackInSlot(ItemStack stack) {
			return inv.canInsertItem(slot, stack, side);
		}

		@Override
		public boolean canTakeStackFromSlot(ItemStack stack) {
			return inv.canExtractItem(slot, stack, side);
		}

		@Override
		public ItemStack decreaseStackInSlot() {
			return inv.decrStackSize(slot, 1);
		}

		@Override
		public int getIndex() {
			return slot;
		}

	}
}
