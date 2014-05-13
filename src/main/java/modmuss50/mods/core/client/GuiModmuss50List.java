package modmuss50.mods.core.client;

import com.google.common.base.Strings;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.IModGuiFactory;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import modmuss50.mods.core.mod.ModRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.Level;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA. User: Mark Date: 05/04/14 Time: 08:54
 */
public class GuiModmuss50List extends GuiScreen {
	private GuiScreen mainMenu;
	private GuiSlotModListCore modList;
	private int selected = -1;
	private ModContainer selectedMod;
	private int listWidth;
	private ArrayList<ModContainer> mods;
	private GuiButton configModButton;
	private GuiButton disableModButton;
	private ResourceLocation cachedLogo;
	private Dimension cachedLogoDimensions;
	private ArrayList<String> modToAdd;
	private boolean hasInted = false;
	/**
	 * @param mainMenu
	 */
	public GuiModmuss50List(GuiScreen mainMenu) {

        for (int i = 0; i < ModRegistry.mods.size(); i++) {
          addMod(ModRegistry.mods.get(i).modId());
        }


//		addMod("transcraft");
//		addMod("Mod50-Core");
//		addMod("network");

		this.mainMenu = mainMenu;
		this.mods = new ArrayList<ModContainer>();
		FMLClientHandler.instance().addSpecialModEntries(mods);
		for (ModContainer mod : Loader.instance().getModList()) {
			if (mod.getMetadata() != null
					&& mod.getMetadata().parentMod == null
					&& !Strings.isNullOrEmpty(mod.getMetadata().parent)) {

				String parentMod = mod.getMetadata().parent;
				ModContainer parentContainer = Loader.instance()
						.getIndexedModList().get(parentMod);
				if (parentContainer != null) {

					mod.getMetadata().parentMod = parentContainer;
					parentContainer.getMetadata().childMods.add(mod);
					continue;
				}

			} else if (mod.getMetadata() != null
					&& mod.getMetadata().parentMod != null) {
				continue;
			}

			String packageName = mod.getClass().getClass().getPackage()
					.getName();


			// this.modToAdd=new ArrayList<String>();
			if (modToAdd.contains(mod.getMetadata().modId)) {
				// System.out.println("ADDING " +
				// mod.getMetadata().getClass().getName());
				mods.add(mod);
			}
		}
	}

	/**
	 * Adds the buttons (and other controls) to the screen in question.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void initGui() {

		for (ModContainer mod : mods) {
			listWidth = Math.max(listWidth,
					getFontRenderer().getStringWidth(mod.getName()) + 10);
			listWidth = Math.max(listWidth,
					getFontRenderer().getStringWidth(mod.getVersion()) + 10);
		}
		listWidth = Math.min(listWidth, 150);
		this.buttonList.add(new GuiButton(6, this.width / 2 - 75,
				this.height - 38, I18n.format("gui.done")));
		configModButton = new GuiButton(20, 10, this.height - 60,
				this.listWidth, 20, "Config");
		disableModButton = new GuiButton(21, 10, this.height - 38,
				this.listWidth, 20, "Disable");
		this.buttonList.add(configModButton);
		this.buttonList.add(disableModButton);
		this.modList = new GuiSlotModListCore(this, mods, listWidth);
		this.modList.registerScrollButtons(this.buttonList, 7, 8);
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			switch (button.id) {
				case 6 :
					this.mc.displayGuiScreen(this.mainMenu);
					return;
				case 20 :
					try {


                        for (int i = 0; i < ModRegistry.mods.size(); i++) {
                            if(ModRegistry.mods.get(i).modId().contains(selectedMod.getModId()))
                            {
                                if(ModRegistry.mods.get(i).settingsScreen() != null)
                                {
                                    BaseModGui newScreen = (ModRegistry.mods.get(i).settingsScreen());
                                    newScreen.setParent(this);
                                    this.mc.displayGuiScreen(newScreen);
                                }
                            }
                        }

					} catch (Exception e) {
						FMLLog.log(
								Level.ERROR,
								e,
								"There was a critical issue trying to build the config GUI for %s",
								selectedMod.getModId());
					}
					return;
			}
		}
		super.actionPerformed(button);
	}

	public int drawLine(String line, int offset, int shifty) {
		this.fontRendererObj.drawString(line, offset, shifty, 0xd7edea);
		return shifty + 10;
	}

	/**
	 * Draws the screen and all the components in it.
	 */
	@Override
	public void drawScreen(int p_571_1_, int p_571_2_, float p_571_3_) {
		this.modList.drawScreen(p_571_1_, p_571_2_, p_571_3_);
		this.drawCenteredString(this.fontRendererObj, "Modmuss50 Settings",
				this.width / 2, 16, 0xFFFFFF);
		int offset = this.listWidth + 20;
		if (selectedMod != null) {
			GL11.glEnable(GL11.GL_BLEND);

			configModButton.visible = true;
			disableModButton.visible = true;
			disableModButton.packedFGColour = 0xFF3377;
			configModButton.enabled = false;
			int shifty = 35;

			this.fontRendererObj.drawStringWithShadow(
					selectedMod.getMetadata().name, offset, shifty, 0xFFFFFF);
			shifty += 12;

			shifty = drawLine(
					String.format("Version: %s",
							selectedMod.getDisplayVersion(),
							selectedMod.getVersion()), offset, shifty);

			disableModButton.packedFGColour = 0;
			disableModButton.visible = false;
			disableModButton.enabled = false;

			IModGuiFactory guiFactory = FMLClientHandler.instance()
					.getGuiFactoryFor(selectedMod);



            configModButton.visible = false;
            disableModButton.visible = false;

            for (int i = 0; i < ModRegistry.mods.size(); i++) {

                if(ModRegistry.mods.get(i).modId().contains(selectedMod.getModId()))
                {
                    if(ModRegistry.mods.get(i).settingsScreen() != null)
                    {
                        configModButton.visible = true;
                        configModButton.enabled = true;
                    }
                    else
                    {
                        configModButton.visible = false;
                        disableModButton.visible = false;
                    }
                }
            }



			GL11.glDisable(GL11.GL_BLEND);
		} else {
			configModButton.visible = false;
			disableModButton.visible = false;
		}
		super.drawScreen(p_571_1_, p_571_2_, p_571_3_);
	}

	Minecraft getMinecraftInstance() {
		/**
		 * Reference to the Minecraft object.
		 */
		return mc;
	}

	FontRenderer getFontRenderer() {
		/**
		 * The FontRenderer used by GuiScreen
		 */
		return fontRendererObj;
	}

	/**
	 * @param var1
	 */
	public void selectModIndex(int var1) {
		this.selected = var1;
		if (var1 >= 0 && var1 <= mods.size()) {
			this.selectedMod = mods.get(selected);
		} else {
			this.selectedMod = null;
		}
		cachedLogo = null;
	}

	public boolean modIndexSelected(int var1) {
		return var1 == selected;
	}

	public void addMod(String modid) {
		if (!hasInted) {
			this.modToAdd = new ArrayList<String>();
			hasInted = true;
		}

		modToAdd.add(modid);

	}

}
