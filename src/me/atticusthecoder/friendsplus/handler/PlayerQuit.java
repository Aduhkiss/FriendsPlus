package me.atticusthecoder.friendsplus.handler;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.atticusthecoder.friendsplus.api.PlayerManager;
import me.atticusthecoder.friendsplus.data.DataHandler;
import me.atticusthecoder.friendsplus.object.Friend;
import me.atticusthecoder.friendsplus.object.PlayerData;
import net.md_5.bungee.api.ChatColor;

public class PlayerQuit implements Listener {
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		// When players leave the game, we want to save their data
		PlayerData data = PlayerManager.get().getPlayerData(e.getPlayer().getName());
		List<String> friendsStr = new ArrayList<String>();
		
		for(Friend f : data.getFriends()) {
			friendsStr.add(f.getName());
		}
		
		// Let this persons friends know they left the server
		for(Friend f : data.getFriends()) {
			if(f.isOnline()) {
				Player friend = Bukkit.getPlayer(f.getName());
				friend.sendMessage(ChatColor.AQUA + "Your friend " + e.getPlayer().getName() + " has left.");
			}
		}
		
		
		boolean requests = PlayerManager.get().getPlayerData(e.getPlayer().getName()).getRequestsEnabled();
		DataHandler.getInstance().getFriendData().set("players." + e.getPlayer().getUniqueId() + ".requests", requests);
		
		DataHandler.getInstance().getFriendData().set("players." + e.getPlayer().getUniqueId() + ".friends", friendsStr);
		Bukkit.getLogger().info("[Friends+] Saved friend data for " + e.getPlayer().getName() + ".");
		}

}
