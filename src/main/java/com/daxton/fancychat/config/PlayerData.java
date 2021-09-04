package com.daxton.fancychat.config;

import com.daxton.fancychat.FancyChat;
import com.daxton.fancycore.api.character.conversion.ColorConversion;
import com.daxton.fancycore.api.judgment.NumberJudgment;
import com.daxton.fancycore.nms.NMSVersion;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PlayerData {

    public static Map<String, String[]> color_Map = new HashMap<>();

    //建立初始玩家設定檔
    public static void execute(Player player){
        String uuidString = player.getUniqueId().toString();
        FileConfiguration dataConfig = FileConfig.config_Map.get("PlayerData.yml");
        dataConfig.set(uuidString+".Name", player.getName());
        if(!dataConfig.contains(uuidString+".ChatColor")){
            dataConfig.set(uuidString+".ChatColor","F");
        }
        if(!dataConfig.contains(uuidString+".ChatRGBColor")){
            dataConfig.set(uuidString+".ChatRGBColor","FFFFFF");
        }
        File file = new File(FancyChat.fancyChat.getDataFolder(), "PlayerData.yml");
        try {
            dataConfig.save(file);
        }catch (IOException exception){
            exception.printStackTrace();
        }
        setColor(player);
    }

    //把玩家設定顏色存到Map
    public static void setColor(Player player){
        String uuidString = player.getUniqueId().toString();
        FileConfiguration dataConfig = FileConfig.config_Map.get("PlayerData.yml");

        if(NMSVersion.compareClientVersion(player, "1.16")){
            String colorString = dataConfig.getString(uuidString+".ChatRGBColor");

            if(colorString != null && colorString.length() == 6 && NumberJudgment.isHexNumber(colorString)){
                String[] out = new String[6];
                int i = 0;
                for(Character s : colorString.toCharArray()){
                    out[i++] = s.toString();
                }
                color_Map.put(uuidString, out);
            }else {
                color_Map.put(uuidString, new String[]{"F","F","F","F","F","F"});
            }
        }else {
            String colorString = dataConfig.getString(uuidString+".ChatColor");
            if(colorString != null && colorString.length() == 1 && NumberJudgment.isHexNumber(colorString)){
                String[] out = new String[1];
                out[0] = colorString;
                color_Map.put(uuidString, out);
            }else {
                color_Map.put(uuidString, new String[]{"F"});
            }
        }

    }

    //發送消息時，讀取顏色
    public static ChatColor getColor(Player player){
        String uuidString = player.getUniqueId().toString();
        ChatColor output;
        FileConfiguration dataConfig = FileConfig.config_Map.get("PlayerData.yml");
        if(NMSVersion.compareClientVersion(player, "1.16")){
            String colorString = dataConfig.getString(uuidString+".ChatRGBColor");
            if(colorString != null && colorString.toCharArray().length == 6 && NumberJudgment.isHexNumber(colorString)){
                char[] colorStringArray = colorString.toCharArray();
                String key1 = String.valueOf(colorStringArray[0]);
                String key2 = String.valueOf(colorStringArray[1]);
                String key3 = String.valueOf(colorStringArray[2]);
                String key4 = String.valueOf(colorStringArray[3]);
                String key5 = String.valueOf(colorStringArray[4]);
                String key6 = String.valueOf(colorStringArray[5]);
                output = ChatColor.of("#" + key1 + key2 + key3 + key4 + key5 + key6);
            }else {
                output = ChatColor.of("#FFFFFF");
            }
        }else {
            String colorString = dataConfig.getString(uuidString+".ChatColor");
            if(colorString != null && colorString.toCharArray().length == 1 && NumberJudgment.isHexNumber(colorString)){
                output = ColorConversion.hexToColor(colorString);
            }else {
                output = ColorConversion.hexToColor("F");
            }
        }
        return output;
    }

    //發送消息時，讀取顏色
    public static String getColorString(Player player){
        String uuidString = player.getUniqueId().toString();
        String output;
        FileConfiguration dataConfig = FileConfig.config_Map.get("PlayerData.yml");
        if(NMSVersion.compareClientVersion(player, "1.16")){
            String colorString = dataConfig.getString(uuidString+".ChatRGBColor");
            if(colorString != null && colorString.toCharArray().length == 6 && NumberJudgment.isHexNumber(colorString)){
                char[] colorStringArray = colorString.toCharArray();
                String key1 = String.valueOf(colorStringArray[0]);
                String key2 = String.valueOf(colorStringArray[1]);
                String key3 = String.valueOf(colorStringArray[2]);
                String key4 = String.valueOf(colorStringArray[3]);
                String key5 = String.valueOf(colorStringArray[4]);
                String key6 = String.valueOf(colorStringArray[5]);
                output = "{#" + key1 + key2 + key3 + key4 + key5 + key6+"}";
            }else {
                output = "{#FFFFFF}";
            }
        }else {
            String colorString = dataConfig.getString(uuidString+".ChatColor");
            if(colorString != null && colorString.toCharArray().length == 1 && NumberJudgment.isHexNumber(colorString)){
                output = "{#"+colorString+"}";
            }else {
                output = "{#F}";
            }
        }
        return output;
    }

}
