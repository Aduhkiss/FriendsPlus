package me.atticusthecoder.friendsplus;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.atticusthecoder.friendsplus.command.FriendCommand;
import me.atticusthecoder.friendsplus.data.DataHandler;
import me.atticusthecoder.friendsplus.handler.PlayerJoin;
import me.atticusthecoder.friendsplus.handler.PlayerQuit;

public class FriendsPlus extends JavaPlugin {
	
	private static FriendsPlus me;
	
	@Override
	public void onEnable() {
		me = this;
		
		// Setup anything to do with the config system
		getConfig().options().copyDefaults(true);
		getConfig().options().copyHeader(true);
		saveDefaultConfig();
		
		// Simple data saver system.
		int sec = 300;
		BukkitRunnable runnable = new BukkitRunnable() {
			@Override
			public void run() {
				Bukkit.getLogger().info("[Friends+] Saving player data...");
				DataHandler.getInstance().saveData();
			}
		};
		runnable.runTaskTimer(this, 20L * sec, 20L * sec);
		
		// Register handlers
		Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerQuit(), this);
		
		// Register commands
		getCommand("friend").setExecutor(new FriendCommand());
	}
	
	@Override
	public void onDisable() {
		me = null;
		
		// Save all friend data
		DataHandler.getInstance().saveData();
	}
	
	public static FriendsPlus get() {
		return me;
	}
}
