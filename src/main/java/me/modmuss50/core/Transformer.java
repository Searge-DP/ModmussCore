/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.core;


import net.minecraft.launchwrapper.IClassTransformer;


public class Transformer implements IClassTransformer {

	@Override
	public byte[] transform(String name, String decodedName, byte[] code) {
		return code;
	}


}
