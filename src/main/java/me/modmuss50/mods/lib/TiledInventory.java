package me.modmuss50.mods.lib;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.common.util.Constants;

public class TiledInventory implements IInventory {

	private final ItemStack[] contents;
	private final String name;
	private final int stackLimit;
	private TileEntity tile;

	public TiledInventory(int size, String invName, int invStackLimit) {
		contents = new ItemStack[size];
		name = invName;
		stackLimit = invStackLimit;
	}

	@Override
	public int getSizeInventory() {
		return contents.length;
	}

	@Override
	public ItemStack getStackInSlot(int slotId) {
		return contents[slotId];
	}

	@Override
	public ItemStack decrStackSize(int slotId, int count) {
		if (slotId < contents.length && contents[slotId] != null) {
			if (contents[slotId].stackSize > count) {
				ItemStack result = contents[slotId].splitStack(count);
				markDirty();
				return result;
			}
			ItemStack stack = contents[slotId];
			setInventorySlotContents(slotId, null);
			return stack;
		}
		return null;
	}

	@Override
	public void setInventorySlotContents(int slotId, ItemStack itemstack) {
		if (slotId >= contents.length) {
			return;
		}
		contents[slotId] = itemstack;

		if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
			itemstack.stackSize = this.getInventoryStackLimit();
		}
		markDirty();
	}

	@Override
	public int getInventoryStackLimit() {
		return stackLimit;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) {

	}

	@Override
	public void closeInventory(EntityPlayer player) {

	}


	public void readFromNBT(NBTTagCompound data) {
		readFromNBT(data, "Items");
	}

	public void readFromNBT(NBTTagCompound data, String tag) {
		NBTTagList nbttaglist = data.getTagList(tag, Constants.NBT.TAG_COMPOUND);

		for (int j = 0; j < nbttaglist.tagCount(); ++j) {
			NBTTagCompound slot = nbttaglist.getCompoundTagAt(j);
			int index;
			if (slot.hasKey("index")) {
				index = slot.getInteger("index");
			} else {
				index = slot.getByte("Slot");
			}
			if (index >= 0 && index < contents.length) {
				setInventorySlotContents(index, ItemStack.loadItemStackFromNBT(slot));
			}
		}
	}

	public void writeToNBT(NBTTagCompound data) {
		writeToNBT(data, "Items");
	}

	public void writeToNBT(NBTTagCompound data, String tag) {
		NBTTagList slots = new NBTTagList();
		for (byte index = 0; index < contents.length; ++index) {
			if (contents[index] != null && contents[index].stackSize > 0) {
				NBTTagCompound slot = new NBTTagCompound();
				slots.appendTag(slot);
				slot.setByte("Slot", index);
				contents[index].writeToNBT(slot);
			}
		}
		data.setTag(tag, slots);
	}

	public void setTile(TileEntity tileEntity) {
		tile = tileEntity;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slotId) {
		if (this.contents[slotId] == null) {
			return null;
		}

		ItemStack stackToTake = this.contents[slotId];
		setInventorySlotContents(slotId, null);
		return stackToTake;
	}

	public ItemStack[] getStacks() {
		return contents;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {

	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {

	}

	@Override
	public void markDirty() {
		tile.markDirty();
	}

	@Override
	public String getCommandSenderName() {
		return null;
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public IChatComponent getDisplayName() {
		return new ChatComponentText(name);
	}
}
