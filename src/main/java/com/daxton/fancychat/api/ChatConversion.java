package com.daxton.fancychat.api;

import com.daxton.fancychat.FancyChat;
import com.daxton.fancycore.api.character.stringconversion.ConversionMain;
import com.daxton.fancycore.api.character.stringconversion.ConversionMessage;
import com.daxton.fancycore.api.other.CountWords;
import com.daxton.fancycore.nms.ItemBaseComponent;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Map;

public class ChatConversion {

    public static TextComponent valueOf(Player player, String inputString){ //LivingEntity self, LivingEntity target,
        String outputString = inputString;
        if(outputString.isEmpty()){
            new TextComponent("");
        }
        outputString = ConversionMain.valueOf(player, null, outputString);

        int num1 = CountWords.count(outputString, "\\{");
        int num2 = CountWords.count(outputString, "\\}");
        to:
        if(num1 == num2){
            TextComponent allChat = new TextComponent("");
            int k = 0;
            for(int i = 0; i < num1 ; i++){
                int head = outputString.indexOf("{", k);
                int tail = outputString.indexOf("}",head+1);
                outputString = outputString.replace(inputString.substring(head,tail+1), "`"+inputString.substring(head,tail+1)+"`");
                k = tail+2;
            }
            String[] outputStringArray = outputString.split("`");
            Arrays.stream(outputStringArray).forEach(s -> {

                if(s.contains("{") || s.contains("}")){
                    allChat.addExtra(toComponent(s));
                }else {
                    allChat.addExtra(s);
                }
            });

            return allChat;
        }

        return new TextComponent(inputString);
    }
    public static TextComponent valueOf2(Player player, String inputString){

        //顏色代碼轉換
        inputString = ConversionMessage.valueOf(inputString);

        if(inputString.contains("[item]")){

            inputString = inputString.replace("[item]", "`[item]`");

            TextComponent allChat = new TextComponent("");
            String[] outputStringArray = inputString.split("`");
            Arrays.stream(outputStringArray).forEach(s -> {
                if(s.equals("[item]")){
                    allChat.addExtra(toItem(player));
                }else {
                    allChat.addExtra(s);
                }
            });
            return allChat;
        }
        return new TextComponent(inputString);
    }
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

    public static TextComponent toComponent(String messageString){
        String outputString = messageString.replace("{","").replace("}","");
        TextComponent textComponent = new TextComponent(outputString);
        if(ChatKeySet.chat_Component_Map.get(outputString) != null) {
            Map<String, String> key = ChatKeySet.chat_Component_Map.get(outputString);
            if(key.get("Text") != null){
                String text = key.get("Text");
                textComponent.setText(text);
            }
            if(key.get("Color") != null){
                String color = key.get("Color");
                try {
                    ChatColor chatColor = ChatColor.valueOf(color);
                    textComponent.setColor(chatColor);
                }catch (Exception exception){
                    textComponent.setColor(ChatColor.WHITE);
                }
            }
            if(key.get("Bold") != null){
                String bold = key.get("Bold");
                textComponent.setBold(Boolean.parseBoolean(bold));
            }
            try {
                if(key.get("ClickEventText") != null){
                    String clickEventText = key.get("ClickEventText");
                    if(key.get("ClickEventAction") != null){
                        String clickEventAction = key.get("ClickEventAction");
                        textComponent.setClickEvent(new ClickEvent(Enum.valueOf(ClickEvent.Action.class, clickEventAction), clickEventText));
                    }else {
                        textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, clickEventText));
                    }
                }else {
                    if(key.get("ClickEventAction") != null){
                        String clickEventAction = key.get("ClickEventAction");
                        textComponent.setClickEvent(new ClickEvent(Enum.valueOf(ClickEvent.Action.class, clickEventAction), ""));
                    }
                }

                if(key.get("HoverEventText") != null){
                    String hoverEventText = key.get("HoverEventText");
                    if(key.get("HoverEventAction") != null){
                        String hoverEventAction = key.get("HoverEventAction");
                        textComponent.setHoverEvent( new HoverEvent(Enum.valueOf(HoverEvent.Action.class, hoverEventAction), new Text("test1\ntest2")));
                    }else {
                        textComponent.setHoverEvent( new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(hoverEventText)));

                    }
                }else {
                    if(key.get("HoverEventAction") != null){
                        String hoverEventAction = key.get("HoverEventAction");
                        textComponent.setHoverEvent( new HoverEvent(Enum.valueOf(HoverEvent.Action.class, hoverEventAction), new Text("")));
                    }
                }
            }catch (Exception exception){
                //
            }
            return textComponent;
        }

        return textComponent;
    }



}
