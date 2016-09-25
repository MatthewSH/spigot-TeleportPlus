package com.matthewhatcher.teleportplus;

import org.bukkit.plugin.java.JavaPlugin;

import com.matthewhatcher.teleportplus.Commands.TeleportCommands;

public class TeleportPlus extends JavaPlugin
{
	@Override
	public void onEnable() {
		this.getCommand("teleport").setExecutor(new TeleportCommands());
		this.getCommand("tp").setExecutor(new TeleportCommands());
		super.onEnable();
	}
}
