/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.lib.multiblock;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;

/*
 * Base class for multiblock-capable blocks. This is only a reference implementation
 * and can be safely ignored.
 */
public abstract class BlockMultiblockBase extends BlockContainer {

	protected BlockMultiblockBase(Material material) {
		super(material);
	}
}
