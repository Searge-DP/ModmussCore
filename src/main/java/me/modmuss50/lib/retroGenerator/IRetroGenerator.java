/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.lib.retroGenerator;

import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import java.util.Random;

public interface IRetroGenerator {
	public String getUniqueGenerationID();

	public boolean canGenerateIn(World world, Chunk chunk, Random random);

	public void generate(Random rand, World world, int chunkX, int chunkZ);
}
