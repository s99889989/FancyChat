package com.daxton.fancychat.command;


import com.daxton.fancychat.FancyChat;
import com.daxton.fancychat.bungee.task.OutTask;
import com.daxton.fancychat.config.FileConfig;
import com.daxton.fancychat.gui.MainMenu;
import com.daxton.fancychat.task.Reload;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MainCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args){
        if(sender instanceof Player && !sender.isOp()){
            return true;
        }

        if(args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            Reload.execute();
            String reloadString = FileConfig.languageConfig.getString("Language.Reload");
            if(sender instanceof Player && reloadString != null){
                Player player = (Player) sender;
                player.sendMessage(reloadString);
            }
            FancyChat.fancyChat.getLogger().info(reloadString);
        }
        if(args.length == 1 && args[0].equalsIgnoreCase("color")){
            if(sender instanceof Player){
                Player player = (Player) sender;
                MainMenu.open(player);
            }

        }
        if(args.length == 2 && args[0].equalsIgnoreCase("server")){
            if(sender instanceof Player){
                Player player = (Player) sender;
                OutTask.serverTp(player, args[1]);
            }

        }
        return true;
    }



}
