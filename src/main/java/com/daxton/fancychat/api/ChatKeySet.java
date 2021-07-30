package com.daxton.fancychat.api;

import com.daxton.fancychat.FancyChat;
import com.daxton.fancychat.config.FileConfig;
import com.daxton.fancycore.api.config.SearchConfigFile;
import com.daxton.fancycore.api.config.SearchConfigMap;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChatKeySet {

    public static Map<String, Map<String, String>> chat_Component_Map = new ConcurrentHashMap<>();

    public static void execute(){
        SearchConfigMap.configList(FileConfig.config_Map ,"Message/").forEach(config -> {
            SearchConfigFile.sectionList(config, "").forEach(key -> {
                Map<String, String> key_Map = new ConcurrentHashMap<>();
                if(config.contains(key+".Text")){
                    String text = config.getString(key+".Text");
                    key_Map.put("Text", text);
                }
                if(config.contains(key+".Color")){
                    String color = config.getString(key+".Color");
                    key_Map.put("Color", color);
                }
                if(config.contains(key+".Bold")){
                    String bold = config.getString(key+".Bold");
                    key_Map.put("Bold", bold);
                }
                if(config.contains(key+".ClickEvent.Action")){
                    String clickEventAction = config.getString(key+".ClickEvent.Action");
                    key_Map.put("ClickEventAction", clickEventAction);
                }
                if(config.contains(key+".ClickEvent.Text")){
                    String clickEventText = config.getString(key+".ClickEvent.Text");
                    key_Map.put("ClickEventText", clickEventText);
                }
                if(config.contains(key+".HoverEvent.Action")){
                    String hoverEventAction = config.getString(key+".HoverEvent.Action");
                    key_Map.put("HoverEventAction", hoverEventAction);
                }
                if(config.contains(key+".HoverEvent.Text")){
                    String hoverEventText = config.getString(key+".HoverEvent.Text");
                    key_Map.put("HoverEventText", hoverEventText);
                }
                chat_Component_Map.put(key, key_Map);
            });
        });

    }

    public static void reload(){
        chat_Component_Map.clear();
        execute();
    }

}
