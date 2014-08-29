package modmuss50.mods.lib.api;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * * @package com.tattyseal.zaet.api
 * * This code was made by tattyseal on the 02/07/14 for Zaet
 * *
 */
public class ColoredItemBlock extends ItemBlock {
    public ColoredItemBlock(Block block) {
        super(block);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        ColoredBlock block = (ColoredBlock) Block.getBlockById(ItemBlock.getIdFromItem(stack.getItem()));
        return Colors.nameColors[stack.getItemDamage()] + " " + block.name;
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        ColoredInopaqueBlock block = (ColoredInopaqueBlock) Block.getBlockById(ItemBlock.getIdFromItem(stack.getItem()));
        return Colors.nameColors[stack.getItemDamage()] + "." + block.name;
    }
}
