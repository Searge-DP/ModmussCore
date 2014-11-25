/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.core.fluid;

import net.minecraftforge.fluids.Fluid;

public class BlankFluid extends Fluid {
	public BlankFluid(String fluidName) {
		super(fluidName);
		this.setIcons(BlockFluid.StillIcon);
	}
}
