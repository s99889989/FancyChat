package com.daxton.fancychat.config;

import com.daxton.fancychat.FancyChat;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.md_5.bungee.api.ChatColor.COLOR_CHAR;

public class PlayerData {

    public static Map<String, String[]> color_Map = new HashMap<>();

    public static void execute(Player player){
        String uuidString = player.getUniqueId().toString();
        FileConfiguration dataConfig = FileConfig.config_Map.get("PlayerData.yml");
        dataConfig.set(uuidString+".Name", player.getName());
        if(!dataConfig.contains(uuidString+".ChatColor")){
            dataConfig.set(uuidString+".ChatColor","FFFFFF");
        }
        File file = new File(FancyChat.fancyChat.getDataFolder(), "PlayerData.yml");
        try {
            dataConfig.save(file);
        }catch (IOException exception){
            exception.printStackTrace();
        }
        setColor(player);
    }

    public static void setColor(Player player){
        String uuidString = player.getUniqueId().toString();
        FileConfiguration dataConfig = FileConfig.config_Map.get("PlayerData.yml");
        String colorString = dataConfig.getString(uuidString+".ChatColor");
        to:
        if(colorString != null && colorString.length() == 6){
            String[] out = new String[6];
            int i = 0;
            for(Character s : colorString.toCharArray()){
                if(!checkHex(s.toString())){
                    color_Map.put(uuidString, new String[]{"F","F","F","F","F","F"});
                    break to;
                }
                out[i++] = s.toString();
            }
            color_Map.put(uuidString, out);
        }else {
            color_Map.put(uuidString, new String[]{"F","F","F","F","F","F"});
        }
    }
    //卻認為16進位單字
    public static boolean checkHex(String in){
        in = in.toUpperCase();
        if(in.equals("1") || in.equals("2") || in.equals("3") || in.equals("4") || in.equals("5") || in.equals("6") || in.equals("7") || in.equals("8")){
           return true;
        }
        return in.equals("9") || in.equals("A") || in.equals("B") || in.equals("C") || in.equals("D") || in.equals("E") || in.equals("F") || in.equals("0");
    }

    public static String getColor(Player player){
        String uuidString = player.getUniqueId().toString();
        String output = "";
        FileConfiguration dataConfig = FileConfig.config_Map.get("PlayerData.yml");
        String colorString = dataConfig.getString(uuidString+".ChatColor");
        char[] colorStringArray = colorString.toCharArray();
        if(colorStringArray.length == 6){
            String key1 = String.valueOf(colorStringArray[0]);
            String key2 = String.valueOf(colorStringArray[1]);
            String key3 = String.valueOf(colorStringArray[2]);
            String key4 = String.valueOf(colorStringArray[3]);
            String key5 = String.valueOf(colorStringArray[4]);
            String key6 = String.valueOf(colorStringArray[5]);
            //FancyChat.fancyChat.getLogger().info(key1+" : "+key2+" : "+key3+" : "+key4+" : "+key5+" : "+key6);
            output = "#" + key1 + key2 + key3 + key4 + key5 + key6;
            //FancyChat.fancyChat.getLogger().info(translateHexColorCodes("\\{","\\}","{123456}TEST{12AB56}TEST"));
        }
        return output;
    }

    public static String translateHexColorCodes(String startTag, String endTag, String message) {
        final Pattern hexPattern = Pattern.compile(startTag + "([A-Fa-f0-9]{6})" + endTag);
        Matcher matcher = hexPattern.matcher(message);
        StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);
        while (matcher.find()) {
            String group = matcher.group(1);
            //FancyChat.fancyChat.getLogger().info(group);
            matcher.appendReplacement(buffer, COLOR_CHAR + "x"
                    + COLOR_CHAR + group.charAt(0) + COLOR_CHAR + group.charAt(1)
                    + COLOR_CHAR + group.charAt(2) + COLOR_CHAR + group.charAt(3)
                    + COLOR_CHAR + group.charAt(4) + COLOR_CHAR + group.charAt(5)
            );
        }
        return matcher.appendTail(buffer).toString();
    }

}
