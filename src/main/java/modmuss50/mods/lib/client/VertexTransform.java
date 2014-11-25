package modmuss50.mods.lib.client;


import modmuss50.mods.lib.vecmath.Vector3d;
import modmuss50.mods.lib.vecmath.Vector3f;
import modmuss50.mods.lib.vecmath.Vertex;

public interface VertexTransform {

	public abstract void apply(Vertex vertex);

	public abstract void apply(Vector3d vec);

	public abstract void applyToNormal(Vector3f vec);

}
