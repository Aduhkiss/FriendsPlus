package me.atticusthecoder.friendsplus.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.atticusthecoder.friendsplus.command.sub.AddSubCommand;
import me.atticusthecoder.friendsplus.command.sub.ListSubCommand;
import me.atticusthecoder.friendsplus.command.sub.RemoveSubCommand;
import me.atticusthecoder.friendsplus.command.sub.ToggleSubCommand;
import me.atticusthecoder.friendsplus.data.DataHandler;
import net.md_5.bungee.api.ChatColor;

public class FriendCommand implements CommandExecutor {
	
	private ListSubCommand listSubCommand;
	private AddSubCommand addSubCommand;
	private RemoveSubCommand removeSubCommand;
	private ToggleSubCommand toggleSubCommand;
	
	public FriendCommand() {
		listSubCommand = new ListSubCommand();
		addSubCommand = new AddSubCommand();
		removeSubCommand = new RemoveSubCommand();
		toggleSubCommand = new ToggleSubCommand();
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		if(!(sender instanceof Player)) {
			return false;
		}
		Player player = (Player) sender;
		
		DataHandler.getInstance().saveData();
		
		if(args == null || args.length == 0) {
			player.sendMessage(ChatColor.BLUE + "--------------------------------------------");
			player.sendMessage(ChatColor.GREEN + "Friend Commands: ");
			player.sendMessage(ChatColor.YELLOW + "/f add <player> " + ChatColor.GRAY + "- " + ChatColor.AQUA + "Add a player as a friend");
			player.sendMessage(ChatColor.YELLOW + "/f remove <player> " + ChatColor.GRAY + "- " + ChatColor.AQUA + "Remove a player from your friends list.");
			player.sendMessage(ChatColor.YELLOW + "/f list " + ChatColor.GRAY + "- " + ChatColor.AQUA + "Show your friends");
			player.sendMessage(ChatColor.YELLOW + "/f toggle " + ChatColor.GRAY + "- " + ChatColor.AQUA + "Toggle if others can add you");
			//player.sendMessage(ChatColor.YELLOW + "/f removeall " + ChatColor.GRAY + "- " + ChatColor.AQUA + "Remove all of your friends");
			player.sendMessage(ChatColor.BLUE + "--------------------------------------------");
			return true;
		}
		
		if(args[0].equalsIgnoreCase("list")) {
			listSubCommand.execute(player, args);
			return true;
		}
		if(args[0].equalsIgnoreCase("add")) {
			addSubCommand.execute(player, args);
			return true;
		}
		if(args[0].equalsIgnoreCase("remove")) {
			removeSubCommand.execute(player, args);
			return true;
		}
		if(args[0].equalsIgnoreCase("toggle")) {
			toggleSubCommand.execute(player, args);
			return true;
		}
		
		
		player.sendMessage(ChatColor.BLUE + "--------------------------------------------");
		player.sendMessage(ChatColor.GREEN + "Friend Commands: ");
		player.sendMessage(ChatColor.YELLOW + "/f add <player> " + ChatColor.GRAY + "- " + ChatColor.AQUA + "Add a player as a friend");
		player.sendMessage(ChatColor.YELLOW + "/f remove <player> " + ChatColor.GRAY + "- " + ChatColor.AQUA + "Remove a player from your friends list.");
		player.sendMessage(ChatColor.YELLOW + "/f list " + ChatColor.GRAY + "- " + ChatColor.AQUA + "Show your friends");
		player.sendMessage(ChatColor.YELLOW + "/f toggle " + ChatColor.GRAY + "- " + ChatColor.AQUA + "Toggle if others can add you");
		player.sendMessage(ChatColor.BLUE + "--------------------------------------------");
		return true;
	}

}
