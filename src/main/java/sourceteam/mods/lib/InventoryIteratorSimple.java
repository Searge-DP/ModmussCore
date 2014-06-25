package sourceteam.mods.lib;

import java.util.Iterator;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryIteratorSimple implements Iterable<InventoryIterator.IInvSlot> {

	private final IInventory	inv;

	InventoryIteratorSimple(IInventory inv) {
		this.inv = invUtil.getInventory(inv);
	}

	@Override
	public Iterator<InventoryIterator.IInvSlot> iterator() {
		return new Iterator<InventoryIterator.IInvSlot>() {
			int	slot	= 0;

			@Override
			public boolean hasNext() {
				return slot < inv.getSizeInventory();
			}

			@Override
			public InventoryIterator.IInvSlot next() {
				return new InvSlot(slot++);
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException("Remove not supported.");
			}
		};
	}

	private class InvSlot implements InventoryIterator.IInvSlot {

		private int	slot;

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
			return inv.isItemValidForSlot(slot, stack);
		}

		@Override
		public boolean canTakeStackFromSlot(ItemStack stack) {
			return true;
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
