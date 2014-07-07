package sourceteam.mods.lib.api.client;

import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockHopper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntityRendererChestHelper;
import net.minecraft.src.FMLRenderAccessLibrary;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import sourceteam.mods.core.client.ClientInit;
import sourceteam.mods.lib.api.ColoredBlock;
import sourceteam.mods.lib.api.Colors;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

/**
 * * @package com.tattyseal.zaet.api.client
 * * @author tattyseal
 * *
 */
public class RenderAnimatedBlock implements ISimpleBlockRenderingHandler
{

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
    {
        ColoredBlock zb = (ColoredBlock) block;

        IIcon fluid;

        if(!zb.isHD())
        {
            fluid = zb.liquid;
        }
        else
        {
            fluid = zb.liquid_low;
        }

        float[] color = getRGBFromHex(Colors.itemColors[metadata]);

        renderer.setOverrideBlockTexture(fluid);

        renderBlockAsItem(Blocks.stone, metadata, 1f);

        renderer.clearOverrideBlockTexture();

        renderer.setOverrideBlockTexture(zb.isHD() ? zb.icons[1] : zb.icons[0]);

        renderBlockAsItem(Blocks.stone, metadata, 1f);
        renderer.clearOverrideBlockTexture();
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
        ColoredBlock zb = (ColoredBlock) block;

        IIcon fluid;

        if(!zb.isHD())
        {
            fluid = zb.liquid;
        }
        else
        {
            fluid = zb.liquid_low;
        }

        float[] color = getRGBFromHex(Colors.itemColors[world.getBlockMetadata(x, y, z)]);

        renderer.setOverrideBlockTexture(fluid);

        renderer.renderStandardBlockWithColorMultiplier(Blocks.stone, x, y, z, color[0], color[1], color[2]);

        renderer.clearOverrideBlockTexture();

        renderer.setOverrideBlockTexture(zb.isHD() ? zb.icons[1] : zb.icons[0]);

        renderer.renderStandardBlockWithColorMultiplier(Blocks.stone, x, y, z, color[0] - 2, color[1] - 2, color[2] - 2);
        renderer.clearOverrideBlockTexture();

        return true;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId)
    {
        return true;
    }

    @Override
    public int getRenderId()
    {
        return ClientInit.render;
    }

    public static float[] getRGBFromHex(int color)
    {
        float red = (float) (color >> 16 & 255) / 255.0F;
        float green = (float) (color >> 8 & 255) / 255.0F;
        float blue = (float) (color & 255) / 255.0F;

        return new float[] {red, green, blue};
    }

    /**
     * Is called to render the image of a block on an inventory, as a held item, or as a an item on the ground
     */
    public void renderBlockAsItem(Block p_147800_1_, int p_147800_2_, float p_147800_3_)
    {
        Tessellator tessellator = Tessellator.instance;
        boolean flag = p_147800_1_ == Blocks.grass;

        if (p_147800_1_ == Blocks.dispenser || p_147800_1_ == Blocks.dropper || p_147800_1_ == Blocks.furnace)
        {
            p_147800_2_ = 3;
        }

        int j;
        float f1;
        float f2;
        float f3;

        if (true)//RenderBlocks.getInstance().useInventoryTint)
        {
            j = Colors.itemColors[p_147800_2_];

            if (flag)
            {
                j = 16777215;
            }

            f1 = (float)(j >> 16 & 255) / 255.0F;
            f2 = (float)(j >> 8 & 255) / 255.0F;
            f3 = (float)(j & 255) / 255.0F;
            GL11.glColor4f(f1 * p_147800_3_, f2 * p_147800_3_, f3 * p_147800_3_, 1.0F);
        }

        j = p_147800_1_.getRenderType();
        RenderBlocks.getInstance().setRenderBoundsFromBlock(p_147800_1_);
        int k;

        if (j != 0 && j != 31 && j != 39 && j != 16 && j != 26)
        {
            if (j == 1)
            {
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, -1.0F, 0.0F);
                IIcon iicon = RenderBlocks.getInstance().getBlockIconFromSideAndMetadata(p_147800_1_, 0, p_147800_2_);
                RenderBlocks.getInstance().drawCrossedSquares(iicon, -0.5D, -0.5D, -0.5D, 1.0F);
                tessellator.draw();
            }
            else if (j == 19)
            {
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, -1.0F, 0.0F);
                p_147800_1_.setBlockBoundsForItemRender();
                RenderBlocks.getInstance().renderBlockStemSmall(p_147800_1_, p_147800_2_, RenderBlocks.getInstance().renderMaxY, -0.5D, -0.5D, -0.5D);
                tessellator.draw();
            }
            else if (j == 23)
            {
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, -1.0F, 0.0F);
                p_147800_1_.setBlockBoundsForItemRender();
                tessellator.draw();
            }
            else if (j == 13)
            {
                p_147800_1_.setBlockBoundsForItemRender();
                GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
                f1 = 0.0625F;
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, -1.0F, 0.0F);
                RenderBlocks.getInstance().renderFaceYNeg(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSide(p_147800_1_, 0));
                tessellator.draw();
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, 1.0F, 0.0F);
                RenderBlocks.getInstance().renderFaceYPos(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSide(p_147800_1_, 1));
                tessellator.draw();
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, 0.0F, -1.0F);
                tessellator.addTranslation(0.0F, 0.0F, f1);
                RenderBlocks.getInstance().renderFaceZNeg(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSide(p_147800_1_, 2));
                tessellator.addTranslation(0.0F, 0.0F, -f1);
                tessellator.draw();
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, 0.0F, 1.0F);
                tessellator.addTranslation(0.0F, 0.0F, -f1);
                RenderBlocks.getInstance().renderFaceZPos(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSide(p_147800_1_, 3));
                tessellator.addTranslation(0.0F, 0.0F, f1);
                tessellator.draw();
                tessellator.startDrawingQuads();
                tessellator.setNormal(-1.0F, 0.0F, 0.0F);
                tessellator.addTranslation(f1, 0.0F, 0.0F);
                RenderBlocks.getInstance().renderFaceXNeg(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSide(p_147800_1_, 4));
                tessellator.addTranslation(-f1, 0.0F, 0.0F);
                tessellator.draw();
                tessellator.startDrawingQuads();
                tessellator.setNormal(1.0F, 0.0F, 0.0F);
                tessellator.addTranslation(-f1, 0.0F, 0.0F);
                RenderBlocks.getInstance().renderFaceXPos(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSide(p_147800_1_, 5));
                tessellator.addTranslation(f1, 0.0F, 0.0F);
                tessellator.draw();
                GL11.glTranslatef(0.5F, 0.5F, 0.5F);
            }
            else if (j == 22)
            {
                GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
                TileEntityRendererChestHelper.instance.renderChest(p_147800_1_, p_147800_2_, p_147800_3_);
                GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            }
            else if (j == 6)
            {
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, -1.0F, 0.0F);
                RenderBlocks.getInstance().renderBlockCropsImpl(p_147800_1_, p_147800_2_, -0.5D, -0.5D, -0.5D);
                tessellator.draw();
            }
            else if (j == 2)
            {
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, -1.0F, 0.0F);
                RenderBlocks.getInstance().renderTorchAtAngle(p_147800_1_, -0.5D, -0.5D, -0.5D, 0.0D, 0.0D, 0);
                tessellator.draw();
            }
            else if (j == 10)
            {
                for (k = 0; k < 2; ++k)
                {
                    if (k == 0)
                    {
                        RenderBlocks.getInstance().setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5D);
                    }

                    if (k == 1)
                    {
                        RenderBlocks.getInstance().setRenderBounds(0.0D, 0.0D, 0.5D, 1.0D, 0.5D, 1.0D);
                    }

                    GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, -1.0F, 0.0F);
                    RenderBlocks.getInstance().renderFaceYNeg(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSide(p_147800_1_, 0));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 1.0F, 0.0F);
                    RenderBlocks.getInstance().renderFaceYPos(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSide(p_147800_1_, 1));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 0.0F, -1.0F);
                    RenderBlocks.getInstance().renderFaceZNeg(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSide(p_147800_1_, 2));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 0.0F, 1.0F);
                    RenderBlocks.getInstance().renderFaceZPos(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSide(p_147800_1_, 3));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(-1.0F, 0.0F, 0.0F);
                    RenderBlocks.getInstance().renderFaceXNeg(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSide(p_147800_1_, 4));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(1.0F, 0.0F, 0.0F);
                    RenderBlocks.getInstance().renderFaceXPos(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSide(p_147800_1_, 5));
                    tessellator.draw();
                    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
                }
            }
            else if (j == 27)
            {
                k = 0;
                GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
                tessellator.startDrawingQuads();

                for (int l = 0; l < 8; ++l)
                {
                    byte b0 = 0;
                    byte b1 = 1;

                    if (l == 0)
                    {
                        b0 = 2;
                    }

                    if (l == 1)
                    {
                        b0 = 3;
                    }

                    if (l == 2)
                    {
                        b0 = 4;
                    }

                    if (l == 3)
                    {
                        b0 = 5;
                        b1 = 2;
                    }

                    if (l == 4)
                    {
                        b0 = 6;
                        b1 = 3;
                    }

                    if (l == 5)
                    {
                        b0 = 7;
                        b1 = 5;
                    }

                    if (l == 6)
                    {
                        b0 = 6;
                        b1 = 2;
                    }

                    if (l == 7)
                    {
                        b0 = 3;
                    }

                    float f5 = (float)b0 / 16.0F;
                    float f6 = 1.0F - (float)k / 16.0F;
                    float f7 = 1.0F - (float)(k + b1) / 16.0F;
                    k += b1;
                    RenderBlocks.getInstance().setRenderBounds((double)(0.5F - f5), (double)f7, (double)(0.5F - f5), (double)(0.5F + f5), (double)f6, (double)(0.5F + f5));
                    tessellator.setNormal(0.0F, -1.0F, 0.0F);
                    RenderBlocks.getInstance().renderFaceYNeg(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSide(p_147800_1_, 0));
                    tessellator.setNormal(0.0F, 1.0F, 0.0F);
                    RenderBlocks.getInstance().renderFaceYPos(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSide(p_147800_1_, 1));
                    tessellator.setNormal(0.0F, 0.0F, -1.0F);
                    RenderBlocks.getInstance().renderFaceZNeg(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSide(p_147800_1_, 2));
                    tessellator.setNormal(0.0F, 0.0F, 1.0F);
                    RenderBlocks.getInstance().renderFaceZPos(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSide(p_147800_1_, 3));
                    tessellator.setNormal(-1.0F, 0.0F, 0.0F);
                    RenderBlocks.getInstance().renderFaceXNeg(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSide(p_147800_1_, 4));
                    tessellator.setNormal(1.0F, 0.0F, 0.0F);
                    RenderBlocks.getInstance().renderFaceXPos(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSide(p_147800_1_, 5));
                }

                tessellator.draw();
                GL11.glTranslatef(0.5F, 0.5F, 0.5F);
                RenderBlocks.getInstance().setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
            }
            else if (j == 11)
            {
                for (k = 0; k < 4; ++k)
                {
                    f2 = 0.125F;

                    if (k == 0)
                    {
                        RenderBlocks.getInstance().setRenderBounds((double)(0.5F - f2), 0.0D, 0.0D, (double)(0.5F + f2), 1.0D, (double)(f2 * 2.0F));
                    }

                    if (k == 1)
                    {
                        RenderBlocks.getInstance().setRenderBounds((double)(0.5F - f2), 0.0D, (double)(1.0F - f2 * 2.0F), (double)(0.5F + f2), 1.0D, 1.0D);
                    }

                    f2 = 0.0625F;

                    if (k == 2)
                    {
                        RenderBlocks.getInstance().setRenderBounds((double)(0.5F - f2), (double)(1.0F - f2 * 3.0F), (double)(-f2 * 2.0F), (double)(0.5F + f2), (double)(1.0F - f2), (double)(1.0F + f2 * 2.0F));
                    }

                    if (k == 3)
                    {
                        RenderBlocks.getInstance().setRenderBounds((double)(0.5F - f2), (double)(0.5F - f2 * 3.0F), (double)(-f2 * 2.0F), (double)(0.5F + f2), (double)(0.5F - f2), (double)(1.0F + f2 * 2.0F));
                    }

                    GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, -1.0F, 0.0F);
                    RenderBlocks.getInstance().renderFaceYNeg(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSide(p_147800_1_, 0));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 1.0F, 0.0F);
                    RenderBlocks.getInstance().renderFaceYPos(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSide(p_147800_1_, 1));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 0.0F, -1.0F);
                    RenderBlocks.getInstance().renderFaceZNeg(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSide(p_147800_1_, 2));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 0.0F, 1.0F);
                    RenderBlocks.getInstance().renderFaceZPos(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSide(p_147800_1_, 3));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(-1.0F, 0.0F, 0.0F);
                    RenderBlocks.getInstance().renderFaceXNeg(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSide(p_147800_1_, 4));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(1.0F, 0.0F, 0.0F);
                    RenderBlocks.getInstance().renderFaceXPos(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSide(p_147800_1_, 5));
                    tessellator.draw();
                    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
                }

                RenderBlocks.getInstance().setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
            }
            else if (j == 21)
            {
                for (k = 0; k < 3; ++k)
                {
                    f2 = 0.0625F;

                    if (k == 0)
                    {
                        RenderBlocks.getInstance().setRenderBounds((double)(0.5F - f2), 0.30000001192092896D, 0.0D, (double)(0.5F + f2), 1.0D, (double)(f2 * 2.0F));
                    }

                    if (k == 1)
                    {
                        RenderBlocks.getInstance().setRenderBounds((double)(0.5F - f2), 0.30000001192092896D, (double)(1.0F - f2 * 2.0F), (double)(0.5F + f2), 1.0D, 1.0D);
                    }

                    f2 = 0.0625F;

                    if (k == 2)
                    {
                        RenderBlocks.getInstance().setRenderBounds((double)(0.5F - f2), 0.5D, 0.0D, (double)(0.5F + f2), (double)(1.0F - f2), 1.0D);
                    }

                    GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, -1.0F, 0.0F);
                    RenderBlocks.getInstance().renderFaceYNeg(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSide(p_147800_1_, 0));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 1.0F, 0.0F);
                    RenderBlocks.getInstance().renderFaceYPos(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSide(p_147800_1_, 1));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 0.0F, -1.0F);
                    RenderBlocks.getInstance().renderFaceZNeg(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSide(p_147800_1_, 2));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 0.0F, 1.0F);
                    RenderBlocks.getInstance().renderFaceZPos(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSide(p_147800_1_, 3));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(-1.0F, 0.0F, 0.0F);
                    RenderBlocks.getInstance().renderFaceXNeg(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSide(p_147800_1_, 4));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(1.0F, 0.0F, 0.0F);
                    RenderBlocks.getInstance().renderFaceXPos(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSide(p_147800_1_, 5));
                    tessellator.draw();
                    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
                }
            }
            else if (j == 32)
            {
                for (k = 0; k < 2; ++k)
                {
                    if (k == 0)
                    {
                        RenderBlocks.getInstance().setRenderBounds(0.0D, 0.0D, 0.3125D, 1.0D, 0.8125D, 0.6875D);
                    }

                    if (k == 1)
                    {
                        RenderBlocks.getInstance().setRenderBounds(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
                    }

                    GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, -1.0F, 0.0F);
                    RenderBlocks.getInstance().renderFaceYNeg(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSideAndMetadata(p_147800_1_, 0, p_147800_2_));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 1.0F, 0.0F);
                    RenderBlocks.getInstance().renderFaceYPos(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSideAndMetadata(p_147800_1_, 1, p_147800_2_));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 0.0F, -1.0F);
                    RenderBlocks.getInstance().renderFaceZNeg(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSideAndMetadata(p_147800_1_, 2, p_147800_2_));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 0.0F, 1.0F);
                    RenderBlocks.getInstance().renderFaceZPos(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSideAndMetadata(p_147800_1_, 3, p_147800_2_));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(-1.0F, 0.0F, 0.0F);
                    RenderBlocks.getInstance().renderFaceXNeg(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSideAndMetadata(p_147800_1_, 4, p_147800_2_));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(1.0F, 0.0F, 0.0F);
                    RenderBlocks.getInstance().renderFaceXPos(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSideAndMetadata(p_147800_1_, 5, p_147800_2_));
                    tessellator.draw();
                    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
                }

                RenderBlocks.getInstance().setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
            }
            else if (j == 35)
            {
                GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
                RenderBlocks.getInstance().renderBlockAnvilOrient((BlockAnvil)p_147800_1_, 0, 0, 0, p_147800_2_ << 2, true);
                GL11.glTranslatef(0.5F, 0.5F, 0.5F);
            }
            else if (j == 34)
            {
                for (k = 0; k < 3; ++k)
                {
                    if (k == 0)
                    {
                        RenderBlocks.getInstance().setRenderBounds(0.125D, 0.0D, 0.125D, 0.875D, 0.1875D, 0.875D);
                        RenderBlocks.getInstance().setOverrideBlockTexture(RenderBlocks.getInstance().getBlockIcon(Blocks.obsidian));
                    }
                    else if (k == 1)
                    {
                        RenderBlocks.getInstance().setRenderBounds(0.1875D, 0.1875D, 0.1875D, 0.8125D, 0.875D, 0.8125D);
                        RenderBlocks.getInstance().setOverrideBlockTexture(RenderBlocks.getInstance().getBlockIcon(Blocks.beacon));
                    }
                    else if (k == 2)
                    {
                        RenderBlocks.getInstance().setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
                        RenderBlocks.getInstance().setOverrideBlockTexture(RenderBlocks.getInstance().getBlockIcon(Blocks.glass));
                    }

                    GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, -1.0F, 0.0F);
                    RenderBlocks.getInstance().renderFaceYNeg(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSideAndMetadata(p_147800_1_, 0, p_147800_2_));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 1.0F, 0.0F);
                    RenderBlocks.getInstance().renderFaceYPos(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSideAndMetadata(p_147800_1_, 1, p_147800_2_));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 0.0F, -1.0F);
                    RenderBlocks.getInstance().renderFaceZNeg(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSideAndMetadata(p_147800_1_, 2, p_147800_2_));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 0.0F, 1.0F);
                    RenderBlocks.getInstance().renderFaceZPos(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSideAndMetadata(p_147800_1_, 3, p_147800_2_));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(-1.0F, 0.0F, 0.0F);
                    RenderBlocks.getInstance().renderFaceXNeg(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSideAndMetadata(p_147800_1_, 4, p_147800_2_));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(1.0F, 0.0F, 0.0F);
                    RenderBlocks.getInstance().renderFaceXPos(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSideAndMetadata(p_147800_1_, 5, p_147800_2_));
                    tessellator.draw();
                    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
                }

                RenderBlocks.getInstance().setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
                RenderBlocks.getInstance().clearOverrideBlockTexture();
            }
            else if (j == 38)
            {
                GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
                RenderBlocks.getInstance().renderBlockHopperMetadata((BlockHopper)p_147800_1_, 0, 0, 0, 0, true);
                GL11.glTranslatef(0.5F, 0.5F, 0.5F);
            }
            else
            {
                FMLRenderAccessLibrary.renderInventoryBlock(RenderBlocks.getInstance(), p_147800_1_, p_147800_2_, j);
            }
        }
        else
        {
            if (j == 16)
            {
                p_147800_2_ = 1;
            }

            p_147800_1_.setBlockBoundsForItemRender();
            RenderBlocks.getInstance().setRenderBoundsFromBlock(p_147800_1_);
            GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, -1.0F, 0.0F);
            RenderBlocks.getInstance().renderFaceYNeg(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSideAndMetadata(p_147800_1_, 0, p_147800_2_));
            tessellator.draw();

            if (flag && RenderBlocks.getInstance().useInventoryTint)
            {
                k = p_147800_1_.getRenderColor(p_147800_2_);
                f2 = (float)(k >> 16 & 255) / 255.0F;
                f3 = (float)(k >> 8 & 255) / 255.0F;
                float f4 = (float)(k & 255) / 255.0F;
                GL11.glColor4f(f2 * p_147800_3_, f3 * p_147800_3_, f4 * p_147800_3_, 1.0F);
            }

            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 1.0F, 0.0F);
            RenderBlocks.getInstance().renderFaceYPos(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSideAndMetadata(p_147800_1_, 1, p_147800_2_));
            tessellator.draw();

            if (flag && RenderBlocks.getInstance().useInventoryTint)
            {
                GL11.glColor4f(p_147800_3_, p_147800_3_, p_147800_3_, 1.0F);
            }

            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 0.0F, -1.0F);
            RenderBlocks.getInstance().renderFaceZNeg(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSideAndMetadata(p_147800_1_, 2, p_147800_2_));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 0.0F, 1.0F);
            RenderBlocks.getInstance().renderFaceZPos(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSideAndMetadata(p_147800_1_, 3, p_147800_2_));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(-1.0F, 0.0F, 0.0F);
            RenderBlocks.getInstance().renderFaceXNeg(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSideAndMetadata(p_147800_1_, 4, p_147800_2_));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(1.0F, 0.0F, 0.0F);
            RenderBlocks.getInstance().renderFaceXPos(p_147800_1_, 0.0D, 0.0D, 0.0D, RenderBlocks.getInstance().getBlockIconFromSideAndMetadata(p_147800_1_, 5, p_147800_2_));
            tessellator.draw();
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        }
    }
}
