package modmuss50.mods.lib.mod;

import modmuss50.mods.core.client.BaseModGui;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IModmussMod {

	@SideOnly(Side.CLIENT)
	public BaseModGui settingsScreen();

	public String modId();

	public String modName();

	public String modVersion();

}
