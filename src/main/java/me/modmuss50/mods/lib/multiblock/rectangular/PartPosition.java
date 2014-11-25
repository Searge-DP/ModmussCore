/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.lib.multiblock.rectangular;

public enum PartPosition {
	Unknown,
	Interior,
	FrameCorner,
	Frame,
	TopFace,
	BottomFace,
	NorthFace,
	SouthFace,
	EastFace,
	WestFace;

	public boolean isFace(PartPosition position) {
		switch (position) {
			case TopFace:
			case BottomFace:
			case NorthFace:
			case SouthFace:
			case EastFace:
			case WestFace:
				return true;
			default:
				return false;
		}
	}
}
