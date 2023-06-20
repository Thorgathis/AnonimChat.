package com.thorgathis.anonimchat;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin implements Listener{

	public static main plugin;
	public static final String prefix = ChatColor.GOLD + "[Anonim Chat]" + ChatColor.RESET + " ";
	public static HashMap<Player, ArrayList<Block>> anonPlayers = new HashMap<Player, ArrayList<Block>>();

	public void onEnable(){
		this.getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		PluginManager pm = getServer().getPluginManager();
		Bukkit.getPluginCommand("anonchat").setExecutor(new command());
		pm.registerEvents(this, this);
	    
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerChat(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		if (anonPlayers.containsKey(p)){
			for(Player player : Bukkit.getOnlinePlayers()){
				if(player.hasPermission(("anonchat.use")) || p.isOp()){
					String format = ChatColor.translateAlternateColorCodes('&', getConfig().getString("format")) + " ";
					String player_prefix = ChatColor.GRAY + "(" + ChatColor.GRAY + ChatColor.stripColor(p.getDisplayName()) + ChatColor.GRAY +  ") ";
					e.setFormat(player_prefix + format + ChatColor.RESET + e.getMessage());
				}else{
					String f = getConfig().getString("format");
					e.setFormat(f + " " + e.getMessage());
				}
				e.setCancelled(true);
				player.sendMessage(e.getFormat());
			}
		}
	}

} 
