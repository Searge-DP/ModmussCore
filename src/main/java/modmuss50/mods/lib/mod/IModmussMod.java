package modmuss50.mods.lib.mod;

import modmuss50.mods.core.client.BaseModGui;

public interface IModmussMod {

	public BaseModGui settingsScreen();

    public String modId();

    public String modName();

    public String modVersion();

}
