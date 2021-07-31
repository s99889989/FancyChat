package com.daxton.fancychat.gui;

import com.daxton.fancychat.config.FileConfig;
import com.daxton.fancychat.config.PlayerData;
import com.daxton.fancycore.api.gui.GUI;
import com.daxton.fancycore.api.gui.GuiAction;
import com.daxton.fancycore.api.gui.GuiButtom;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class PutColor implements GuiAction {

    private final GUI gui;
    private final Player player;
    private final String uuidString;

    public PutColor(GUI gui, Player player){
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

            String str = "{#" + key1 + key2 + key3 + key4 + key5 + key6+"}";

            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Clipboard clipboard = toolkit.getSystemClipboard();
            StringSelection strSel = new StringSelection(str);
            clipboard.setContents(strSel, null);

        }
        if(clickType == ClickType.RIGHT){

            String[] colorString = new String[6];
            colorString[0] = randomHex();
            colorString[1] = randomHex();
            colorString[2] = randomHex();
            colorString[3] = randomHex();
            colorString[4] = randomHex();
            colorString[5] = randomHex();

            PlayerData.color_Map.put(uuidString, colorString);

            setDefaultColor();

        }
    }

    public void setDefaultColor(){
        String uuidString = player.getUniqueId().toString();
        FileConfiguration languageConfig = FileConfig.languageConfig;
        String[] colorString = PlayerData.color_Map.get(uuidString);
        String key1 = colorString[0];
        String key2 = colorString[1];
        String key3 = colorString[2];
        String key4 = colorString[3];
        String key5 = colorString[4];
        String key6 = colorString[5];

        gui.setItem(GuiButtom.valueOf(languageConfig,"Language.Gui.Color"+Integer.parseInt(changeHex(key1))), false,1, 2);
        gui.setItem(GuiButtom.valueOf(languageConfig,"Language.Gui.Color"+Integer.parseInt(changeHex(key2))), false,1, 3);
        gui.setItem(GuiButtom.valueOf(languageConfig,"Language.Gui.Color"+Integer.parseInt(changeHex(key3))), false,1, 4);
        gui.setItem(GuiButtom.valueOf(languageConfig,"Language.Gui.Color"+Integer.parseInt(changeHex(key4))), false,1, 5);
        gui.setItem(GuiButtom.valueOf(languageConfig,"Language.Gui.Color"+Integer.parseInt(changeHex(key5))), false,1, 6);
        gui.setItem(GuiButtom.valueOf(languageConfig,"Language.Gui.Color"+Integer.parseInt(changeHex(key6))), false,1, 7);
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

    public static String randomHex(){
        int k = (int) (Math.random() *16) +1;
        return toHex(k);
    }
    public static String toHex(int in){
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

}
