package com.daxton.fancychat.api;

import com.daxton.fancychat.FancyChat;
import com.daxton.fancychat.config.PlayerData;
import com.daxton.fancycore.api.character.conversion.ColorConversion;
import com.daxton.fancycore.nms.ItemBaseComponent;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatConversion {

    //聊天訊息處理
    public static TextComponent valueOf(Player player, String inputString){

        //顏色代碼轉換
        //inputString = ConversionMessage.valueOf(inputString);
        inputString = PlayerData.getColorString(player) + inputString;
        if(inputString.contains("[item]")){

            inputString = inputString.replace("[item]", "`[item]`");

            TextComponent allChat = new TextComponent("");
            String[] outputStringArray = inputString.split("`");
            Arrays.stream(outputStringArray).forEach(s -> {
                FancyChat.fancyChat.getLogger().info(s);
                if(s.equals("[item]")){
                    allChat.addExtra(toItem(player));
                }else {
                    allChat.addExtra(toColor(s));
                }
            });
            return allChat;
        }else {
            return toColor(inputString);
        }
        //return new TextComponent(inputString);
    }

    //物品顯示設定
    public static TextComponent toItem(Player player){
        TextComponent textComponent = new TextComponent();
        textComponent.setText("[item]");
        textComponent.setColor(ChatColor.BLUE);
        if(player.getInventory().getItemInMainHand().getType() != Material.AIR){
            ItemStack itemStack = player.getInventory().getItemInMainHand();
            textComponent.setHoverEvent( new HoverEvent(HoverEvent.Action.SHOW_ITEM, ItemBaseComponent.to(itemStack)));
        }
        return textComponent;
    }
    //聊天字串加上顏色
    public static TextComponent toColor(String input){
        TextComponent textComponent = new TextComponent();
        input = translateHexColorCodes("\\{#" ,"\\}", input);
        input = translateColorCodes("\\{#" ,"\\}", input);
        if(input.contains("`")){
            String[] xArray = input.split("`");
            for(String s : xArray){
                String[] sArray = s.split("\\|");
                if(sArray.length == 2){
                    TextComponent textAdd = new TextComponent(sArray[1]);
                    if(sArray[0].length() == 6){
                        textAdd.setColor(ChatColor.of("#"+sArray[0]));
                    }
                    if(sArray[0].length() == 1){
                        textAdd.setColor(ColorConversion.hexToColor(sArray[0]));
                    }
                    textComponent.addExtra(textAdd);
                }else {
                    Pattern pattern = Pattern.compile("([A-Fa-f0-9]{6})"+"\\|");
                    Matcher matcher = pattern.matcher(s);
                    if(matcher.find()){
                        FancyChat.fancyChat.getLogger().info(s);
                    }else {
                        textComponent.addExtra(new TextComponent(s));
                    }
                }
            }
        }else {
            textComponent.addExtra(input);
        }

        return textComponent;
    }

    //顏色轉換§
    public static String translateColorCodes(String startTag, String endTag, String message) {
        final Pattern hexPattern = Pattern.compile(startTag + "([A-Fa-f0-9]{1})" + endTag);
        Matcher matcher = hexPattern.matcher(message);
        StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);
        while (matcher.find()) {
            String group = matcher.group(1);
            //System.out.println(group);
            matcher.appendReplacement(buffer, "`"+group+"|");
        }

        return matcher.appendTail(buffer).toString();
    }

    //16進制顏色轉換§
    public static String translateHexColorCodes(String startTag, String endTag, String message) {
        final Pattern hexPattern = Pattern.compile(startTag + "([A-Fa-f0-9]{6})" + endTag);
        Matcher matcher = hexPattern.matcher(message);
        StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);
        while (matcher.find()) {
            String group = matcher.group(1);
            //System.out.println(group);
            matcher.appendReplacement(buffer, "`"+group+"|");
        }

        return matcher.appendTail(buffer).toString();
    }

}
