/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.lib;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;

public class GeneralHelper {
	public static boolean isHostileEntity(EntityLivingBase entity) {

		if (entity instanceof IMob)
			return true;
		else
			return false;
	}
}
