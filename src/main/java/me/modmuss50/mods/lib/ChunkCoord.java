/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.lib;

import net.minecraft.world.chunk.Chunk;

public class ChunkCoord {
	public int chunkX;
	public int chunkZ;

	public ChunkCoord(Chunk chunk) {
		this.chunkX = chunk.xPosition;
		this.chunkZ = chunk.zPosition;
	}

	public ChunkCoord(int x, int z) {
		this.chunkX = x;
		this.chunkZ = z;
	}

	public ChunkCoord() {
		this(0, 0);
	}

	public ChunkCoord(ChunkCoord cCoord) {
		this.chunkX = cCoord.chunkX;
		this.chunkZ = cCoord.chunkZ;
	}

	public static ChunkCoord getChunkCoordFromBlockCoords(int x, int z) {
		int chunkX = (int) Math.floor(x / 16);
		int chunkZ = (int) Math.floor(z / 16);

		return new ChunkCoord(chunkX, chunkZ);
	}

	public static ChunkCoord getChunkCoordFromBlockCoord(Location bCoord) {
		return getChunkCoordFromBlockCoords(bCoord.x, bCoord.z);
	}

	public int getCenterX() {
		return (this.chunkX << 4) + 8;
	}

	public int getCenterZ() {
		return (this.chunkZ << 4) + 8;
	}

	public boolean isEqual(ChunkCoord cCoord) {
		return (this.chunkX == cCoord.chunkX) && (this.chunkZ == cCoord.chunkZ);
	}

	@Override
	public Object clone() {
		return new ChunkCoord(this);
	}

	@Override
	public String toString() {
		return "ChunkCoord: [chunkX: " + this.chunkX + ", chunkZ: " + this.chunkZ + "]";
	}

	@Override
	public int hashCode() {
		return ("chunkX:" + this.chunkX + "chunkZ:" + this.chunkZ).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof ChunkCoord) && isEqual((ChunkCoord) obj);
	}
}
