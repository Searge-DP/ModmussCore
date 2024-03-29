/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.core.client;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class BaseModGui extends GuiScreen {
	public GuiScreen parent;

	public BaseModGui() {

	}

	@SuppressWarnings("unchecked")
	@Override
	public void initGui() {
		this.buttonList.add(new GuiButton(1, this.width / 2 - 75, this.height - 38, I18n.format("gui.done")));
	}

	@Override
	public void actionPerformed(GuiButton button) {
		if (button.enabled && button.id == 1) {
			FMLClientHandler.instance().showGuiScreen(parent);
		}
	}

	@Override
	public void drawScreen(int x, int y, float f) {
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRendererObj, "config screen", this.width / 2, 40, 0xFFFFFF);
		drawScreen2(x, y, f);
		super.drawScreen(x, y, f);
	}

	public void drawScreen2(int p_73863_1_, int p_73863_2_, float p_73863_3_) {

	}

	public void setParent(GuiScreen parent) {
		this.parent = parent;
	}
}
