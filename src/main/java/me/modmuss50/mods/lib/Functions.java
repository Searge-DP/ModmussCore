/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.lib;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

public class Functions {

	public static void showMessageInChat(EntityPlayer player, String message) {
		player.addChatMessage(new ChatComponentText(message));

	}

	public static List mergeList(List l1, List l2) {
		for (Object object : l1) {
			if (!l2.contains(object)) {
				l2.add(object);
			}
		}
		return l2;
	}

	public static int getIntDirFromDirection(EnumFacing dir) {
		switch (dir) {
			case DOWN:
				return 0;
			case EAST:
				return 5;
			case NORTH:
				return 2;
			case SOUTH:
				return 3;
			case UP:
				return 1;
			case WEST:
				return 4;
			default:
				return 0;
		}
	}

	public static EnumFacing getDirectionFromInt(int dir) {
		int metaDataToSet = 0;
		switch (dir) {
			case 0:
				metaDataToSet = 2;
				break;
			case 1:
				metaDataToSet = 4;
				break;
			case 2:
				metaDataToSet = 3;
				break;
			case 3:
				metaDataToSet = 5;
				break;
		}
		return EnumFacing.getFront(metaDataToSet);
	}

	public static boolean isInString(String oreName, String[] list) {
		boolean ret = false;
		for (int i = 0; i < list.length; i++) {
			ret = ret || (oreName.substring(0, list[i].length()).equals(list[i]));
		}
		return ret;
	}

	public static String getPrefixName(String oreDictName) {
		// TODO: Fix this function up. It looks ugly
		String[] prefix = {"ingot"};
		if (isInString(oreDictName, prefix)) {
			return "ingot";
		}

		prefix[0] = "ore";
		if (isInString(oreDictName, prefix)) {
			return "ore";
		}

		if (oreDictName.equals("netherquartz")) {
			return "Quartz";
		} else {
			return "ERROR";
		}
	}

	public static String getMetalName(String oreDictName) {
		String[] prefix = {"ingot"};
		if (isInString(oreDictName, prefix)) {
			return oreDictName.substring(prefix[0].length());
		}

		prefix[0] = "ore";
		if (isInString(oreDictName, prefix)) {
			return oreDictName.substring(prefix[0].length());
		}

		if (oreDictName.equals("netherquartz")) {
			return "Quartz";
		} else {
			return "ERROR";
		}
	}

	public static ItemStack getIngot(String ingotName) {
		List<ItemStack> targetStackL = OreDictionary.getOres(ingotName);
		if (targetStackL.size() > 0) {
			return targetStackL.get(0);
		}
		return null;
	}

}
