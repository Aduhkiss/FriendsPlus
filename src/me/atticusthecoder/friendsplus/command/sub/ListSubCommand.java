package me.atticusthecoder.friendsplus.command.sub;

import org.bukkit.entity.Player;

import me.atticusthecoder.friendsplus.api.PlayerManager;
import me.atticusthecoder.friendsplus.command.SubCommand;
import me.atticusthecoder.friendsplus.object.Friend;
import net.md_5.bungee.api.ChatColor;

public class ListSubCommand extends SubCommand {
	@Override
	public void execute(Player caller, String[] args) {
		caller.sendMessage(ChatColor.BLUE + "--------------------------------------------");
		
		// Push an update
		PlayerManager.get().getPlayerData(caller.getName()).updateData();
		
		if(PlayerManager.get().getPlayerData(caller.getName()).getFriends().size() == 0) {
			caller.sendMessage(ChatColor.RED + "Oh no! It looks like you don't have any friends!");
			return;
		}
		
		for(Friend f : PlayerManager.get().getPlayerData(caller.getName()).getFriends()) {
			if(f.isOnline()) {
				caller.sendMessage(ChatColor.GRAY + f.getName() + ChatColor.GREEN + " is online.");
			} else {
				caller.sendMessage(ChatColor.GRAY + f.getName() + ChatColor.RED + " is offline.");
			}
		}
		
		caller.sendMessage(ChatColor.BLUE + "--------------------------------------------");
	}
}
