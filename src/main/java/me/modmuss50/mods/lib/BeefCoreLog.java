/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.lib;


import net.minecraftforge.fml.common.FMLLog;
import org.apache.logging.log4j.Level;

public class BeefCoreLog {

	private static final String CHANNEL = "BeefCore";

	public static void log(Level level, String format, Object... data) {
		FMLLog.log(level, format, data);
	}

	public static void fatal(String format, Object... data) {
		log(Level.FATAL, format, data);
	}

	public static void error(String format, Object... data) {
		log(Level.ERROR, format, data);
	}

	public static void warning(String format, Object... data) {
		log(Level.WARN, format, data);
	}

	public static void info(String format, Object... data) {
		log(Level.INFO, format, data);
	}

	public static void debug(String format, Object... data) {
		log(Level.DEBUG, format, data);
	}

	public static void trace(String format, Object... data) {
		log(Level.TRACE, format, data);
	}
}
