package modmuss50.mods.core.mod;

import modmuss50.mods.core.client.BaseModGui;
import modmuss50.mods.lib.mod.IModmussMod;
import net.minecraftforge.common.MinecraftForge;

public class ForgeIMod implements IModmussMod {
	@Override
	public BaseModGui settingsScreen() {
		return null;
	}

	@Override
	public String modId() {
		return "Forge";
	}

	@Override
	public String modName() {
		return "Forge";
	}

	@Override
	public String modVersion() {
		return MinecraftForge.getBrandingVersion();
	}
}
