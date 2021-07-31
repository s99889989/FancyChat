package com.daxton.fancychat.api;

import com.daxton.fancychat.FancyChat;
import com.daxton.fancychat.config.FileConfig;
import com.daxton.fancycore.api.config.SearchConfigFile;
import com.daxton.fancycore.api.config.SearchConfigMap;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChatKeySet {

    public static Map<String, ButtomText> chat_Component_Map = new ConcurrentHashMap<>();

    //設定按鈕
    public static void execute(){
        SearchConfigMap.configList(FileConfig.config_Map ,"Message/").forEach(config -> {
            SearchConfigFile.sectionList(config, "").forEach(key -> {
                chat_Component_Map.put(key, new ButtomText(config, key));
            });
        });

    }
    //重新設定按鈕
    public static void reload(){
        chat_Component_Map.clear();
        execute();
    }

}
