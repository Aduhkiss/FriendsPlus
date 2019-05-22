package me.atticusthecoder.friendsplus.object;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import me.atticusthecoder.friendsplus.data.DataHandler;

public class PlayerData {
	
	Player player;
	List<Friend> friends;
	List<FriendRequest> friendRequests = new ArrayList<FriendRequest>();
	boolean requestsEnabled;
	
	public PlayerData(Player player) {
		this.player = player;
		this.requestsEnabled = true;
	}
	
	public PlayerData(Player player, List<String> theFriends) {
		this.requestsEnabled = true;
		this.player = player;
		
		friends = new ArrayList<Friend>();
		
		for(String str : theFriends) {
			Friend f = new Friend(str);
			friends.add(f);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void updateData() {
		friends = new ArrayList<Friend>();
		List<String> friendsFound = (List<String>) DataHandler.getInstance().getFriendData().getList("players." + player.getUniqueId().toString() + ".friends");
		for(String str : friendsFound) {
			Friend f = new Friend(str);
			friends.add(f);
		}
	}
	
	public void disableRequests() {
		requestsEnabled = false;
	}
	
	public void enableRequests() {
		requestsEnabled = true;
	}
	
	public boolean getRequestsEnabled() {
		return requestsEnabled;
	}
	
	public List<Friend> getFriends() {
		return friends;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void addFriendRequest(String name) {
		friendRequests.add(new FriendRequest(new Friend(name), this));
	}
	
	public List<FriendRequest> getFriendRequests() {
		List<FriendRequest> _requests = new ArrayList<FriendRequest>();
		for(FriendRequest fr : friendRequests) {
			if(fr.valid) {
				_requests.add(fr);
			}
		}
		return _requests;
	}
}
