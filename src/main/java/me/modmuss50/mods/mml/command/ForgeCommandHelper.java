/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package me.modmuss50.mods.mml.command;

import com.google.common.collect.Maps;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandHandler;
import net.minecraft.command.ICommand;

import java.util.Iterator;
import java.util.Map;

public class ForgeCommandHelper {

//	public static void removeServerCommand(ICommand command) {


//		System.out.println(commandMap.size());
//
//		for(Object obj : commandMap.values()){
//			ICommand cmd = (ICommand) obj;
//
//			if(cmd.getName().equals(command.getName())){
//				ch.getCommands().remove(command);
//				System.out.println(cmd.getName());
//			}
//		}
//
//		System.out.println(commandMap.size());

//		for (int i = 0; i < ch.getCommands().size(); i++) {
//
////			if(cmd.getClass().getCanonicalName().equals(command.getClass().getCanonicalName())){
////				ch.getCommands().remove(cmd);
////			}
//		}

		//System.out.println(ch.getCommands().size());

//	}

	public static void removeServerCommand(ICommand command)
	{
		CommandHandler ch = (CommandHandler) Minecraft.getMinecraft().getIntegratedServer().getCommandManager();
		Map commandMap = ch.getCommands();
		//commandMap.put(command.getName(), command);
		//ch.com.add(command);
		for(Object obj : commandMap.values()){
			ICommand cmd = (ICommand) obj;
			System.out.println(cmd.getName());
			if(cmd.getName().equals(command.getName())){
				ch.getCommands().remove(command);

			}
		}
	}
}
