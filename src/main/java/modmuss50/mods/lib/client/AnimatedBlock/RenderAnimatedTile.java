package modmuss50.mods.lib.client.AnimatedBlock;

import modmuss50.mods.core.Core;
import modmuss50.mods.lib.client.IColour;
import modmuss50.mods.lib.client.IRGBColour;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.opengl.GL11;

public class RenderAnimatedTile extends TileEntitySpecialRenderer {

	public static void setGLColorFromInt(int color) {
		float red = (float) (color >> 16 & 255) / 255.0F;
		float green = (float) (color >> 8 & 255) / 255.0F;
		float blue = (float) (color & 255) / 255.0F;
		GL11.glColor4f(red, green, blue, 1.0F);
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
		FluidStack liquid = new FluidStack(Core.blankFluid, 100);
		if (tileentity instanceof IHighRes && ((IHighRes) tileentity).useHighRes()) {
			liquid = new FluidStack(Core.blankFluidH, 100);
		}
		int color = 0xFFFFFF;
		if (liquid == null || liquid.amount <= 0) {
			return;
		}
		int[] displayList = FluidRenderer.getFluidDisplayLists(liquid, tileentity.getWorldObj(), false);
		if (displayList == null) {
			return;
		}
		GL11.glPushMatrix();
		GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		bindTexture(((IColour) tileentity).isAnimated() ? FluidRenderer.getFluidSheet(liquid) : new ResourceLocation("sourcecore", "textures/blocks/still_low.png"));

		if (tileentity instanceof IHighRes && !((IColour) tileentity).isAnimated()) {
			if (((IHighRes) tileentity).useHighRes())
				bindTexture(new ResourceLocation("sourcecore", "textures/blocks/still.png"));
		}

		//   setGLColorFromInt(0x666666);
		if (tileentity instanceof IColour && ((IColour) tileentity).colour() != 0) {
			setGLColorFromInt(((IColour) tileentity).colour());
		}

		if (tileentity instanceof IRGBColour) {
			GL11.glColor4f((float) ((IRGBColour) tileentity).Cred() / 255, (float) ((IRGBColour) tileentity).Cgreen() / 255, (float) ((IRGBColour) tileentity).Cblue() / 255, 1.0F);
		}

		GL11.glTranslatef((float) x + 0.000001F, (float) y + 0.51F, (float) z + 0.000001F);
		GL11.glScalef(0.999999F, 0.9F, 0.999999F);
		GL11.glTranslatef(0, -0.5F, 0);

		GL11.glCallList(displayList[(int) ((float) 100 / (float) (100) * (FluidRenderer.DISPLAY_STAGES - 1))]);
		GL11.glPopAttrib();
		GL11.glPopMatrix();
	}

}
