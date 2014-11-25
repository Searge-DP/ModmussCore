package modmuss50.mods.mml.command;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandHandler;
import net.minecraft.command.ICommand;

public class ForgeCommandHelper {

	public static void removeServerCommand(ICommand command) {
		CommandHandler ch = (CommandHandler) Minecraft.getMinecraft().getIntegratedServer().getCommandManager();

		//TODO use hackey stuff to remove the commands from forge here

	}
}
