package sourceteam.mods.lib.api;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;

/**
 * * @package sourceteam.mods.lib.api
 * * @author tattyseal
 * <p/>
 * THIS IS INTERNAL DO NOT USE
 * *
 */
public class ColoredNetworkBlock extends ColoredBlock {
    public ColoredNetworkBlock(Material material, String texture) {
        super("", material, texture, "", true);
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
        list.add(new ItemStack(item, 1));
    }

    @Override
    public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z) {
        return 0xFFFFFF;
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        super.registerBlockIcons(iconRegister);
    }

    @Override
    public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side) {
        return icons[0];
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return icons[0];
    }

}
