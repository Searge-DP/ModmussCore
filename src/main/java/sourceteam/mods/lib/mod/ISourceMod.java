package sourceteam.mods.lib.mod;

import sourceteam.mods.core.client.BaseModGui;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface ISourceMod {

	@SideOnly(Side.CLIENT)
	public BaseModGui settingsScreen();

	public String modId();

	public String modName();

	public String modVersion();

	public String recomenedMinecraftVeriosion();

}
