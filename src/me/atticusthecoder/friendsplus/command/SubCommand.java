package me.atticusthecoder.friendsplus.command;

import org.bukkit.entity.Player;

public abstract class SubCommand {
	public abstract void execute(Player caller, String[] args);
}
