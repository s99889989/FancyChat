package com.daxton.fancychat.command;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class BungeeCommand extends Command {
    public  BungeeCommand(){
        super("hub","permission.hub",new String[0]);
    }
    @Override
    public void execute(CommandSender sender, String[] args){
        if(sender instanceof ProxiedPlayer){
            ProxiedPlayer player = (ProxiedPlayer) sender;
            if(!player.getServer().getInfo().getName().equalsIgnoreCase("hub")){
                ServerInfo target = ProxyServer.getInstance().getServerInfo("Lobby2");
                player.connect(target);
            }else{
                player.sendMessage(new ComponentBuilder("You are already connected to the Hub!").color(ChatColor.RED).create());
            }
        }else{
            sender.sendMessage(new ComponentBuilder("This command can only be run by a player!").color(ChatColor.RED).create());
        }
    }

}
