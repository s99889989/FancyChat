package com.daxton.fancychat.gui;

import com.daxton.fancychat.config.FileConfig;
import com.daxton.fancychat.config.PlayerData;
import com.daxton.fancychat.manager.Manager;
import com.daxton.fancycore.api.gui.GUI;
import com.daxton.fancycore.api.gui.GuiButtom;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class MainMenu {

    public static void open(Player player){
        String uuidString = player.getUniqueId().toString();
        FileConfiguration languageConfig = FileConfig.languageConfig;

        if(Manager.gui_Map.get(uuidString) == null){
            GUI gui = new GUI(player, 9, "ChatColor");

            gui.setItem(GuiButtom.valueOf(languageConfig,"Language.Gui.CopyColor"), false,1, 1);

            gui.setItem(GuiButtom.valueOf(languageConfig,"Language.Gui.Define"), false,1, 8);

            gui.setItem(GuiButtom.valueOf(languageConfig,"Language.Gui.Exit"), false,1, 9);

            gui.setAction(new PutColor(gui, player), 1, 1);

            gui.setAction(new ChangeColorNumber(gui, player), 1, 2);
            gui.setAction(new ChangeColorNumber(gui, player), 1, 3);
            gui.setAction(new ChangeColorNumber(gui, player), 1, 4);
            gui.setAction(new ChangeColorNumber(gui, player), 1, 5);
            gui.setAction(new ChangeColorNumber(gui, player), 1, 6);
            gui.setAction(new ChangeColorNumber(gui, player), 1, 7);

            gui.setAction(new SaveColor(gui, player), 1, 8);

            gui.setAction(new CloseMenu(gui, player), 1, 9);

            setDefaultColor(player, gui);
            Manager.gui_Map.put(uuidString, gui);
            gui.open();
        }else {
            GUI gui = Manager.gui_Map.get(uuidString);

            setDefaultColor(player, gui);


            gui.open();
        }


    }

    public static void setDefaultColor(Player player, GUI gui){
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

    public static String changeHex(String in){
        in = in.toUpperCase();
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

}
