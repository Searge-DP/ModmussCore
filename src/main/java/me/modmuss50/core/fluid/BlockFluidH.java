/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.core.fluid;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.BlockFluidClassic;

public class BlockFluidH extends BlockFluidClassic {


	public static IIcon StillIcon;

	public BlockFluidH(String fluidName) {
		super(new BlankFluid("BlankFluid"), Material.water);

	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		StillIcon = iconRegister.registerIcon("sourcecore:fluidh");
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return StillIcon;
	}


}
