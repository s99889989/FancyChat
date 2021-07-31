package com.daxton.fancychat.task;


import com.daxton.fancychat.FancyChat;
import com.daxton.fancychat.api.ChatKeySet;
import com.daxton.fancychat.config.FileConfig;
import com.daxton.fancychat.listener.FancyChatListener;
import org.bukkit.configuration.file.FileConfiguration;

public class Start {

    //只在開服時執行的任務
    public static void execute(){
        //設定檔
        FileConfig.execute();
        //對話按鈕設置
        ChatKeySet.execute();
        FileConfiguration config = FileConfig.config_Map.get("config.yml");

        boolean bungeeCoreEnable = config.getBoolean("bungeecore.enable");
        if(bungeeCoreEnable){
            //通道監聽
            FancyChat.fancyChat.getServer().getMessenger().registerOutgoingPluginChannel(FancyChat.fancyChat, "BungeeCord");
            FancyChat.fancyChat.getServer().getMessenger().registerIncomingPluginChannel(FancyChat.fancyChat, "BungeeCord", new FancyChatListener());
        }
    }

}
