/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.lib.multiblock.rectangular;

import me.modmuss50.mods.lib.CoordTriplet;
import me.modmuss50.mods.lib.multiblock.MultiblockControllerBase;
import me.modmuss50.mods.lib.multiblock.MultiblockTileEntityBase;
import me.modmuss50.mods.lib.multiblock.MultiblockValidationException;
import net.minecraft.util.EnumFacing;


public abstract class RectangularMultiblockTileEntityBase extends
		MultiblockTileEntityBase {

	PartPosition position;
	EnumFacing outwards;

	public RectangularMultiblockTileEntityBase() {
		super();

		position = PartPosition.Unknown;
		outwards = null;
	}

	// Positional Data
	public EnumFacing getOutwardsDir() {
		return outwards;
	}

	public PartPosition getPartPosition() {
		return position;
	}

	// Handlers from MultiblockTileEntityBase 
	@Override
	public void onAttached(MultiblockControllerBase newController) {
		super.onAttached(newController);
		recalculateOutwardsDirection(newController.getMinimumCoord(), newController.getMaximumCoord());
	}


	@Override
	public void onMachineAssembled(MultiblockControllerBase controller) {
		CoordTriplet maxCoord = controller.getMaximumCoord();
		CoordTriplet minCoord = controller.getMinimumCoord();

		// Discover where I am on the reactor
		recalculateOutwardsDirection(minCoord, maxCoord);
	}

	@Override
	public void onMachineBroken() {
		position = PartPosition.Unknown;
		outwards = null;
	}

	// Positional helpers
	public void recalculateOutwardsDirection(CoordTriplet minCoord, CoordTriplet maxCoord) {
		outwards = null;
		position = PartPosition.Unknown;

		int facesMatching = 0;
		if (maxCoord.x == this.getPos().getX() || minCoord.x == this.getPos().getX()) {
			facesMatching++;
		}
		if (maxCoord.y == this.getPos().getY() || minCoord.y == this.getPos().getY()) {
			facesMatching++;
		}
		if (maxCoord.z == this.getPos().getZ() || minCoord.z == this.getPos().getZ()) {
			facesMatching++;
		}

		if (facesMatching <= 0) {
			position = PartPosition.Interior;
		} else if (facesMatching >= 3) {
			position = PartPosition.FrameCorner;
		} else if (facesMatching == 2) {
			position = PartPosition.Frame;
		} else {
			// 1 face matches
			if (maxCoord.x == this.pos.getX()) {
				position = PartPosition.EastFace;
				outwards = EnumFacing.EAST;
			} else if (minCoord.x == this.pos.getX()) {
				position = PartPosition.WestFace;
				outwards = EnumFacing.WEST;
			} else if (maxCoord.z == this.pos.getZ()) {
				position = PartPosition.SouthFace;
				outwards = EnumFacing.SOUTH;
			} else if (minCoord.z == this.pos.getZ()) {
				position = PartPosition.NorthFace;
				outwards = EnumFacing.NORTH;
			} else if (maxCoord.y == this.pos.getY()) {
				position = PartPosition.TopFace;
				outwards = EnumFacing.UP;
			} else {
				position = PartPosition.BottomFace;
				outwards = EnumFacing.DOWN;
			}
		}
	}

	///// Validation Helpers (IMultiblockPart)
	public abstract void isGoodForFrame() throws MultiblockValidationException;

	public abstract void isGoodForSides() throws MultiblockValidationException;

	public abstract void isGoodForTop() throws MultiblockValidationException;

	public abstract void isGoodForBottom() throws MultiblockValidationException;

	public abstract void isGoodForInterior() throws MultiblockValidationException;
}
