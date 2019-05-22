package me.atticusthecoder.friendsplus.handler;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.atticusthecoder.friendsplus.api.PlayerManager;
import me.atticusthecoder.friendsplus.data.DataHandler;
import me.atticusthecoder.friendsplus.object.Friend;
import me.atticusthecoder.friendsplus.object.PlayerData;

public class PlayerQuit implements Listener {
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		// When players leave the game, we want to save their data
		PlayerData data = PlayerManager.get().getPlayerData(e.getPlayer().getName());
		List<String> friendsStr = new ArrayList<String>();
		
		for(Friend f : data.getFriends()) {
			friendsStr.add(f.getName());
		}
		
		boolean requests = PlayerManager.get().getPlayerData(e.getPlayer().getName()).getRequestsEnabled();
		DataHandler.getInstance().getFriendData().set("players." + e.getPlayer().getUniqueId() + ".requests", requests);
		
		DataHandler.getInstance().getFriendData().set("players." + e.getPlayer().getUniqueId() + ".friends", friendsStr);
		Bukkit.getLogger().info("[Friends+] Saved friend data for " + e.getPlayer().getName() + ".");
	}

}
