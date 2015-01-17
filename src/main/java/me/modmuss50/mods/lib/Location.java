/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.lib;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Location implements Comparable<Location> {
	public int x;
	public int y;
	public int z;
	public int depth;

	public Location(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Location(int x, int y, int z, int depth) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Location(int xCoord, int yCoord, int zCoord, EnumFacing dir) {
		this.x = xCoord + dir.getFrontOffsetX();
		this.y = yCoord + dir.getFrontOffsetY();
		this.z = zCoord + dir.getFrontOffsetZ();
	}

	public Location(int[] coords) {
		if (coords.length >= 2) {
			this.x = coords[0];
			this.y = coords[1];
			this.z = coords[2];
		}
	}

//	public Location(ChunkPosition pos) {
//		if (pos != null) {
//			this.x = pos.chunkPosX;
//			this.y = pos.chunkPosY;
//			this.z = pos.chunkPosZ;
//		}
//	}

	public Location(MovingObjectPosition blockLookedAt) {
		if (blockLookedAt != null) {
			this.x = blockLookedAt.getBlockPos().getX();
			this.y = blockLookedAt.getBlockPos().getY();
			this.z = blockLookedAt.getBlockPos().getZ();
		}
	}

	public Location(TileEntity par1) {
		this.x = par1.getPos().getX();
		this.y = par1.getPos().getY();
		this.z = par1.getPos().getZ();
	}

	public boolean equals(Location toTest) {
		if (this.x == toTest.x && this.y == toTest.y && this.z == toTest.z) {
			return true;
		}
		return false;
	}

	public void setLocation(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int newX) {
		this.x = newX;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int newY) {
		this.y = newY;
	}

	public int getZ() {
		return this.z;
	}

	public void setZ(int newZ) {
		this.z = newZ;
	}

	public int[] getLocation() {
		int[] ret = new int[3];
		ret[0] = this.x;
		ret[1] = this.y;
		ret[2] = this.z;
		return ret;
	}

	public void setLocation(int[] coords) {
		this.x = coords[0];
		this.y = coords[1];
		this.z = coords[2];
	}

	public int getDifference(Location otherLoc) {
		return (int) Math.sqrt(Math.pow(this.x - otherLoc.x, 2) + Math.pow(this.y - otherLoc.y, 2) + Math.pow(this.z - otherLoc.z, 2));
	}

	public String printLocation() {
		return "X: " + this.x + " Y: " + this.y + " Z: " + this.z;
	}

	public String printCoords() {
		return this.x + ", " + this.y + ", " + this.z;
	}

	public boolean compare(int x, int y, int z) {
		return (this.x == x && this.y == y && this.z == z);
	}

	public Location getLocation(EnumFacing dir) {
		return new Location(x + dir.getFrontOffsetX(), y + dir.getFrontOffsetY(), z + dir.getFrontOffsetZ());
	}

	public Location modifyPositionFromSide(EnumFacing side, int amount) {
		switch (side.ordinal()) {
			case 0:
				this.y -= amount;
				break;
			case 1:
				this.y += amount;
				break;
			case 2:
				this.z -= amount;
				break;
			case 3:
				this.z += amount;
				break;
			case 4:
				this.x -= amount;
				break;
			case 5:
				this.x += amount;
				break;
		}
		return this;
	}

	public Location modifyPositionFromSide(EnumFacing side) {
		return this.modifyPositionFromSide(side, 1);
	}

	/**
	 * This will load the chunk.
	 */
	public TileEntity getTileEntity(IBlockAccess world) {
		return world.getTileEntity(new BlockPos(this.x, this.y, this.z));
	}

	public final Location clone() {
		return new Location(this.x, this.y, this.z);
	}

	/**
	 * No chunk load: returns null if chunk to side is unloaded
	 */
	public TileEntity getTileEntityOnSide(World world, EnumFacing side) {
		int x = this.x;
		int y = this.y;
		int z = this.z;
		switch (side.ordinal()) {
			case 0:
				y--;
				break;
			case 1:
				y++;
				break;
			case 2:
				z--;
				break;
			case 3:
				z++;
				break;
			case 4:
				x--;
				break;
			case 5:
				x++;
				break;
			default:
				return null;
		}
		if (!world.isAirBlock(new BlockPos(x, y, z))) {
			return world.getTileEntity(new BlockPos(x, y, z));
		} else {
			return null;
		}
	}

	/**
	 * No chunk load: returns null if chunk to side is unloaded
	 */
	public TileEntity getTileEntityOnSide(World world, int side) {
		int x = this.x;
		int y = this.y;
		int z = this.z;
		switch (side) {
			case 0:
				y--;
				break;
			case 1:
				y++;
				break;
			case 2:
				z--;
				break;
			case 3:
				z++;
				break;
			case 4:
				x--;
				break;
			case 5:
				x++;
				break;
			default:
				return null;
		}
		if (!world.isAirBlock(new BlockPos(x, y, z))) {
			return world.getTileEntity(new BlockPos(x, y, z));
		} else {
			return null;
		}
	}

	public int getDepth() {
		return depth;
	}

	@Override
	public int compareTo(Location o) {
		return ((Integer) depth).compareTo(o.depth);
	}
}