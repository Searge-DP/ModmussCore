/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.lib.multiblock;

/**
 * An exception thrown when trying to validate a multiblock. Requires a string describing why the multiblock
 * could not assemble.
 *
 * @author Erogenous Beef
 */
public class MultiblockValidationException extends Exception {

	public MultiblockValidationException(String reason) {
		super(reason);
	}
}
