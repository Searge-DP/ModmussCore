/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.lib.util;

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
