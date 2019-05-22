package me.atticusthecoder.friendsplus.command.sub;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.atticusthecoder.friendsplus.api.FriendAPI;
import me.atticusthecoder.friendsplus.command.SubCommand;
import net.md_5.bungee.api.ChatColor;

public class RemoveSubCommand extends SubCommand {
	
	@Override
	public void execute(Player caller, String[] args) {
		caller.sendMessage(ChatColor.BLUE + "--------------------------------------------");
		if(args.length == 1) {
			caller.sendMessage(ChatColor.RED + "Invalid Usage! Valid Usage: /f remove <player>");
			return;
		}
		
		String target = args[1];
		if(!FriendAPI.getApi().areFriends(caller.getName(), target)) {
			caller.sendMessage(ChatColor.RED + "You are not friends with " + target + "!");
			caller.sendMessage(ChatColor.BLUE + "--------------------------------------------");
			return;
		}
		
		FriendAPI.getApi().removeFriend(caller.getName(), target);
		
		caller.sendMessage(ChatColor.GREEN + "You have removed " + target + " from your friends list!");
		
		if(Bukkit.getPlayer(target) != null) {
			Bukkit.getPlayer(target).sendMessage(ChatColor.GREEN + caller.getName() + " has removed you from their friends list!");
		}
		
		return;
	}

}
