package com.daxton.fancychat.api;

import com.daxton.fancycore.api.character.stringconversion.ConversionMain;
import com.daxton.fancycore.api.other.CountWords;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.entity.Player;

public class PrefixSuffixConversion {

    //前後輟處理
    public static TextComponent valueOf(Player player, String inputString){
        String outputString = inputString;
        if(outputString.isEmpty()){
            new TextComponent("");
        }
        outputString = ConversionMain.valueOf(player, null, outputString, false);

        int num1 = CountWords.count(outputString, "\\{");
        int num2 = CountWords.count(outputString, "\\}");
        if(num1 == num2){
            TextComponent allChat = new TextComponent("");
            int k = 0;
            for(int i = 0; i < num1 ; i++){
                int head = outputString.indexOf("{", k);
                int tail = outputString.indexOf("}",head+1);
                outputString = outputString.replace(outputString.substring(head,tail+1), "`"+outputString.substring(head,tail+1)+"`");
                k = tail;
            }
            String[] outputStringArray = outputString.split("`");
            ChatColor chatColor = null;
            for(String s : outputStringArray){
                if(s.length() == 9 && s.startsWith("{#") && s.endsWith("}")){
                    chatColor = ChatColor.of(s.replace("{","").replace("}",""));
                }else if(s.startsWith("{") && s.endsWith("}")){
                    TextComponent textComponent = toComponent(player,s);
                    if(chatColor != null){
                        chatColor = null;
                    }
                    allChat.addExtra(textComponent);
                }else {
                    TextComponent textComponent = new TextComponent(s);
                    if(chatColor != null){
                        textComponent.setColor(chatColor);
                        chatColor = null;
                    }
                    allChat.addExtra(textComponent);
                }
            }
//            Arrays.stream(outputStringArray).forEach(s -> {
//                FancyChat.fancyChat.getLogger().info(s);
//                if(s.startsWith("{#") || s.endsWith("}")){
//
//                }else if(s.startsWith("{") || s.startsWith("}")){
//                    allChat.addExtra(toComponent(player,s));
//                }else {
//                    allChat.addExtra(s);
//                }
//            });

            return allChat;
        }

        return new TextComponent(inputString);
    }


    //按鈕設定
    public static TextComponent toComponent(Player player, String messageString){
        String outputString = messageString.replace("{","").replace("}","");
        TextComponent textComponent = new TextComponent(outputString);
        if(ChatKeySet.chat_Component_Map.get(outputString) != null) {
            ButtomText buttomText = ChatKeySet.chat_Component_Map.get(outputString);
            textComponent.setText(buttomText.getText(player));
            textComponent.setColor(buttomText.getChatColor());
            textComponent.setBold(buttomText.isBold());
            textComponent.setClickEvent(new ClickEvent(buttomText.getClickAction(), buttomText.getClickText(player)));
            textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(buttomText.getHoveText(player))));
            //textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ENTITY, EntityJson.to(player)));

            //new ComponentBuilder(id).create();
        }
        return textComponent;
    }

}
