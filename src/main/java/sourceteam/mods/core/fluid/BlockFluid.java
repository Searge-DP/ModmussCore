package sourceteam.mods.core.fluid;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.BlockFluidClassic;

public class BlockFluid extends BlockFluidClassic {


    
    public static IIcon StillIcon;
    public BlockFluid(String fluidName) {
        super(new BlankFluid("BlankFluid"), Material.water);

    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        StillIcon = iconRegister.registerIcon("sourcecore:fluid");
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return StillIcon;
    }


}
