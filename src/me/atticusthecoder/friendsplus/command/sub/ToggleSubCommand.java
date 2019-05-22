package me.atticusthecoder.friendsplus.command.sub;

import org.bukkit.entity.Player;

import me.atticusthecoder.friendsplus.api.PlayerManager;
import me.atticusthecoder.friendsplus.command.SubCommand;
import net.md_5.bungee.api.ChatColor;

public class ToggleSubCommand extends SubCommand {
	
	@Override
	public void execute(Player caller, String[] args) {
		caller.sendMessage(ChatColor.BLUE + "--------------------------------------------");
		
		if(PlayerManager.get().getPlayerData(caller.getName()).getRequestsEnabled()) {
			// Disable
			PlayerManager.get().getPlayerData(caller.getName()).disableRequests();
			caller.sendMessage(ChatColor.GREEN + "You have disabled your friend requests!");
		} else {
			// Enable
			PlayerManager.get().getPlayerData(caller.getName()).enableRequests();
			caller.sendMessage(ChatColor.GREEN + "You have enabled your friend requests!");
		}
		
		caller.sendMessage(ChatColor.BLUE + "--------------------------------------------");
		return;
	}

}
