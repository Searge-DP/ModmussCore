package modmuss50.mods.api;

import cpw.mods.fml.common.FMLCommonHandler;
import modmuss50.mods.core.ConfigurationHandler;
import modmuss50.mods.core.Core;
import modmuss50.mods.core.mod.ModRegistry;
import modmuss50.mods.lib.ReflectUtilities;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

//Use this api too add your own brandings to the main menu(Dont use it to add your mod name because lex will shout at me and you)
//Try and only add new brandings before postInit
public class BrandingsAPI {

	//this is the array list of new brandings to inject
	private static ArrayList<String> newBrandings = new ArrayList<String>();

	//use this to add new brandings bellow the minecraft version
	public static void addBranding(String string) {
		newBrandings.add(string);
	}

	//You can call this when the brandings need to be updated with the new ones
	//Things might break if you call this later(but you can try)
	public static void injectBrandings() {
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
		} catch (Exception e) {
			e.printStackTrace();
			//an epic description of the error
			Core.logger.log("THE BRANDING'S COULD NOT BE PATCHED!!!");
		}
	}

}
