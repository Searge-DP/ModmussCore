package modmuss50.mods.core.asm;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

import java.io.File;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ModmussTransformer implements IClassTransformer
{
    public byte[] transform(String arg0, String arg1, byte[] arg2)
    {
//        if ((arg0.equals("adr") | arg0.contains("net.minecraft.world.chunk.Chunk"))) {
//            System.out.println("********* INSIDE OBFUSCATED GENERATION TRANSFORMER ABOUT TO PATCH: " + arg0);
//            arg2 = addMethod(arg0, arg2, ModmussPachingLoader.location);
//        }
//        if ((arg0.equals("aee") | arg0.contains("net.minecraft.world.chunk.storage.AnvilChunkLoader"))) {
//            System.out.println("********* INSIDE OBFUSCATED GENERATION TRANSFORMER ABOUT TO PATCH: " + arg0);
//            arg2 = patchClassInJar(arg0, arg2, arg1, ModmussPachingLoader.location);
//        }
//        if (arg0.equals("cpw.mods.fml.common.registry.GameRegistry")) {
//            System.out.println("********* INSIDE OBFUSCATED GENERATION TRANSFORMER ABOUT TO PATCH: " + arg0);
//            arg2 = patchClassInJar(arg0, arg2, arg1, ModmussPachingLoader.location);
//        }
        if (arg0.equals(" net.minecraft.client.gui.GuiMainMenu")) {
            System.out.println("********* INSIDE OBFUSCATED GENERATION TRANSFORMER ABOUT TO PATCH: " + arg0);
            arg2 = patchClassInJar(arg0, arg2, arg1, ModmussPachingLoader.location);
        }


        return arg2;
    }

    public byte[] addMethod(String name, byte[] bytes, File location)
    {
        ClassReader reader = new ClassReader(bytes);
        ClassNode clazz = new ClassNode();
        reader.accept(clazz, 0);

        clazz.fields.add(new FieldNode(1, "modsGenerated", Type.getDescriptor(String.class), null, null));

        ClassWriter writer = new ClassWriter(3);
        clazz.accept(writer);
        bytes = writer.toByteArray();
        return bytes;
    }

    public byte[] patchClassInJar(String name, byte[] bytes, String ObfName, File location)
    {
        try
        {
            ZipFile zip = new ZipFile(location);

            ZipEntry entry = zip.getEntry(name.replace('.', '/') + ".class");

            if (entry == null) {
                System.out.println(name + " not found in " + location.getName());
            }
            else
            {
                InputStream zin = zip.getInputStream(entry);
                bytes = new byte[(int)entry.getSize()];
                zin.read(bytes);
                zin.close();
                System.out.println("[GenerationManager]: Class " + name + " patched!");
            }
            zip.close();
        } catch (Exception e) {
            throw new RuntimeException("Error overriding " + name + " from " + location.getName(), e);
        }

        return bytes;
    }
}
