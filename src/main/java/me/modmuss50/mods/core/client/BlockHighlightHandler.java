/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.core.client;


import me.modmuss50.mods.lib.client.ICustomHighlight;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

public class BlockHighlightHandler {

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void handleBlockHighlight(DrawBlockHighlightEvent e) {
		if (e.target.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
			int x = e.target.getBlockPos().getX();
			int y = e.target.getBlockPos().getY();
			int z = e.target.getBlockPos().getZ();
			Block block = e.player.worldObj.getBlockState(new BlockPos(x, y, z)).getBlock();
			if (block instanceof ICustomHighlight) {
				ArrayList<AxisAlignedBB> aabbs = ((ICustomHighlight) block).getBoxes(e.player.worldObj, x, y, z, e.player);
				if(aabbs == null)
					return;
				BlockPos pos = e.player.getPosition();
				GL11.glEnable(GL11.GL_BLEND);
				OpenGlHelper.glBlendFunc(770, 771, 1, 0);
				GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.4F);
				GL11.glLineWidth(2.0F);
				GL11.glDisable(GL11.GL_TEXTURE_2D);
				GL11.glDepthMask(false);
				for (AxisAlignedBB aabb : aabbs) {
					RenderGlobal.drawOutlinedBoundingBox(aabb
							.offset(x, y, z)
							.offset(-pos.getX(), -pos.getY(), -pos.getZ()), -1);
				}
				GL11.glDepthMask(true);
				GL11.glEnable(GL11.GL_TEXTURE_2D);
				GL11.glDisable(GL11.GL_BLEND);
				e.setCanceled(true);
			}
		}
	}
}
