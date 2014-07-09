package sourceteam.mods.lib.schematics;

import net.minecraft.block.Block;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.io.InputStream;

/**
 * * @package sourceteam.mods.lib.schematics
 * * @author tattyseal
 * *
 */
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


    public static boolean placeSchematic(World world, int x, int y, int z, Schematic schematic) {
        int id = 0;

        for (int sy = 0; sy < schematic.height; sy++) {
            for (int sz = 0; sz < schematic.length; sz++) {
                for (int sx = 0; sx < schematic.width; sx++) {
                    Block block = Block.getBlockById(schematic.blocks[id]);

                    world.setBlockToAir(x + sx, y + sy, z + sz);
                    world.setBlock(x + sx, y + sy, z + sz, block, 0, 2);

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
                        id = id + 1;
                    }
                }
            }

        }

        return true;
    }
}
