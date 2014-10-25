package modmuss50.mods.mml;

import cpw.mods.fml.common.Loader;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ModmussMod {

	 String modId();

	 String modName();

	 String modVersion() default "0";

	 String recomenedMinecraftVeriosion() default Loader.MC_VERSION;

}
