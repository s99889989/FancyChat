package com.daxton.fancychat.gui;

import com.daxton.fancychat.FancyChat;
import com.daxton.fancychat.config.FileConfig;
import com.daxton.fancychat.config.PlayerData;
import com.daxton.fancycore.api.gui.GUI;
import com.daxton.fancycore.api.gui.GuiAction;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;

import java.io.File;
import java.io.IOException;

public class SaveColor implements GuiAction {

    private final GUI gui;
    private final Player player;
    private final String uuidString;

    public SaveColor(GUI gui, Player player){
        this.gui = gui;
        this.player = player;
        this.uuidString = player.getUniqueId().toString();
    }

    public void execute(ClickType clickType, InventoryAction action, int slot){
        if(clickType == ClickType.LEFT){
            String[] colorString = PlayerData.color_Map.get(uuidString);
            String key1 = colorString[0];
            String key2 = colorString[1];
            String key3 = colorString[2];
            String key4 = colorString[3];
            String key5 = colorString[4];
            String key6 = colorString[5];
            String output = key1 +  key2 + key3 +  key4 +  key5 + key6;

            FileConfiguration dataConfig = FileConfig.config_Map.get("PlayerData.yml");
            dataConfig.set(uuidString+".ChatColor", output);
            File file = new File(FancyChat.fancyChat.getDataFolder(), "PlayerData.yml");
            FileConfig.config_Map.put("PlayerData.yml", dataConfig);
            try {
                dataConfig.save(file);
            }catch (IOException exception){
                exception.printStackTrace();
            }
        }


    }

}
