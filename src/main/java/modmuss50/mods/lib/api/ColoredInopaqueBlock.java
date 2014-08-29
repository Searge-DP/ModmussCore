package modmuss50.mods.lib.api;

import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;

/**
 * * @package com.tattyseal.zaet.api
 * * This code was made by tattyseal on the 02/07/14 for Zaet
 * *
 */
public class ColoredInopaqueBlock extends ColoredBlock {
    public String lowTexture;
    public String highTexture;

    public String name;

    public IIcon[] icons;

    public ColoredInopaqueBlock(String name, Material material, String lowString, String highString) {
        super(name, material, lowString, highString, false);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }
}
