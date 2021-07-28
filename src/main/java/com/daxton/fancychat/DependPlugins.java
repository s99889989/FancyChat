package com.daxton.fancychat;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class DependPlugins {

    public static boolean depend(){

        FancyChat fancyChat = FancyChat.fancyChat;

        if (Bukkit.getServer().getPluginManager().getPlugin("FancyCore") != null && Bukkit.getPluginManager().isPluginEnabled("FancyCore")){
            fancyChat.getLogger().info(ChatColor.GREEN+"Loaded FancyCore");
        }else {
            fancyChat.getLogger().severe("*** FancyCore is not installed or not enabled. ***");
            fancyChat.getLogger().severe("*** FancyItemsy will be disabled. ***");
            fancyChat.getLogger().severe("*** FancyCore未安裝或未啟用。 ***");
            fancyChat.getLogger().severe("*** FancyItems將被卸載。 ***");
            return false;
        }

        return true;
    }

}
