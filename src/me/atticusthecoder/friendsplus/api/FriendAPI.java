package me.atticusthecoder.friendsplus.api;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;

import me.atticusthecoder.friendsplus.data.DataHandler;
import me.atticusthecoder.friendsplus.object.Friend;

public class FriendAPI {
	private static FriendAPI api;
	
	public static FriendAPI getApi() {
		if(api == null) {
			api = new FriendAPI();
		}
		return api;
	}
	
	public List<Friend> getFriends(String player) {
		UUID uuid = Bukkit.getOfflinePlayer(player).getUniqueId();
		return (List<Friend>) DataHandler.getInstance().getFriendData().get("players." + uuid.toString() + ".friends");
	}
	
	public void addFriend(String friend1, String friend2) {
		UUID uuid1 = Bukkit.getOfflinePlayer(friend1).getUniqueId();
		UUID uuid2 = Bukkit.getOfflinePlayer(friend2).getUniqueId();
		
		List<String> currentFriends = new ArrayList<String>();
		currentFriends = (List<String>) DataHandler.getInstance().getFriendData().getList("players." + uuid1.toString() + ".friends");
		
		currentFriends.add(friend2);
		DataHandler.getInstance().getFriendData().set("players." + uuid1.toString() + ".friends", currentFriends);
		
		// Then do the same for the second person
		
		List<String> currentFriends2 = new ArrayList<String>();
		currentFriends2 = (List<String>) DataHandler.getInstance().getFriendData().getList("players." + uuid2.toString() + ".friends");
		
		currentFriends2.add(friend1);
		DataHandler.getInstance().getFriendData().set("players." + uuid2.toString() + ".friends", currentFriends2);
	}
	
	public boolean areFriends(String friend1, String friend2) {
		UUID uuid = Bukkit.getOfflinePlayer(friend1).getUniqueId();
		List<String> friends = new ArrayList<String>();
		
		friends = (List<String>) DataHandler.getInstance().getFriendData().getList("players." + uuid.toString() + ".friends");
		
		for(String str : friends) {
			if(str.equalsIgnoreCase(friend2)) {
				return true;
			}
		}
		return false;
	}
	
	public void removeFriend(String remover, String friend) {
		if(!areFriends(remover, friend)) return;
		UUID uuid1 = Bukkit.getOfflinePlayer(remover).getUniqueId();
		UUID uuid2 = Bukkit.getOfflinePlayer(friend).getUniqueId();
		
		List<String> list1 = new ArrayList<String>();
		list1 = (List<String>) DataHandler.getInstance().getFriendData().getList("players." + uuid1.toString() + ".friends");
		
		list1.remove(friend);
		DataHandler.getInstance().getFriendData().set("players." + uuid1.toString() + ".friends", list1);
		DataHandler.getInstance().saveData();
		
		List<String> list2 = new ArrayList<String>();
		list2 = (List<String>) DataHandler.getInstance().getFriendData().getList("players." + uuid2.toString() + ".friends");
		
		list2.remove(remover);
		DataHandler.getInstance().getFriendData().set("players." + uuid2.toString() + ".friends", list2);
		DataHandler.getInstance().saveData();
	}
}
