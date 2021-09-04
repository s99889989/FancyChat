package com.daxton.fancychat.gui;

import com.daxton.fancychat.FancyChat;
import com.daxton.fancychat.config.FileConfig;
import com.daxton.fancychat.config.PlayerData;
import com.daxton.fancycore.api.gui.button.GuiAction;
import com.daxton.fancycore.nms.NMSVersion;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;

import java.io.File;
import java.io.IOException;

public class SaveColorButton implements GuiAction {


    private final Player player;
    private final String uuidString;

    public SaveColorButton(Player player){
        this.player = player;
        this.uuidString = player.getUniqueId().toString();
    }

    public void execute(ClickType clickType, InventoryAction action, int slot){
        if(clickType == ClickType.LEFT){
            if(NMSVersion.compareClientVersion(player, "1.16")){
                seve16up();
            }else {
                save();
            }
        }


    }
    //1.16以上的存檔
    public void seve16up(){
        String[] colorString = PlayerData.color_Map.get(uuidString);
        String key1 = colorString[0];
        String key2 = colorString[1];
        String key3 = colorString[2];
        String key4 = colorString[3];
        String key5 = colorString[4];
        String key6 = colorString[5];
        String output = key1 +  key2 + key3 +  key4 +  key5 + key6;

        FileConfiguration dataConfig = FileConfig.config_Map.get("PlayerData.yml");
        dataConfig.set(uuidString+".ChatRGBColor", output);
        File file = new File(FancyChat.fancyChat.getDataFolder(), "PlayerData.yml");
        FileConfig.config_Map.put("PlayerData.yml", dataConfig);
        try {
            dataConfig.save(file);
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }
    //1.16以下的存檔
    public void save(){
        String[] colorString = PlayerData.color_Map.get(uuidString);
        String key1 = colorString[0];
        FileConfiguration dataConfig = FileConfig.config_Map.get("PlayerData.yml");
        dataConfig.set(uuidString+".ChatColor", key1);
        File file = new File(FancyChat.fancyChat.getDataFolder(), "PlayerData.yml");
        FileConfig.config_Map.put("PlayerData.yml", dataConfig);
        try {
            dataConfig.save(file);
        }catch (IOException exception){
            exception.printStackTrace();
        }

    }

}
