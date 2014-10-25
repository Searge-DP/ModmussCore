package modmuss50.mods.core.Command;

import modmuss50.mods.core.mod.ModRegistry;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class ModmussCommand extends CommandBase {

	@Override
	public String getCommandName() {
		return "mmc";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		return "Use /forge <subcommand>";
	}

	@Override
	public void processCommand(ICommandSender player, String[] args) {
		if(args.length == 0){
			player.addChatMessage(new ChatComponentText("No args provided"));
		} else if(args[0].equals("help")) {
			player.addChatMessage(new ChatComponentText("There is no commands at the moment :P"));
		} else if(args[0].equals("mods")) {
			for (int i = 0; i < ModRegistry.mods.size(); i++)
				player.addChatMessage(new ChatComponentText(ModRegistry.mods.get(i).modName()));
		}
	}
}
