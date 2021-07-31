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
        //設定檔
        FileConfig.execute();
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

//    public static void copy(){
//        FancyChat.fancyChat.getLogger().info("複製貼上");
//        Toolkit toolkit = Toolkit.getDefaultToolkit();
//        Clipboard clipboard = toolkit.getSystemClipboard();
//        StringSelection strSel = new StringSelection("TEST MESSAGE");
//        clipboard.setContents(strSel, null);
//
//        Transferable pasteData = clipboard.getContents(clipboard);
//
//        if (pasteData != null) {
//            try {
//                if (pasteData.isDataFlavorSupported(DataFlavor.stringFlavor)) {
//                    String s = (String)(pasteData.getTransferData(DataFlavor.stringFlavor));
//                    //output s
//                }
//            } catch (UnsupportedFlavorException ex) {
//                ex.printStackTrace();
//            } catch (IOException ex1) {
//                ex1.printStackTrace();
//            }
//        }
//    }

}
