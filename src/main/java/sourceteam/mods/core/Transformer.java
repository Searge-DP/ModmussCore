package sourceteam.mods.core;

import net.minecraft.launchwrapper.IClassTransformer;

public class Transformer implements IClassTransformer {

	@Override
	public byte[] transform(String name, String decodedName, byte[] code) {
		return code;
	}
	

}
