package modmuss50.mods.lib.api;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

/**
 * * @package com.tattyseal.zaet.api
 * * This code was made by tattyseal on the 02/07/14 for Zaet
 * *
 */
public class ColoredSidedBlock extends ColoredBlock {
    public String[] lowTextures;
    public String[] highTextures;

    public String name;

    public IIcon[] lowIcons;
    public IIcon[] highIcons;

    public ColoredSidedBlock(String name, Material material, String[] highString, String[] lowString, boolean source) {
        super(name, material, "", "", source);
        lowTextures = lowString;
        highTextures = highString;
        setBlockName(name.replace(" ", "") + ".zaet");

        this.name = name;

        lowIcons = new IIcon[6];
        highIcons = new IIcon[6];
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        super.registerBlockIcons(iconRegister);

        for (int i = 0; i < 6; i++) {
            lowIcons[i] = iconRegister.registerIcon(lowTextures[i]);
        }

        for (int i = 0; i < 6; i++) {
            highIcons[i] = iconRegister.registerIcon(highTextures[i]);
        }
    }

    @Override
    public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side) {
        if (isHD()) {
            return highIcons[side];
        }

        return lowIcons[side];
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        if (isHD()) {
            return highIcons[side];
        }

        return lowIcons[side];
    }

    @Override
    public boolean isHD()
    {
        return super.isHD();
    }
}
