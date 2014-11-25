package modmuss50.mods.core.client;

import cpw.mods.fml.client.GuiScrollingList;
import cpw.mods.fml.common.ModContainer;
import modmuss50.mods.core.mod.ModRegistry;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.common.MinecraftForge;

import java.util.ArrayList;

public class GuiSlotModListCore extends GuiScrollingList {
	private GuSourceList parent;
	private ArrayList<ModContainer> mods;

	public GuiSlotModListCore(GuSourceList parent, ArrayList<ModContainer> mods, int listWidth) {
		super(parent.getMinecraftInstance(), listWidth, parent.height, 32, parent.height - 66 + 4, 10, 35);
		this.parent = parent;
		this.mods = mods;
	}

	@Override
	protected int getSize() {
		return mods.size();
	}

	@Override
	protected void elementClicked(int var1, boolean var2) {
		this.parent.selectModIndex(var1);
	}

	@Override
	protected boolean isSelected(int var1) {
		return this.parent.modIndexSelected(var1);
	}

	@Override
	protected void drawBackground() {
		this.parent.drawDefaultBackground();
	}

	@Override
	protected int getContentHeight() {
		return (this.getSize()) * 35 + 1;
	}

	@Override
	protected void drawSlot(int listIndex, int var2, int var3, int var4, Tessellator var5) {
		ModContainer mc = mods.get(listIndex);
		this.parent.getFontRenderer().drawString(this.parent.getFontRenderer().trimStringToWidth(mc.getName(), listWidth - 10), this.left + 3, var3 + 2, 0xFFFFFF);
		this.parent.getFontRenderer().drawString(this.parent.getFontRenderer().trimStringToWidth(mc.getDisplayVersion(), listWidth - 10), this.left + 3, var3 + 12, 0xCCCCCC);
		this.parent.getFontRenderer().drawString(this.parent.getFontRenderer().trimStringToWidth("MC Version: " + getModCompiledVersion(mc.getModId()), listWidth - 10), this.left + 3, var3 + 22, getModVersionColour(mc.getModId()));
	}

	public String getModCompiledVersion(String modid) {
		for (int i = 0; i < ModRegistry.mods.size(); i++) {
			if (ModRegistry.mods.get(i).modId().equals(modid)) {
				return ModRegistry.mods.get(i).recomenedMinecraftVeriosion();
			}
		}
		return "ERROR";
	}

	public int getModVersionColour(String modid) {
		for (int i = 0; i < ModRegistry.mods.size(); i++) {
			if (ModRegistry.mods.get(i).modId().equals(modid)) {
				if (!ModRegistry.mods.get(i).recomenedMinecraftVeriosion().equals(MinecraftForge.MC_VERSION)) {
					return 0xf40000;
				} else {
					return 0x3adf00;
				}
			}
		}
		return 0xCCCCCC;
	}


}
