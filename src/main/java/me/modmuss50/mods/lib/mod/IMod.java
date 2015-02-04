/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.lib.mod;


import me.modmuss50.mods.core.client.BaseModGui;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IMod {

	@SideOnly(Side.CLIENT)
	public BaseModGui settingsScreen();

	public String modId();

	public String modName();

	public String modVersion();

	public String recomenedMinecraftVeriosion();

}
