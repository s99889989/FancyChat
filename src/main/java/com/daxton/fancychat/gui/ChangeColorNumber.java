package com.daxton.fancychat.gui;

import com.daxton.fancychat.FancyChat;
import com.daxton.fancychat.config.FileConfig;
import com.daxton.fancychat.config.PlayerData;
import com.daxton.fancycore.api.gui.GUI;
import com.daxton.fancycore.api.gui.GuiAction;
import com.daxton.fancycore.api.gui.GuiButtom;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;

public class ChangeColorNumber implements GuiAction {

    private final GUI gui;
    private final Player player;
    private final String uuidString;

    public ChangeColorNumber(GUI gui, Player player){
        this.gui = gui;
        this.player = player;
        this.uuidString = player.getUniqueId().toString();
    }

    public void execute(ClickType clickType, InventoryAction action, int slot){
        //FancyChat.fancyChat.getLogger().info(slot+"");
        if(clickType == ClickType.LEFT){
            addNumber(slot);
        }
        if(clickType == ClickType.RIGHT){
            cutNumber(slot);
        }
    }

    public void addNumber(int slot){
        if(getNumber(slot) < 0){
            return;
        }
        int sum = getNumber(slot)+1;
        if(sum > 15){
            sum = 0;
        }
        FileConfiguration languageConfig = FileConfig.languageConfig;
        gui.setItem(GuiButtom.valueOf(languageConfig,"Language.Gui.Color"+changeHex(String.valueOf(sum))), false, 1, slot+1);

        String[] colorString = PlayerData.color_Map.get(uuidString);
        colorString[slot-1] = toHex(sum);
        PlayerData.color_Map.put(uuidString, colorString);



    }

    public void cutNumber(int slot){
        if(getNumber(slot) < 0){
            return;
        }
        int sum = getNumber(slot)-1;
        if(sum < 0){
            sum = 15;
        }
        FileConfiguration languageConfig = FileConfig.languageConfig;
        gui.setItem(GuiButtom.valueOf(languageConfig,"Language.Gui.Color"+changeHex(String.valueOf(sum))), false, 1, slot+1);


        String[] colorString = PlayerData.color_Map.get(uuidString);
        colorString[slot-1] = toHex(sum);
        PlayerData.color_Map.put(uuidString, colorString);
    }
    public String toHex(int in){
        String out = "0";
        if(in == 10){
            out = "A";
        }
        if(in == 11){
            out = "B";
        }
        if(in == 12){
            out = "C";
        }
        if(in == 13){
            out = "D";
        }
        if(in == 14){
            out = "E";
        }
        if(in == 15){
            out = "F";
        }
        if(in < 10 && in > 0){
            out = String.valueOf(in);
        }

        return out;
    }
    public String changeHex(String in){
        if(in.equals("A")){
            in = "10";
        }
        if(in.equals("B")){
            in = "11";
        }
        if(in.equals("C")){
            in = "12";
        }
        if(in.equals("D")){
            in = "13";
        }
        if(in.equals("E")){
            in = "14";
        }
        if(in.equals("F")){
            in = "15";
        }
        return in;
    }

    public int getNumber(int key){
        String uuidString = player.getUniqueId().toString();
        String[] colorString = PlayerData.color_Map.get(uuidString);
        String number = colorString[key-1];
        return Integer.parseInt(changeHex(number));
    }


}


