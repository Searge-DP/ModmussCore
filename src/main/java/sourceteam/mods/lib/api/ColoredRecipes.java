package sourceteam.mods.lib.api;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * * @package com.tattyseal.zaet.api
 * * @author tattyseal
 * *
 */
public class ColoredRecipes
{
    public static void addZaetRecipe(Item item, ItemStack vanilla)
    {
        for(int i = 0; i < 16; i++)
        {
            GameRegistry.addShapedRecipe(new ItemStack(item, 8, i), "XXX", "XIX", "XXX", 'X', new ItemStack(vanilla.getItem(), 1, vanilla.getItemDamage()), 'I', new ItemStack(Items.dye, 1, i));
        }
    }

    public static void addMetadataRecipe(Item item, Item meta, Item metadata)
    {
        for(int i = 0; i < 16; i++)
        {
            GameRegistry.addShapedRecipe(new ItemStack(item, 8, i), "XXX", "XIX", "XXX", 'X', new ItemStack(meta, 1, i), 'I', new ItemStack(metadata, 1, i));
        }
    }
}
