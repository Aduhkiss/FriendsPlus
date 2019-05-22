package me.atticusthecoder.friendsplus.api;

import java.util.ArrayList;
import java.util.List;

import me.atticusthecoder.friendsplus.object.FriendRequest;
import me.atticusthecoder.friendsplus.object.PlayerData;

public class PlayerManager {
	private static PlayerManager me;
	
	private List<PlayerData> playerData = new ArrayList<PlayerData>();
	
	public static PlayerManager get() {
		if(me == null) {
			me = new PlayerManager();
		}
		return me;
	}
	
	public List<PlayerData> playerDataList() {
		return playerData;
	}
	
	public PlayerData getPlayerData(String name) {
		for(PlayerData pd : playerData) {
			if(pd.getPlayer().getName().equalsIgnoreCase(name)) {
				return pd;
			}
		}
		return null;
	}
}
