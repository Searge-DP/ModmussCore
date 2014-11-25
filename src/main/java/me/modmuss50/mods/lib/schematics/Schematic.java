package me.modmuss50.mods.lib.schematics;

import net.minecraft.block.Block;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.io.InputStream;

public class Schematic {
	public NBTTagList tileentities;
	public short width;
	public short height;
	public short length;
	public byte[] blocks;
	public byte[] data;

	public Schematic(String location) {
		try {
			InputStream is = getClass().getResourceAsStream(location);
			NBTTagCompound nbtdata = CompressedStreamTools.readCompressed(is);
			width = nbtdata.getShort("Width");
			height = nbtdata.getShort("Height");
			length = nbtdata.getShort("Length");

			blocks = nbtdata.getByteArray("Blocks");
			data = nbtdata.getByteArray("Data");
			tileentities = nbtdata.getTagList("TileEntities", 10);

			System.out.println("Loaded schematic from " + location + " with attributes: width - " + width + ", height - " + height + ", length - " + length);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public boolean placeSchematic(World world, int x, int y, int z, boolean placeair) {
		new SchematicThread(world, x, y, z, this, placeair).run();
		return true;
	}

	public boolean canplaceBlock(boolean air, Block block) {
		if (block.getUnlocalizedName().contains("air")) {
			return air;
		} else {
			return true;
		}
	}

	class SchematicThread extends Thread {
		public int x;
		public int z;
		public int y;

		public World world;
		public Schematic schematic;

		public boolean placeair;

		public SchematicThread(World world, int x, int y, int z, Schematic schematic, boolean placeair) {
			super("Schematic Placer");

			this.world = world;
			this.x = x;
			this.y = y;
			this.z = z;
			this.schematic = schematic;
			this.placeair = placeair;
		}

		@Override
		public void run() {
			int id = 0;
			int placedBlocks = 0;

			x = x - (schematic.width - schematic.length / 2);
			z = z - (schematic.length - schematic.length / 2);

			for (int sy = 0; sy < schematic.height; sy++) {
				for (int sz = 0; sz < schematic.length; sz++) {
					for (int sx = 0; sx < schematic.width; sx++) {
						Block block = Block.getBlockById(schematic.blocks[id]);
						if (canplaceBlock(placeair, block)) {
							world.setBlock(x + sx, y + sy, z + sz, block, schematic.data[id], 2);
							placedBlocks += 1;
						}
						if (schematic.tileentities != null) {
							for (int il = 0; il < schematic.tileentities.tagCount(); ++il) {
								NBTTagCompound tag = schematic.tileentities.getCompoundTagAt(il);
								TileEntity tileEntity = TileEntity.createAndLoadEntity(tag);
								if (tileEntity != null) {
									tileEntity.xCoord = x + sx;
									tileEntity.yCoord = y + sy;
									tileEntity.zCoord = z + sz;
									world.setTileEntity(x + sx, y + sy, z + sz, tileEntity);
								}
							}
						}
						id = id + 1;
					}
				}
			}
			System.out.println("Placed " + placedBlocks + " blocks");
			this.stop();
		}
	}


}
