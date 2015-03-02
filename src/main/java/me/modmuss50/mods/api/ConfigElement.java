/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.api;

/**
 * Created by mark on 05/02/15.
 */
public class ConfigElement {

	public String name;

	public String value;

	public ConfigElement(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public ConfigElement(String name, Boolean value) {
		this.name = name;
		if(value){
			this.value = "true";
		} else {
			this.value = "false";
		}

	}

	public ConfigElement(String name, int value) {
		this.name = name;
		this.value = Integer.toString(value);
	}
}
