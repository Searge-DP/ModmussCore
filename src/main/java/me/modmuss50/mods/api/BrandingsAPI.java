/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.api;


import me.modmuss50.mods.core.ConfigurationHandler;
import me.modmuss50.mods.core.Core;
import me.modmuss50.mods.core.mod.ModRegistry;
import me.modmuss50.mods.lib.ReflectUtilities;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

//Use this api too add your own brandings to the main menu(Dont use it to add your mod name because lex will shout at me and you)
//Try and only add new brandings before postInit
public class BrandingsAPI {

	//this is the array list of new brandings to inject
	private static ArrayList<String> newBrandings = new ArrayList<String>();

	private static boolean hasInjected = false;

	//You can call this when the brandings need to be updated with the new ones
	//Things might break if you call this later(but you can try)
	public static void injectBrandings() {
		//This makes sure that it can only be ran once.
		if (!hasInjected) {
			//Disable the brandings
			if (ConfigurationHandler.brandingsInjector == false) {
				return;
			}
			Field f = ReflectUtilities.getField(FMLCommonHandler.instance(), "brandings");
			f.setAccessible(true);
			try {
				//let FML add its brandings
				FMLCommonHandler.instance().computeBranding();
				//get the forge brandings
				List<String> brands = new ArrayList<String>((List<String>) f.get(FMLCommonHandler.instance()));
				//this is the new List of brandings
				List<String> newBrands = new ArrayList<String>();
				//Adds all of the forge brandings
				for (int i = 0; i < brands.size(); i++) {
					if (i == 1) {
						//add the new custom brandings bellow the minecraft version
						newBrands.addAll(BrandingsAPI.newBrandings);
					}
					//add the keep adding the forge brandings
					newBrands.add(brands.get(i));
				}
				//Add the ammout of source mods loaded at the bottom of the list
				newBrands.add(ModRegistry.mods.size() + " source mods loaded");
				//over rights the old brandings with this new ones
				f.set(FMLCommonHandler.instance(), newBrands);
				hasInjected = true;
			} catch (Exception e) {
				e.printStackTrace();
				//an epic description of the error
				Core.logger.log("THE BRANDING'S COULD NOT BE PATCHED!!!");
				//Do this becuase if it didnt work the first time it might not work again.
				hasInjected = true;
			}
		}
	}

	//This returns true if they have been added before the new brandings has been injected.
	//most of the time you dont need to check that but it is there if you need it
	public static boolean addBranding(String branding) {
		newBrandings.add(branding);
		return !hasInjected;
	}
}
