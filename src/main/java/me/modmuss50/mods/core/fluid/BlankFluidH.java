package me.modmuss50.mods.core.fluid;

import net.minecraftforge.fluids.Fluid;

public class BlankFluidH extends Fluid {
	public BlankFluidH(String fluidName) {
		super(fluidName);
		this.setIcons(BlockFluidH.StillIcon);
	}
}
