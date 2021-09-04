package com.daxton.fancychat.gui;

import com.daxton.fancychat.config.PlayerData;
import com.daxton.fancycore.api.gui.GUI;
import com.daxton.fancycore.api.gui.button.GuiAction;
import com.daxton.fancycore.api.number.Random;
import com.daxton.fancycore.other.task.CopyClipboard;
import com.daxton.fancycore.nms.NMSVersion;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;

public class CopyColorButton implements GuiAction {

    private final GUI gui;
    private final Player player;
    private final String uuidString;

    public CopyColorButton(GUI gui, Player player){
        this.gui = gui;
        this.player = player;
        this.uuidString = player.getUniqueId().toString();
    }

    public void execute(ClickType clickType, InventoryAction action, int slot){
        if(clickType == ClickType.LEFT){
            String[] colorString = PlayerData.color_Map.get(uuidString);
            StringBuilder in = new StringBuilder("{#");
            for(String cc : colorString){
                in.append(cc);
            }
            in.append("}");
            //複製顏色代碼
            CopyClipboard.copy(in.toString());
        }
        if(clickType == ClickType.RIGHT){
            radanColor();
        }
    }

    //隨機變換顏色代碼
    public void radanColor(){
        if(NMSVersion.compareClientVersion(player, "1.16")){
            String[] colorString = new String[6];
            colorString[0] = Random.randomHex();
            colorString[1] = Random.randomHex();
            colorString[2] = Random.randomHex();
            colorString[3] = Random.randomHex();
            colorString[4] = Random.randomHex();
            colorString[5] = Random.randomHex();

            PlayerData.color_Map.put(uuidString, colorString);

            MainMenu.setDefaultColor(player, gui);
        }else {
            String[] colorString = new String[1];
            colorString[0] = Random.randomHex();
            PlayerData.color_Map.put(uuidString, colorString);

            MainMenu.setDefaultColor(player, gui);
        }
    }



}
