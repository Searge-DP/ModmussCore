/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.lib.client;

import me.modmuss50.mods.lib.Location;
import me.modmuss50.mods.lib.vecmath.*;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import org.lwjgl.opengl.GL11;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import static net.minecraftforge.common.util.ForgeDirection.*;
import static org.lwjgl.opengl.GL11.*;


public class RenderUtil {

	public static final Vector4f DEFAULT_TEXT_SHADOW_COL = new Vector4f(0.33f, 0.33f, 0.33f, 0.33f);

	public static final Vector4f DEFAULT_TXT_COL = new Vector4f(1, 1, 1, 1);

	public static final Vector4f DEFAULT_TEXT_BG_COL = new Vector4f(0.275f, 0.08f, 0.4f, 0.75f);

	public static final Vector3d UP_V = new Vector3d(0, 1, 0);

	public static final Vector3d ZERO_V = new Vector3d(0, 0, 0);
	public static final ResourceLocation BLOCK_TEX = TextureMap.locationBlocksTexture;
	public static final ResourceLocation ITEM_TEX = TextureMap.locationItemsTexture;
	public static final ResourceLocation GLINT_TEX = new ResourceLocation("textures/misc/enchanted_item_glint.png");
	private static final FloatBuffer MATRIX_BUFFER = GLAllocation.createDirectFloatBuffer(16);

	public static void loadMatrix(Matrix4d mat) {
		MATRIX_BUFFER.rewind();
		MATRIX_BUFFER.put((float) mat.m00);
		MATRIX_BUFFER.put((float) mat.m01);
		MATRIX_BUFFER.put((float) mat.m02);
		MATRIX_BUFFER.put((float) mat.m03);
		MATRIX_BUFFER.put((float) mat.m10);
		MATRIX_BUFFER.put((float) mat.m11);
		MATRIX_BUFFER.put((float) mat.m12);
		MATRIX_BUFFER.put((float) mat.m13);
		MATRIX_BUFFER.put((float) mat.m20);
		MATRIX_BUFFER.put((float) mat.m21);
		MATRIX_BUFFER.put((float) mat.m22);
		MATRIX_BUFFER.put((float) mat.m23);
		MATRIX_BUFFER.put((float) mat.m30);
		MATRIX_BUFFER.put((float) mat.m31);
		MATRIX_BUFFER.put((float) mat.m32);
		MATRIX_BUFFER.put((float) mat.m33);
		MATRIX_BUFFER.rewind();
		GL11.glLoadMatrix(MATRIX_BUFFER);
	}

	public static TextureManager engine() {
		return Minecraft.getMinecraft().renderEngine;
	}

	public static void bindItemTexture(ItemStack stack) {
		engine().bindTexture(stack.getItemSpriteNumber() == 0 ? BLOCK_TEX : ITEM_TEX);
	}

	public static void bindItemTexture() {
		engine().bindTexture(ITEM_TEX);
	}

	public static void bindBlockTexture() {
		engine().bindTexture(BLOCK_TEX);
	}

	public static void bindGlintTexture() {
		engine().bindTexture(BLOCK_TEX);
	}

	public static void bindTexture(String string) {
		engine().bindTexture(new ResourceLocation(string));
	}

	public static void bindTexture(ResourceLocation tex) {
		engine().bindTexture(tex);
	}

	public static FontRenderer fontRenderer() {
		return Minecraft.getMinecraft().fontRenderer;
	}

	public static float[] getDefaultPerSideBrightness() {
		float[] brightnessPerSide = new float[6];
		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			brightnessPerSide[dir.ordinal()] = RenderUtil.getColorMultiplierForFace(dir);
		}
		return brightnessPerSide;
	}

	public static IIcon[] getBlockTextures(Block block, int meta) {
		IIcon[] icons = new IIcon[6];
		int i = 0;
		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			icons[i] = block.getIcon(dir.ordinal(), meta);
			i++;
		}
		return icons;
	}

	public static IIcon[] getBlockTextures(IBlockAccess world, int x, int y, int z) {
		Block block = world.getBlock(x, y, z);
		IIcon[] icons = new IIcon[6];
		int i = 0;
		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			icons[i] = block.getIcon(world, x, y, z, dir.ordinal());
			i++;
		}
		return icons;
	}

	public static float claculateTotalBrightnessForLocation(World worldObj, int xCoord, int yCoord, int zCoord) {
		int i = worldObj.getLightBrightnessForSkyBlocks(xCoord, yCoord, zCoord, 0);
		int j = i % 65536;
		int k = i / 65536;

		float minLight = 0;
		//0.2 - 1
		float sunBrightness = worldObj.getSunBrightness(1);

		float percentRecievedFromSun = k / 255f;

		//Highest value recieved from a light
		float fromLights = j / 255f;

		// 0 - 1 for sun only, 0 - 0.6 for light only
		float recievedPercent = worldObj.getLightBrightness(xCoord, yCoord, zCoord);
		float highestValue = Math.max(fromLights, percentRecievedFromSun * sunBrightness);
		return Math.max(0.2f, highestValue);
	}

	public static float getColorMultiplierForFace(ForgeDirection face) {
		if (face == ForgeDirection.UP) {
			return 1;
		}
		if (face == ForgeDirection.DOWN) {
			return 0.5f;
		}
		if (face.offsetX != 0) {
			return 0.6f;
		}
		return 0.8f; // z
	}

	public static int setTesselatorBrightness(IBlockAccess world, int x, int y, int z) {
		Block block = world.getBlock(x, y, z);
		int res = block == null ? world.getLightBrightnessForSkyBlocks(x, y, z, 0) : block.getMixedBrightnessForBlock(world, x, y, z);
		Tessellator.instance.setBrightness(res);
		Tessellator.instance.setColorRGBA_F(1, 1, 1, 1);
		return res;
	}

	public static void renderQuad2D(double x, double y, double z, double width, double height, int colorRGB) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.setColorOpaque_I(colorRGB);
		tessellator.addVertex(x, y + height, z);
		tessellator.addVertex(x + width, y + height, z);
		tessellator.addVertex(x + width, y, z);
		tessellator.addVertex(x, y, z);
		tessellator.draw();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}

	public static void renderQuad2D(double x, double y, double z, double width, double height, Vector4f colorRGBA) {
		GL11.glColor4f(colorRGBA.x, colorRGBA.y, colorRGBA.z, colorRGBA.w);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertex(x, y + height, z);
		tessellator.addVertex(x + width, y + height, z);
		tessellator.addVertex(x + width, y, z);
		tessellator.addVertex(x, y, z);
		tessellator.draw();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}

	public static Matrix4d createBillboardMatrix(TileEntity te, EntityLivingBase entityPlayer) {
		return createBillboardMatrix(new Vector3d(te.xCoord + 0.5, te.yCoord + 0.5, te.zCoord + 0.5), entityPlayer);
	}

	public static Matrix4d createBillboardMatrix(Vector3d lookAt, EntityLivingBase entityPlayer) {
		Vector3d playerEye = new Vector3d(entityPlayer.posX, entityPlayer.posY + 1.62 - entityPlayer.yOffset, entityPlayer.posZ);
		Vector3d blockOrigin = new Vector3d(lookAt.x, lookAt.y, lookAt.z);
		Matrix4d lookMat = VecmathUtil.createMatrixAsLookAt(blockOrigin, playerEye, RenderUtil.UP_V);
		lookMat.setTranslation(new Vector3d());
		lookMat.invert();
		return lookMat;
	}

	public static void renderBillboard(Matrix4d lookMat, float minU, float maxU, float minV, float maxV, double size, int brightness) {
		Tessellator tes = Tessellator.instance;
		tes.startDrawingQuads();
		tes.setBrightness(brightness);

		double s = size / 2;
		Vector3d v = new Vector3d();
		v.set(-s, s, 0);
		lookMat.transform(v);
		tes.addVertexWithUV(v.x, v.y, v.z, minU, maxV);
		v.set(s, s, 0);
		lookMat.transform(v);
		tes.addVertexWithUV(v.x, v.y, v.z, maxU, maxV);
		v.set(s, -s, 0);
		lookMat.transform(v);
		tes.addVertexWithUV(v.x, v.y, v.z, maxU, minV);
		v.set(-s, -s, 0);
		lookMat.transform(v);
		tes.addVertexWithUV(v.x, v.y, v.z, minU, minV);
		tes.draw();
	}

	/**
	 * left, bottom, right, top
	 *
	 * @param face
	 * @return
	 */
	public static List<ForgeDirection> getEdgesForFace(ForgeDirection face) {
		List<ForgeDirection> result = new ArrayList<ForgeDirection>(4);
		if (face.offsetY != 0) {
			result.add(NORTH);
			result.add(EAST);
			result.add(SOUTH);
			result.add(WEST);

		} else if (face.offsetX != 0) {
			result.add(DOWN);
			result.add(SOUTH);
			result.add(UP);
			result.add(NORTH);
		} else {
			result.add(DOWN);
			result.add(WEST);
			result.add(UP);
			result.add(EAST);
		}
		return result;
	}

	public static void addVerticesToTesselator(List<Vertex> vertices) {
		addVerticesToTessellator(vertices, Tessellator.instance);
	}

	public static void addVerticesToTessellator(List<Vertex> vertices, Tessellator tes) {
		for (Vertex v : vertices) {
			if (v.brightness != -1) {
				tes.setBrightness(v.brightness);
			}
			if (v.color != null) {
				tes.setColorRGBA_F(v.r(), v.g(), v.b(), v.a());
			}
			if (v.uv != null) {
				tes.setTextureUV(v.u(), v.v());
			}
			if (v.normal != null) {
				tes.setNormal(v.nx(), v.ny(), v.nz());
			}
			tes.addVertex(v.x(), v.y(), v.z());
		}
	}

	public static void getUvForCorner(Vector2f uv, Vector3d corner, int x, int y, int z, ForgeDirection face, IIcon icon) {
		if (icon == null) {
			return;
		}

		Vector3d p = new Vector3d(corner);
		p.x -= x;
		p.y -= y;
		p.z -= z;

		float uWidth = 1;
		float vWidth = 1;
		if (icon != null) {
			uWidth = icon.getMaxU() - icon.getMinU();
			vWidth = icon.getMaxV() - icon.getMinV();
		}

		uv.x = (float) VecmathUtil.distanceFromPointToPlane(getUPlaneForFace(face), p);
		uv.y = (float) VecmathUtil.distanceFromPointToPlane(getVPlaneForFace(face), p);

		if (icon != null) {
			uv.x = icon.getMinU() + (uv.x * uWidth);
			uv.y = icon.getMinV() + (uv.y * vWidth);
		}

	}

	public static Vector4d getVPlaneForFace(ForgeDirection face) {
		switch (face) {
			case DOWN:
			case UP:
				return new Vector4d(0, 0, 1, 0);
			case EAST:
			case WEST:
			case NORTH:
			case SOUTH:
				return new Vector4d(0, -1, 0, 1);
			case UNKNOWN:
			default:
				break;
		}
		return null;
	}

	public static Vector4d getUPlaneForFace(ForgeDirection face) {
		switch (face) {
			case DOWN:
			case UP:
				return new Vector4d(1, 0, 0, 0);
			case EAST:
				return new Vector4d(0, 0, -1, 1);
			case WEST:
				return new Vector4d(0, 0, 1, 0);
			case NORTH:
				return new Vector4d(-1, 0, 0, 1);
			case SOUTH:
				return new Vector4d(1, 0, 0, 0);
			case UNKNOWN:
			default:
				break;
		}
		return null;
	}

	public static ForgeDirection getVDirForFace(ForgeDirection face) {
		switch (face) {
			case DOWN:
			case UP:
				return SOUTH;
			case EAST:
			case WEST:
			case NORTH:
			case SOUTH:
				return ForgeDirection.UP;
			case UNKNOWN:
			default:
				break;
		}
		return null;
	}

	public static ForgeDirection getUDirForFace(ForgeDirection face) {
		switch (face) {
			case DOWN:
			case UP:
				return ForgeDirection.EAST;
			case EAST:
				return NORTH;
			case WEST:
				return SOUTH;
			case NORTH:
				return WEST;
			case SOUTH:
				return ForgeDirection.EAST;
			case UNKNOWN:
			default:
				break;
		}
		return null;
	}

	public static void renderGuiTank(FluidTank tank, double x, double y, double zLevel, double width, double height) {
		renderGuiTank(tank.getFluid(), tank.getCapacity(), tank.getFluidAmount(), x, y, zLevel, width, height);
	}

	public static void renderGuiTank(FluidStack fluid, int capacity, int amount, double x, double y, double zLevel, double width, double height) {
		if (fluid == null || fluid.getFluid() == null || fluid.amount <= 0) {
			return;
		}

		IIcon icon = fluid.getFluid().getStillIcon();
		if (icon == null) {
			icon = fluid.getFluid().getIcon();
			if (icon == null) {
				return;
			}
		}

		int renderAmount = (int) Math.max(Math.min(height, amount * height / capacity), 1);
		int posY = (int) (y + height - renderAmount);

		RenderUtil.bindBlockTexture();
		int color = fluid.getFluid().getColor(fluid);
		GL11.glColor3ub((byte) (color >> 16 & 0xFF), (byte) (color >> 8 & 0xFF), (byte) (color & 0xFF));

		GL11.glEnable(GL11.GL_BLEND);
		for (int i = 0; i < width; i += 16) {
			for (int j = 0; j < renderAmount; j += 16) {
				int drawWidth = (int) Math.min(width - i, 16);
				int drawHeight = Math.min(renderAmount - j, 16);

				int drawX = (int) (x + i);
				int drawY = posY + j;

				double minU = icon.getMinU();
				double maxU = icon.getMaxU();
				double minV = icon.getMinV();
				double maxV = icon.getMaxV();

				Tessellator tessellator = Tessellator.instance;
				tessellator.startDrawingQuads();
				tessellator.addVertexWithUV(drawX, drawY + drawHeight, 0, minU, minV + (maxV - minV) * drawHeight / 16F);
				tessellator.addVertexWithUV(drawX + drawWidth, drawY + drawHeight, 0, minU + (maxU - minU) * drawWidth / 16F, minV + (maxV - minV) * drawHeight / 16F);
				tessellator.addVertexWithUV(drawX + drawWidth, drawY, 0, minU + (maxU - minU) * drawWidth / 16F, minV);
				tessellator.addVertexWithUV(drawX, drawY, 0, minU, minV);
				tessellator.draw();
			}
		}
		GL11.glDisable(GL11.GL_BLEND);
	}

	public static void drawBillboardedText(Vector3f pos, String text, float size) {
		drawBillboardedText(pos, text, size, DEFAULT_TXT_COL, true, DEFAULT_TEXT_SHADOW_COL, true, DEFAULT_TEXT_BG_COL);
	}

	public static void drawBillboardedText(Vector3f pos, String text, float size, Vector4f bgCol) {
		drawBillboardedText(pos, text, size, DEFAULT_TXT_COL, true, DEFAULT_TEXT_SHADOW_COL, true, bgCol);
	}

	public static void drawBillboardedText(Vector3f pos, String text, float size, Vector4f txtCol, boolean drawShadow, Vector4f shadowCol,
										   boolean drawBackground, Vector4f bgCol) {
		GL11.glPushMatrix();
		GL11.glTranslatef(pos.x, pos.y, pos.z);
		glRotatef(180, 1, 0, 0);

		FontRenderer fnt = Minecraft.getMinecraft().fontRenderer;
		float scale = size / fnt.FONT_HEIGHT;
		GL11.glScalef(scale, scale, scale);
		glRotatef(RenderManager.instance.playerViewY + 180, 0.0F, 1.0F, 0.0F);
		glRotatef(-RenderManager.instance.playerViewX, 1.0F, 0.0F, 0.0F);

		glTranslatef(-fnt.getStringWidth(text) / 2, 0, 0);
		if (drawBackground) {
			renderBackground(fnt, text, bgCol);
		}
		fnt.drawString(text, 0, 0, ColorUtil.getRGBA(txtCol));
		if (drawShadow) {
			glTranslatef(0.5f, 0.5f, 0.1f);
			fnt.drawString(text, 0, 0, ColorUtil.getRGBA(shadowCol));
		}
		GL11.glPopMatrix();

		RenderUtil.bindBlockTexture();
	}

	public static void renderBackground(FontRenderer fnt, String toRender, Vector4f color) {
		glPushAttrib(GL_ALL_ATTRIB_BITS);
		glDisable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glShadeModel(GL_SMOOTH);
		glDisable(GL_ALPHA_TEST);
		glDisable(GL_CULL_FACE);
		glDepthMask(false);
		RenderHelper.disableStandardItemLighting();
		OpenGlHelper.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ONE, GL_ZERO); // stop random disappearing

		float width = (float) fnt.getStringWidth(toRender);
		float height = (float) fnt.FONT_HEIGHT;
		float padding = 2f;
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.setColorRGBA_F(color.x, color.y, color.z, color.w);
		tessellator.addVertex(-padding, -padding, 0);
		tessellator.addVertex(-padding, height + padding, 0);
		tessellator.addVertex(width + padding, height + padding, 0);
		tessellator.addVertex(width + padding, -padding, 0);
		tessellator.draw();

		glPopAttrib();
	}

	private static class EdgeNeighbour {
		final ForgeDirection dir;
		final Location bc;

		public EdgeNeighbour(Location bc, ForgeDirection dir) {
			this.dir = dir;
			this.bc = bc.getLocation(dir);
		}

	}

}
