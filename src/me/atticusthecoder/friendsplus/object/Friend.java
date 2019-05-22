package me.atticusthecoder.friendsplus.object;

import org.bukkit.Bukkit;

public class Friend {
	String name;
	boolean isOnline;
	
	public Friend(String name) {
		this.name = name;
		if(Bukkit.getPlayer(name) == null) {
			isOnline = false;
		} else {
			isOnline = true;
		}
	}
	
	public String getName() {
		return name;
	}
	
	public void setOnline(boolean a) {
		isOnline = a;
	}
	
	public boolean isOnline() {
		return isOnline;
	}
}
