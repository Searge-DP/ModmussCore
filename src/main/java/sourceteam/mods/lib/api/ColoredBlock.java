package sourceteam.mods.lib.api;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import sourceteam.mods.core.client.ClientInit;

import java.util.List;

/**
 * * @package com.tattyseal.zaet.api
 * * This code was made by tattyseal on the 02/07/14 for Zaet
 * *
 */
public class ColoredBlock extends Block
{
    public String lowTexture;
    public String highTexture;

    public IIcon liquid;
    public IIcon liquid_low;

    public boolean source;

    public String name;

    public IIcon[] icons;

    public ColoredBlock(String name, Material material, String lowString, String highString, boolean source)
    {
        super(material);
        lowTexture = lowString;
        highTexture = highString;
        setBlockName(name.replace(" ", "") + ".zaet");

       // if(source) MinecraftForgeClient.registerItemRenderer(ItemBlock.getItemFromBlock(this), new ZaetBlockRenderer());

        this.source = source;

        setHardness(1f);

        this.name = name;

        icons = new IIcon[2];
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
    {
        for(int i = 0; i < 16; i++)
        {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public int getRenderColor(int metadata)
    {
        return isSourceBlock() ? 0x787878 : Colors.itemColors[metadata];
    }

    @Override
    public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z)
    {
        return isSourceBlock() ? 0x787878 : Colors.itemColors[blockAccess.getBlockMetadata(x, y, z)];
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
        return new ItemStack(world.getBlock(x, y, z), 1, world.getBlockMetadata(x, y, z));
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        icons[0] = iconRegister.registerIcon(lowTexture);
        icons[1] = iconRegister.registerIcon(highTexture);

        liquid = iconRegister.registerIcon("sourcecore:fluid");
        liquid_low = iconRegister.registerIcon("sourcecore:fluidh");
    }

    @Override
    public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side)
    {
        if(isHD())
        {
            return icons[1];
        }

        return icons[0];
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        if(isHD())
        {
            return icons[1];
        }

        return icons[0];
    }


    public boolean isHD()
    {
        /******
         ** TODO THIS IS TEMPORARY AND NEEDS FIXING
         */

        return true;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float j, float k, float l)
    {
        ItemStack item = player.getHeldItem();

        if(item.getItem() == Items.dye)
        {
            world.setBlockMetadataWithNotify(x, y, z, item.getItemDamage(), 2);
        }

        return false;
    }

    @Override
    public int damageDropped(int metadata)
    {
        return metadata;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return ClientInit.render;
    }

    public boolean isSourceBlock()
    {
        return source;
    }
}
