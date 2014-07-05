package sourceteam.mods.core.fluid;

import net.minecraftforge.fluids.Fluid;

public class BlankFluid extends Fluid {
    public BlankFluid(String fluidName) {
        super(fluidName);
        this.setIcons(BlockFluid.StillIcon, BlockFluid.StillIcon);
    }
}