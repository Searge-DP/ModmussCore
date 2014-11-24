package modmuss50.mods.lib.client;

import modmuss50.mods.lib.vecmath.Vector3d;
import modmuss50.mods.lib.vecmath.Vector3f;
import modmuss50.mods.lib.vecmath.Vertex;

import java.util.Collection;


public class VertexTransformComposite implements VertexTransform {

	private final VertexTransform[] xforms;

	public VertexTransformComposite(VertexTransform... xforms) {
		this.xforms = xforms;
	}

	VertexTransformComposite(Collection<VertexTransform> xformsIn) {
		xforms = new VertexTransform[xformsIn.size()];
		int i = 0;
		for (VertexTransform xform : xformsIn) {
			xforms[i] = xform;
			i++;
		}
	}

	@Override
	public void apply(Vertex vertex) {
		for (VertexTransform xform : xforms) {
			xform.apply(vertex);
		}
	}

	@Override
	public void apply(Vector3d vec) {
		for (VertexTransform xform : xforms) {
			xform.apply(vec);
		}
	}

	@Override
	public void applyToNormal(Vector3f vec) {
		for (VertexTransform xform : xforms) {
			xform.applyToNormal(vec);
		}
	}

}
