package me.modmuss50.mods.mml;

import java.util.Collection;

public interface IModListener {

	public String getModID();

	public void handle(Collection<Class<?>> plugins);
}
