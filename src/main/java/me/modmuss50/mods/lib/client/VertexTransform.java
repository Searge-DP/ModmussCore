/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.lib.client;


import me.modmuss50.mods.lib.vecmath.Vector3d;
import me.modmuss50.mods.lib.vecmath.Vector3f;
import me.modmuss50.mods.lib.vecmath.Vertex;

public interface VertexTransform {

	public abstract void apply(Vertex vertex);

	public abstract void apply(Vector3d vec);

	public abstract void applyToNormal(Vector3f vec);

}
