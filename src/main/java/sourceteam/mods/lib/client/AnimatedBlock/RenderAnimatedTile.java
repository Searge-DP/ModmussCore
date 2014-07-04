package sourceteam.mods.lib.client.AnimatedBlock;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.opengl.GL11;
import sourceteam.mods.core.Core;
import sourceteam.mods.core.fluid.BlockFluid;
import sourceteam.mods.lib.client.IColour;

public class RenderAnimatedTile extends TileEntitySpecialRenderer {


    public static final Fluid fluid = new Fluid("fluidBlank") {
        @Override
        public String getLocalizedName() {
            return StatCollector.translateToLocal("tile.fluidBlank.name");
        }
    }.setBlock(Blocks.water).setUnlocalizedName(Blocks.water.getUnlocalizedName()).setIcons(BlockFluid.StillIcon);

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
        FluidStack liquid = new FluidStack(Core.blankFluid, 100);
     //   liquid = new FluidStack(fluid, 100);
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

        bindTexture(FluidRenderer.getFluidSheet(liquid));
       // bindTexture(new ResourceLocation("sourcecore", "textures/blocks/fluid.png"));

        if(tileentity instanceof IColour){
            GL11.glColor4f((float)((IColour)tileentity).red(), (float)((IColour)tileentity).green(), (float)((IColour)tileentity).blue(), 1.0F);
        }



        GL11.glTranslatef((float) x + 0.001F, (float) y + 0.5F, (float) z + 0.001F);
        GL11.glScalef(0.999F, 0.999F, 0.999F);
        GL11.glTranslatef(0, -0.5F, 0);

        GL11.glCallList(displayList[(int) ((float) 100 / (float) (100) * (FluidRenderer.DISPLAY_STAGES - 1))]);

        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    public static void setGLColorFromInt(int color) {
        float red = (float) (color >> 16 & 255) / 255.0F;
        float green = (float) (color >> 8 & 255) / 255.0F;
        float blue = (float) (color & 255) / 255.0F;
        GL11.glColor4f(red, green, blue, 1.0F);
    }

}
