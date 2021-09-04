package com.daxton.fancychat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import static com.daxton.fancychat.config.FileConfig.languageConfig;

public class DependPlugins {
    //依賴插件
    public static boolean depend(){

        FancyChat fancyChat = FancyChat.fancyChat;

        if (Bukkit.getServer().getPluginManager().getPlugin("FancyCore") != null && Bukkit.getPluginManager().isPluginEnabled("FancyCore")){
            fancyChat.getLogger().info(languageConfig.getString("LogMessage.LoadFancyCore"));
        }else {
            languageConfig.getStringList("LogMessage.UnLoadFancyCore").forEach(s -> fancyChat.getLogger().info(s));
            return false;
        }

        return true;
    }

}
