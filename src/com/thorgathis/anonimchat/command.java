package com.thorgathis.anonimchat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.thorgathis.anonimchat.main.anonPlayers;
import static com.thorgathis.anonimchat.main.prefix;

public class command implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(label.equalsIgnoreCase("anonchat") && (sender.hasPermission("anonim.use") || sender.isOp())){
            if(sender instanceof Player){
                Player p = (Player) sender;
                if(args.length == 0){
                    if(anonPlayers.containsKey(p)){
                        p.sendMessage(prefix + ChatColor.RED + "Вы выключили анонимный режим");
                        anonPlayers.remove(p);
                    }else{
                        p.sendMessage(prefix  + ChatColor.GREEN + "Вы включили анонимный режим");
                        anonPlayers.put(p, null);
                    }
                }else {
                    p.sendMessage(prefix + ChatColor.RED + "Слишком много аргументов! Используйте: /anonchat");
                }
            }else{
                sender.sendMessage(prefix + ChatColor.RED + "Только игроки могут использовать эту команду!");
            }
            return true;
        } else {
            sender.sendMessage(prefix + ChatColor.RED + "У вас недостаточно прав для выполнения этой команды. Необходимы права " + ChatColor.DARK_RED + "anonim.use");
            return true;
        }
    }
}
