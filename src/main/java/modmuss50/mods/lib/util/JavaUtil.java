package modmuss50.mods.lib.util;

public class JavaUtil {
	/**
	 * Checks if the user is using Java 7 or above
	 *
	 * @return true if the user is using Java 7 or above else false
	 */
	public static boolean isJava7OrAbove() {
		return Integer.parseInt(System.getProperty("java.version").substring(2, 3)) >= 7;
	}
}
