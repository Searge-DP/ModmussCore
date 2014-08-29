package modmuss50.mods.lib.api;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

/**
 * * @package com.tattyseal.zaet.api
 * * This code was made by tattyseal on the 29/06/14 for Zaet
 * *
 */
public class ColoredFood extends ItemFood {
    public String name;

    public IIcon low;
    public IIcon high;

    public String lowString;
    public String highString;

    public ColoredFood(String name, String lowTexture, String highTexture, int heal, float saturation, boolean wolf) {
        super(heal, saturation, wolf);
        this.name = name;
        setHasSubtypes(true);
        lowString = lowTexture;
        highString = highTexture;
    }

    @Override
    public int getColorFromItemStack(ItemStack itemstack, int meta) {
        return Colors.itemColors[itemstack.getItemDamage()];
    }

    @Override
    public String getItemStackDisplayName(ItemStack itemstack) {
        return Colors.nameColors[itemstack.getItemDamage()] + " " + name;
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int meta = 0; meta < 16; meta++) {
            list.add(new ItemStack(item, 1, meta));
        }
    }


    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        if (isHD()) return high;
        return low;
    }

    @Override
    public void registerIcons(IIconRegister iIconRegister) {
        low = iIconRegister.registerIcon(lowString);
        high = iIconRegister.registerIcon(highString);
    }

    @Override
    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
        if (isHD()) return high;
        return low;
    }

    @Override
    public IIcon getIconFromDamage(int metadata) {
        if (isHD()) return high;
        return low;
    }

    public boolean isHD() {
        /******
         * TODO FIX THIS
         */

        return true;
    }
}
