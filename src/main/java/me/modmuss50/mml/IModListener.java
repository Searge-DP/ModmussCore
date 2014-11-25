/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mml;

import java.util.Collection;

public interface IModListener {

	public String getModID();

	public void handle(Collection<Class<?>> plugins);
}
