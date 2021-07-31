package com.daxton.fancychat.api;

import com.daxton.fancychat.FancyChat;
import com.daxton.fancycore.api.character.stringconversion.ConversionMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class ButtomText {

    private final FileConfiguration config;

    private final String key;

    private String text;

    private ChatColor chatColor;

    private boolean bold;

    private ClickEvent.Action clickAction;

    private String clickText;

    private String hoveText;

    public ButtomText(FileConfiguration config, String key){
        this.config = config;
        this.key = key;
        setText();
        setColror();
        setBold();
        setClickAction();
        setHoveText();
    }

    public void setText(){
        String text = "";
        if(config.contains(key+".Text")){
            text = config.getString(key+".Text");
        }
        this.text = text;
    }

    public void setColror(){
        ChatColor chatColor = ChatColor.WHITE;
        if(config.contains(key+".Color")){
            String color = config.getString(key+".Color");
            try {
                chatColor = ChatColor.valueOf(color);
            }catch (Exception exception){
                try {
                    chatColor = ChatColor.of("#"+color);
                }catch (Exception exception2){
                    chatColor = ChatColor.WHITE;
                }
            }
        }
        this.chatColor = chatColor;
    }

    public void setBold(){
        Boolean bold = false;
        if(config.contains(key+".Bold")){
            String boldString = config.getString(key+".Bold");
            bold = Boolean.parseBoolean(boldString);
        }
        this.bold = bold;
    }

    public void setClickAction(){
        ClickEvent.Action clickAction = ClickEvent.Action.RUN_COMMAND;
        if(config.contains(key+".ClickEvent.Action")){
            String clickEventAction = config.getString(key+".ClickEvent.Action");
            try {
                clickAction = Enum.valueOf(ClickEvent.Action.class, clickEventAction);
            }catch (Exception exception){
                clickAction = ClickEvent.Action.RUN_COMMAND;
            }
        }
        this.clickAction =  clickAction;
        String clickText = "";
        if(config.contains(key+".ClickEvent.Text")){
            clickText = config.getString(key+".ClickEvent.Text");
        }
        this.clickText = clickText;
    }

    public void setHoveText(){
        String out = "";
        if(config.contains(key+".ShowText")){
            List<String> hoverEventText = config.getStringList(key+".ShowText");

            for(int i = 0 ; i < hoverEventText.size() ; i++){
                if(i > 0){
                    out += "\n";
                }
                out += hoverEventText.get(i);
            }

        }
        this.hoveText = out;
    }

    public String getText(Player player) {
        return ConversionMain.valueOf(player, null, text);
    }

    public ChatColor getChatColor() {
        return chatColor;
    }

    public boolean isBold() {
        return bold;
    }

    public ClickEvent.Action getClickAction() {
        return clickAction;
    }

    public String getClickText(Player player) {
        return ConversionMain.valueOf(player, null, clickText);
    }

    public String getHoveText(Player player) {
        return ConversionMain.valueOf(player, null, hoveText);
    }
}
