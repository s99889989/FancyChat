package com.daxton.fancychat.gui;

import com.daxton.fancychat.config.FileConfig;
import com.daxton.fancychat.config.PlayerData;
import com.daxton.fancychat.manager.Manager;
import com.daxton.fancycore.api.gui.GUI;
import com.daxton.fancycore.api.gui.button.GuiButton;
import com.daxton.fancycore.api.gui.item.GuiItem;
import com.daxton.fancycore.nms.NMSVersion;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import static com.daxton.fancychat.config.FileConfig.languageConfig;

public class MainMenu {
    //打開顏色選單
    public static void open(Player player){
        String uuidString = player.getUniqueId().toString();

        if(Manager.gui_Map.get(uuidString) == null){
            GUI gui = GUI.GUIBuilder.getInstance().setPlayer(player).setSize(9).setTitle(languageConfig.getString("Title")).build();
            gui.setMove(false);

            GuiButton copyColorButton = GuiButton.ButtonBuilder.getInstance().
                setItemStack(GuiItem.valueOf(languageConfig,"Gui.CopyColor")).
                setGuiAction(new CopyColorButton(gui, player)).
                build();

            gui.setButton(copyColorButton, 1, 1);

            GuiButton defineButton = GuiButton.ButtonBuilder.getInstance().
                setItemStack(GuiItem.valueOf(languageConfig,"Gui.Define")).
                setGuiAction(new SaveColorButton(player)).
                build();
            gui.setButton(defineButton, 1, 8);

            GuiButton exitButton = GuiButton.ButtonBuilder.getInstance().
                setItemStack(GuiItem.valueOf(languageConfig,"Gui.Exit")).
                setGuiAction(new CloseMenuButton(gui)).
                build();
            gui.setButton(exitButton, 1, 9);

            setDefaultColor(player, gui);
            Manager.gui_Map.put(uuidString, gui);
            gui.open(gui);
        }else {
            GUI gui = Manager.gui_Map.get(uuidString);

            setDefaultColor(player, gui);


            gui.open(gui);
        }


    }
    //刷新數字
    public static void setDefaultColor(Player player, GUI gui){
        String uuidString = player.getUniqueId().toString();
        FileConfiguration languageConfig = FileConfig.languageConfig;
        String[] colorString = PlayerData.color_Map.get(uuidString);

        GuiButton changeNumberButton0 = GuiButton.ButtonBuilder.getInstance().
            setItemStack(GuiItem.valueOf(languageConfig,"Gui.Color"+Integer.parseInt(colorString[0], 16))).
            setGuiAction(new ChangeNumberButton(gui, player)).
            build();
        gui.setButton(changeNumberButton0, 1, 2);

        if(NMSVersion.compareClientVersion(player, "1.16")){
            GuiButton changeNumberButton1 = GuiButton.ButtonBuilder.getInstance().
                setItemStack(GuiItem.valueOf(languageConfig,"Gui.Color"+Integer.parseInt(colorString[1], 16))).
                setGuiAction(new ChangeNumberButton(gui, player)).
                build();
            gui.setButton(changeNumberButton1, 1, 3);

            GuiButton changeNumberButton2 = GuiButton.ButtonBuilder.getInstance().
                setItemStack(GuiItem.valueOf(languageConfig,"Gui.Color"+Integer.parseInt(colorString[2], 16))).
                setGuiAction(new ChangeNumberButton(gui, player)).
                build();
            gui.setButton(changeNumberButton2, 1, 4);

            GuiButton changeNumberButton3 = GuiButton.ButtonBuilder.getInstance().
                setItemStack(GuiItem.valueOf(languageConfig,"Gui.Color"+Integer.parseInt(colorString[3], 16))).
                setGuiAction(new ChangeNumberButton(gui, player)).
                build();
            gui.setButton(changeNumberButton3, 1, 5);

            GuiButton changeNumberButton4 = GuiButton.ButtonBuilder.getInstance().
                setItemStack(GuiItem.valueOf(languageConfig,"Gui.Color"+Integer.parseInt(colorString[4], 16))).
                setGuiAction(new ChangeNumberButton(gui, player)).
                build();
            gui.setButton(changeNumberButton4, 1, 6);

            GuiButton changeNumberButton5 = GuiButton.ButtonBuilder.getInstance().
                setItemStack(GuiItem.valueOf(languageConfig,"Gui.Color"+Integer.parseInt(colorString[5], 16))).
                setGuiAction(new ChangeNumberButton(gui, player)).
                build();
            gui.setButton(changeNumberButton5, 1, 7);
        }


    }



}
