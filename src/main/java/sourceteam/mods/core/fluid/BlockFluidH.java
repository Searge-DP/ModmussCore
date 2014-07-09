package sourceteam.mods.core.fluid;

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
