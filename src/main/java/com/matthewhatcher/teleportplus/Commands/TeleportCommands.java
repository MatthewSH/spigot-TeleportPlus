package com.matthewhatcher.teleportplus.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportCommands implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
			if(label.equalsIgnoreCase("teleport") || label.equalsIgnoreCase("tp")) {
				if(args.length == 1) {
					String target = args[0];
					
					if(target.equalsIgnoreCase(".")) {
						if(canTPAll(player)) {
							teleportAll(player);
						} else {
							sender.sendMessage("[Teleport+] Sorry, but you don't have permission to do that.");
						}
					} else {
						if(canTP(player)) {
							Player playerToTP = Bukkit.getServer().getPlayer(target);
							if(Bukkit.getServer().getPlayer(target) != null) {
								teleport(playerToTP, player);
								sender.sendMessage("[Teleport+] Player has been teleported");
								return true;
							} else {
								sender.sendMessage("[Teleport+] That player is not online.");
								return true;
							}
						} else {
							sender.sendMessage("[Teleport+] Sorry, but you don't have permission to do that.");
						}
					}
					
				} else if (args.length == 2) {
					String target = args[0];
					
					
					if(target.equalsIgnoreCase(".")) {
						if(canTPAll(player)) {
							Player playerToTPTo = Bukkit.getServer().getPlayer(args[1]);
							
							if(playerToTPTo != null) {
								teleportAll(playerToTPTo);
							} else {
								sender.sendMessage("[Teleport+] That target player is not online.");
							}
							
						} else {
							sender.sendMessage("[Teleport+] Sorry, but you don't have permission to do that.");
						}
					} else {
						if(canTP(player)) {
							Player playerToTPTo = Bukkit.getServer().getPlayer(args[1]);
							Player playerToTP = Bukkit.getServer().getPlayer(target);
							
							if((playerToTP != null) && (playerToTPTo != null)) {
								teleport(playerToTP, playerToTPTo);
								sender.sendMessage("[Teleport+] Player has been teleported to " + playerToTPTo.getName());
								return true;
							} else {
								sender.sendMessage("[Teleport+] One or more of those players are not online.");
							}
						} else {
							sender.sendMessage("[Teleport+] Sorry, but you don't have permission to do that.");
						}
					}
					
				} else {
					sender.sendMessage("Usage: teleport <target> <player>");
				}
				
				return true;
			}
		} else {
			sender.sendMessage("You must be a player to perform the teleport command.");
			return true;
		}
		
		return false;
	}
	
	private void teleport(Player target, Player sender) {
		Location location = new Location(sender.getWorld(), sender.getLocation().getX(), sender.getLocation().getY(), sender.getLocation().getZ());
		target.teleport(location);
		target.sendMessage("[Teleport+] You have been teleported.");
	}
	
	private void teleportAll(Player sender) {
		/*Collection<?> players = Arrays.asList(Bukkit.getServer().getOnlinePlayers());
		Location location = new Location(sender.getWorld(), sender.getLocation().getX(), sender.getLocation().getY(), sender.getLocation().getZ());
		
		for(Object playerObj : players) {
			Player player = (Player)playerObj;
			
			if(player.getName() != sender.getName()) {
				player.teleport(location);
				player.sendMessage("[Teleport+] You have been teleported.");
			}
		}*/
		
		Location location = new Location(sender.getWorld(), sender.getLocation().getX(), sender.getLocation().getY(), sender.getLocation().getZ());
		
		for(Player player : Bukkit.getServer().getOnlinePlayers()) {
			if(player.getName() != sender.getName()) {
				player.teleport(location);
				player.sendMessage("[Teleport+] You have been teleported.");
			}
		}
	}
	
	private boolean canTPAll(Player player) {
		return player.hasPermission("teleportplus.teleportall");
	}
	
	private boolean canTP(Player player) {
		return player.hasPermission("teleportplus.teleport");
	}
}
