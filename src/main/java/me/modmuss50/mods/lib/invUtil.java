/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.lib;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class invUtil {

	private static final List<ForgeDirection> directions = new ArrayList<ForgeDirection>(Arrays.asList(ForgeDirection.VALID_DIRECTIONS));

	/* IINVENTORY HELPERS */

	/**
	 * Tries to add the passed stack to any valid inventories around the given
	 * coordinates.
	 *
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param stack
	 * @return amount used
	 */
	public static int addToRandomInventoryAround(World world, int x, int y, int z, ItemStack stack) {
		Collections.shuffle(directions);
		for (ForgeDirection orientation : directions) {
			Position pos = new Position(x, y, z, orientation);
			pos.moveForwards(1.0);

			TileEntity tileInventory = world.getTileEntity((int) pos.x, (int) pos.y, (int) pos.z);
			ITransactor transactor = Transactor.getTransactorFor(tileInventory);
			if (transactor != null && transactor.add(stack, orientation.getOpposite(), false).stackSize > 0) {
				return transactor.add(stack, orientation.getOpposite(), true).stackSize;
			}
		}
		return 0;

	}

	/**
	 * Tries to add the passed stack to any valid inventories around the given
	 * coordinates.
	 *
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param stack
	 * @return amount used
	 */
	public static int addToInventoryAround(World world, int x, int y, int z, ItemStack stack) {
		for (ForgeDirection orientation : directions) {
			Position pos = new Position(x, y, z, orientation);
			pos.moveForwards(1.0);

			TileEntity tileInventory = world.getTileEntity((int) pos.x, (int) pos.y, (int) pos.z);
			ITransactor transactor = Transactor.getTransactorFor(tileInventory);
			if (transactor != null && transactor.add(stack, orientation.getOpposite(), false).stackSize > 0) {
				return transactor.add(stack, orientation.getOpposite(), true).stackSize;
			}
		}
		return 0;

	}

	/**
	 * Tries to add the passed stack to and inventory from the direction.
	 *
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param orientation
	 * @param stack
	 * @return amount used
	 */
	public static int addToInventoryAround(World world, int x, int y, int z, ForgeDirection orientation , ItemStack stack) {
			Position pos = new Position(x, y, z, orientation);
			pos.moveForwards(1.0);
			TileEntity tileInventory = world.getTileEntity((int) pos.x, (int) pos.y, (int) pos.z);
			ITransactor transactor = Transactor.getTransactorFor(tileInventory);
			if (transactor != null && transactor.add(stack, orientation.getOpposite(), false).stackSize > 0) {
				return transactor.add(stack, orientation.getOpposite(), true).stackSize;
			}

		return 0;

	}


	/**
	 * Tries to add the passed stack to a valid inventorie at the given
	 * coordinates.
	 *
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param stack
	 * @return amount used
	 */
	public static int addToInventory(World world, int x, int y, int z, ItemStack stack) {
		Position pos = new Position(x, y, z, ForgeDirection.DOWN);

		TileEntity tileInventory = world.getTileEntity((int) pos.x, (int) pos.y, (int) pos.z);
		ITransactor transactor = Transactor.getTransactorFor(tileInventory);
		if (transactor != null && transactor.add(stack, ForgeDirection.DOWN, false).stackSize > 0) {

			return transactor.add(stack, ForgeDirection.DOWN, true).stackSize;
		}
		return 0;

	}

	/**
	 * Ensures that the given inventory is the full inventory, i.e. takes double
	 * chests into account.
	 *
	 * @param inv
	 * @return Modified inventory if double chest, unmodified otherwise.
	 */
	public static IInventory getInventory(IInventory inv) {
		if (inv instanceof TileEntityChest) {
			TileEntityChest chest = (TileEntityChest) inv;

			TileEntityChest adjacent = null;

			if (chest.adjacentChestXNeg != null) {
				adjacent = chest.adjacentChestXNeg;
			}

			if (chest.adjacentChestXPos != null) {
				adjacent = chest.adjacentChestXPos;
			}

			if (chest.adjacentChestZNeg != null) {
				adjacent = chest.adjacentChestZNeg;
			}

			if (chest.adjacentChestZPos != null) {
				adjacent = chest.adjacentChestZPos;
			}

			if (adjacent != null) {
				return new InventoryLargeChest("", inv, adjacent);
			}
			return inv;
		}
		return inv;
	}

}
