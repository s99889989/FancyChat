package com.daxton.fancychat.api;

import com.daxton.fancychat.FancyChat;
import com.daxton.fancycore.api.character.stringconversion.ConversionMain;
import com.daxton.fancycore.api.character.stringconversion.ConversionMessage;
import com.daxton.fancycore.api.other.CountWords;
import com.daxton.fancycore.nms.ItemBaseComponent;
import com.daxton.fancycore.nms.v1_16_R3.EntityJson;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
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
                    allChat.addExtra(toComponent(player,s));
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
