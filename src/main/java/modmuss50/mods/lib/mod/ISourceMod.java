package modmuss50.mods.lib.mod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import modmuss50.mods.core.client.BaseModGui;

public interface ISourceMod {

	@SideOnly(Side.CLIENT)
	public BaseModGui settingsScreen();

	public String modId();

	public String modName();

	public String modVersion();

	public String recomenedMinecraftVeriosion();

}
