package modmuss50.mods.lib.retroGenerator;

import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import java.util.Random;

public interface IRetroGenerator
{
	public String getUniqueGenerationID();

	public boolean canGenerateIn(World world, Chunk chunk, Random random);

	public void generate(Random rand, World world, int chunkX, int chunkZ);
}
