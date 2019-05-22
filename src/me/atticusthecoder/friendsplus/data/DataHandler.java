package me.atticusthecoder.friendsplus.data;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.atticusthecoder.friendsplus.FriendsPlus;

public class DataHandler {
	private static DataHandler instance = new DataHandler();
	
	public static DataHandler getInstance() {
		return instance;
	}
	
	public DataHandler() {
		this.friendDataFile = new File(FriendsPlus.get().getDataFolder(), "friendData.yml");
		if(!this.friendDataFile.exists()) {
			try {
				this.friendDataFile.getParentFile().mkdirs();
				this.friendDataFile.createNewFile();
			} catch(IOException ex) {
				ex.printStackTrace();
			}
		}
		this.friendData = YamlConfiguration.loadConfiguration(this.friendDataFile);
	}
	
	private File friendDataFile;
	private FileConfiguration friendData;
	
	public FileConfiguration getFriendData() {
		return this.friendData;
	}
	
	public void saveData() {
		try {
			this.friendData.save(friendDataFile);
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}
