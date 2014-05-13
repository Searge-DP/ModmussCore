package modmuss50.mods.core.client;

import cpw.mods.fml.client.GuiScrollingList;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.LoaderState;
import cpw.mods.fml.common.ModContainer;
import net.minecraft.client.renderer.Tessellator;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA. User: Mark Date: 05/04/14 Time: 11:47
 */
public class GuiSlotModListCore extends GuiScrollingList {
	private GuiModmuss50List parent;
	private ArrayList<ModContainer> mods;

	public GuiSlotModListCore(GuiModmuss50List parent,
			ArrayList<ModContainer> mods, int listWidth) {
		super(parent.getMinecraftInstance(), listWidth, parent.height, 32,
				parent.height - 66 + 4, 10, 35);
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
	protected void drawSlot(int listIndex, int var2, int var3, int var4,
			Tessellator var5) {
		ModContainer mc = mods.get(listIndex);
		if (Loader.instance().getModState(mc) == LoaderState.ModState.DISABLED) {
			this.parent.getFontRenderer().drawString(
					this.parent.getFontRenderer().trimStringToWidth(
							mc.getName(), listWidth - 10), this.left + 3,
					var3 + 2, 0xFF2222);
			this.parent.getFontRenderer().drawString(
					this.parent.getFontRenderer().trimStringToWidth(
							mc.getDisplayVersion(), listWidth - 10),
					this.left + 3, var3 + 12, 0xFF2222);
			this.parent.getFontRenderer()
					.drawString(
                            this.parent.getFontRenderer().trimStringToWidth(
                                    "DISABLED", listWidth - 10), this.left + 3,
                            var3 + 22, 0xFF2222);
		} else {
			this.parent.getFontRenderer().drawString(
					this.parent.getFontRenderer().trimStringToWidth(
							mc.getName(), listWidth - 10), this.left + 3,
					var3 + 2, 0xFFFFFF);
			this.parent.getFontRenderer().drawString(
					this.parent.getFontRenderer().trimStringToWidth(
							mc.getDisplayVersion(), listWidth - 10),
					this.left + 3, var3 + 12, 0xCCCCCC);
			this.parent.getFontRenderer().drawString(
					this.parent.getFontRenderer().trimStringToWidth(
							mc.getMetadata() != null
									? mc.getMetadata().getChildModCountString()
									: "Metadata not found", listWidth - 10),
					this.left + 3, var3 + 22, 0xCCCCCC);
		}
	}

}
