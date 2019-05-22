package me.atticusthecoder.friendsplus.handler;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.atticusthecoder.friendsplus.api.PlayerManager;
import me.atticusthecoder.friendsplus.data.DataHandler;
import me.atticusthecoder.friendsplus.object.Friend;
import me.atticusthecoder.friendsplus.object.PlayerData;

public class PlayerJoin implements Listener {
	@EventHandler
	public void playerJoin(PlayerJoinEvent e) {
		// Check if they have data on them
		
		if(DataHandler.getInstance().getFriendData().getString("players." + e.getPlayer().getUniqueId() + ".name") == null) {
			// No data has been found on this player, so we want to create it now
			DataHandler.getInstance().getFriendData().set("players." + e.getPlayer().getUniqueId() + ".name", e.getPlayer().getName());
			DataHandler.getInstance().getFriendData().set("players." + e.getPlayer().getUniqueId() + ".requests", true);
			
			List<String> empty = new ArrayList<String>();
			
			// Save the data
			DataHandler.getInstance().saveData();
			
			// Can you set it to an empty list?
			DataHandler.getInstance().getFriendData().set("players." + e.getPlayer().getUniqueId() + ".friends", empty);
			
			// Once, we have inputted the new data into the database, we want to create local data in memory
			PlayerManager.get().playerDataList().add(new PlayerData(e.getPlayer()));
		} else {
			List<String> friendsFound = (List<String>) DataHandler.getInstance().getFriendData().getList("players." + e.getPlayer().getUniqueId() + ".friends");
			
			PlayerManager.get().playerDataList().add(new PlayerData(e.getPlayer(), friendsFound));
		}
	}
	
	@EventHandler
	public void friendJoin(PlayerJoinEvent e) {
		List<Friend> friends = PlayerManager.get().getPlayerData(e.getPlayer().getName()).getFriends();
		
		for(Friend f : friends) {
			if(f.isOnline()) {
				Bukkit.getPlayer(f.getName()).sendMessage(ChatColor.AQUA + "Your friend " + e.getPlayer().getName() + " has joined.");
			}
		}
	}
}
