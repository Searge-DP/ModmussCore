/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.lib.client;


import me.modmuss50.mods.lib.vecmath.Quat4d;
import me.modmuss50.mods.lib.vecmath.Vector3d;
import me.modmuss50.mods.lib.vecmath.Vector3f;
import me.modmuss50.mods.lib.vecmath.Vertex;

public class VertexRotation implements VertexTransform {

	private final Vector3d center;
	private final Vector3d axis;
	private Quat4d quat;
	private double angle;

	public VertexRotation(double angle, Vector3d axis, Vector3d center) {
		this.center = new Vector3d(center);
		this.axis = new Vector3d(axis);
		this.angle = angle;
		quat = Quat4d.makeRotate(angle, axis);
	}

	@Override
	public void apply(Vertex vertex) {
		apply(vertex.xyz);
	}

	@Override
	public void apply(Vector3d vec) {
		vec.sub(center);
		quat.rotate(vec);
		vec.add(center);
	}

	public void setAngle(double angle) {
		this.angle = angle;
		quat = Quat4d.makeRotate(angle, axis);
	}

	public void setAxis(Vector3d axis) {
		this.axis.set(axis);
		quat = Quat4d.makeRotate(angle, axis);
	}

	public void setCenter(Vector3d cen) {
		center.set(cen);
	}

	@Override
	public void applyToNormal(Vector3f vec) {
		quat.rotate(vec);
	}

}
