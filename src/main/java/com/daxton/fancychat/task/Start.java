package com.daxton.fancychat.task;


import com.daxton.fancychat.FancyChat;
import com.daxton.fancychat.api.ChatKeySet;
import com.daxton.fancychat.config.FileConfig;
import com.daxton.fancychat.listener.FancyChatListener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;

public class Start {

    //只在開服時執行的任務
    public static void execute(){

        //對話按鈕設置
        ChatKeySet.execute();
        FileConfiguration config = FileConfig.config_Map.get("config.yml");
        //Bungee通道註冊
        boolean bungeeCoreEnable = config.getBoolean("bungeecore.enable");
        if(bungeeCoreEnable){
            //通道監聽
            FancyChat.fancyChat.getServer().getMessenger().registerOutgoingPluginChannel(FancyChat.fancyChat, "BungeeCord");
            FancyChat.fancyChat.getServer().getMessenger().registerIncomingPluginChannel(FancyChat.fancyChat, "BungeeCord", new FancyChatListener());
        }

    }


}
