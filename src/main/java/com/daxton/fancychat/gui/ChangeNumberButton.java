package com.daxton.fancychat.gui;

import com.daxton.fancychat.config.PlayerData;
import com.daxton.fancycore.api.gui.GUI;

import com.daxton.fancycore.api.gui.button.GuiAction;
import com.daxton.fancycore.api.gui.button.GuiButton;
import com.daxton.fancycore.api.gui.item.GuiItem;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;

import static com.daxton.fancychat.config.FileConfig.languageConfig;

public class ChangeNumberButton implements GuiAction {

    private final GUI gui;
    private final Player player;
    private final String uuidString;
    //改變數字
    public ChangeNumberButton(GUI gui, Player player){
        this.gui = gui;
        this.player = player;
        this.uuidString = player.getUniqueId().toString();
    }

    public void execute(ClickType clickType, InventoryAction action, int slot){
        if(clickType == ClickType.LEFT){
            addNumber(slot);
        }
        if(clickType == ClickType.RIGHT){
            cutNumber(slot);
        }
    }
    //增加數字
    public void addNumber(int slot){
        if(getNumber(slot) < 0){
            return;
        }
        int sum = getNumber(slot)+1;
        if(sum > 15){
            sum = 0;
        }

        GuiButton addNumberButton = GuiButton.ButtonBuilder.getInstance().
            setItemStack(GuiItem.valueOf(languageConfig,"Gui.Color"+sum)).
            setGuiAction(this).
            build();

        gui.setButton(addNumberButton, 1, slot+1);

        String[] colorString = PlayerData.color_Map.get(uuidString);
        colorString[slot-1] = Integer.toHexString(sum).toUpperCase();
        PlayerData.color_Map.put(uuidString, colorString);

    }
    //減少數字
    public void cutNumber(int slot){
        if(getNumber(slot) < 0){
            return;
        }
        int sum = getNumber(slot)-1;
        if(sum < 0){
            sum = 15;
        }

        GuiButton cutNumberButton = GuiButton.ButtonBuilder.getInstance().
            setItemStack(GuiItem.valueOf(languageConfig,"Gui.Color"+sum)).
            setGuiAction(this).
            build();

        gui.setButton(cutNumberButton, 1, slot+1);

        String[] colorString = PlayerData.color_Map.get(uuidString);
        colorString[slot-1] = Integer.toHexString(sum).toUpperCase();
        PlayerData.color_Map.put(uuidString, colorString);
    }
    //獲取該位置數字
    public int getNumber(int key){
        String uuidString = player.getUniqueId().toString();
        String[] colorString = PlayerData.color_Map.get(uuidString);
        String number = colorString[key-1];
        return Integer.parseInt(number, 16);
    }


}


