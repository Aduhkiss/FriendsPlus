package me.atticusthecoder.friendsplus.command.sub;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.atticusthecoder.friendsplus.api.FriendAPI;
import me.atticusthecoder.friendsplus.api.PlayerManager;
import me.atticusthecoder.friendsplus.command.SubCommand;
import me.atticusthecoder.friendsplus.object.FriendRequest;
import net.md_5.bungee.api.ChatColor;

public class AddSubCommand extends SubCommand {
	
	@Override
	public void execute(Player caller, String[] args) {
		caller.sendMessage(ChatColor.BLUE + "--------------------------------------------");
		if(args.length == 1) {
			caller.sendMessage(ChatColor.RED + "Invalid Usage! Valid Usage: /f add <player>");
			return;
		}
		
		// First check if they sent a friend request to you first
		FriendRequest fr = sentOneFirst(args[1], caller);
		if(fr != null) {
			fr.disable();
			// Accept the one they sent
			if(Bukkit.getPlayer(fr.getFromName()) != null) {
				Bukkit.getPlayer(fr.getFromName()).sendMessage(ChatColor.GREEN + caller.getName() + " has accepted your friend request!");
			}
			caller.sendMessage(ChatColor.GREEN + "You have accepted " + fr.getFromName() + "'s friend request!");
			
			// Then add them as friends
			FriendAPI.getApi().addFriend(fr.getFromName(), fr.getToName());
			return;
			
		} else {
			String target = args[1];
			if(Bukkit.getPlayer(target) == null) {
				caller.sendMessage(ChatColor.RED + "Can't find online player by the name of `" + target + "`");
				caller.sendMessage(ChatColor.BLUE + "--------------------------------------------");
				return;
			}
			
			// Make sure their not trying to add themselfs
			if(caller.getName().equalsIgnoreCase(target)) {
				caller.sendMessage(ChatColor.RED + "You cannot add yourself as a friend!");
				caller.sendMessage(ChatColor.BLUE + "--------------------------------------------");
				return;
			}
			
			// Check if they have requests disabled
			if(!PlayerManager.get().getPlayerData(target).getRequestsEnabled()) {
				caller.sendMessage(ChatColor.RED + target + " has friend requests disabled!");
				caller.sendMessage(ChatColor.BLUE + "--------------------------------------------");
				return;
			}
			
			// Make sure they are not already friends
			if(FriendAPI.getApi().areFriends(caller.getName(), target)) {
				caller.sendMessage(ChatColor.RED + "You are already friends with " + target + "!");
				caller.sendMessage(ChatColor.BLUE + "--------------------------------------------");
				return;
			}
			
			// Make sure the player cant spam them with friend requests
			if(friendRequestOpen(target, caller.getName())) {
				caller.sendMessage(ChatColor.RED + "You already have an open friend request to " + target + "!");
				caller.sendMessage(ChatColor.BLUE + "--------------------------------------------");
				return;
			}
			
			
			caller.sendMessage(ChatColor.GREEN + "You have sent a friend request to " + target + "! They have 5 minutes to accept it!");
			PlayerManager.get().getPlayerData(target).addFriendRequest(caller.getName());
			
			PlayerManager.get().getPlayerData(target).getPlayer().sendMessage(ChatColor.GREEN + caller.getName() + " sent you a friend request! Run " + ChatColor.YELLOW + "/f add " + caller.getName() + ChatColor.GREEN + " to accept!");
			
			caller.sendMessage(ChatColor.BLUE + "--------------------------------------------");
			return;
		}
	}
	
	public boolean friendRequestOpen(String target, String requester) {
		List<FriendRequest> requests = PlayerManager.get().getPlayerData(target).getFriendRequests();
		for(FriendRequest r : requests) {
			if(r.getFromName().equalsIgnoreCase(requester)) {
				return true;
			}
		}
		return false;
	}
	
	public FriendRequest sentOneFirst(String name, Player caller) {
		for(FriendRequest fr : PlayerManager.get().getPlayerData(caller.getName()).getFriendRequests()) {
			if(fr.getFromName().equalsIgnoreCase(name)) {
				return fr;
			}
		}
		return null;
	}

}
