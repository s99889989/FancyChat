package com.daxton.fancychat.gui;

import com.daxton.fancycore.api.gui.GUI;
import com.daxton.fancycore.api.gui.button.GuiAction;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;

public class CloseMenuButton implements GuiAction {

    private final GUI gui;

    public CloseMenuButton(GUI gui){
        this.gui = gui;
    }

    public void execute(ClickType clickType, InventoryAction action, int slot){
        if(clickType == ClickType.LEFT){
            gui.close();
        }
    }


}
