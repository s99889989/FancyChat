package com.daxton.fancychat.gui;

import com.daxton.fancycore.api.gui.GUI;
import com.daxton.fancycore.api.gui.GuiAction;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;

public class CloseMenu implements GuiAction {

    private final GUI gui;
    private final Player player;
    private final String uuidString;

    public CloseMenu(GUI gui, Player player){
        this.gui = gui;
        this.player = player;
        this.uuidString = player.getUniqueId().toString();
    }

    public void execute(ClickType clickType, InventoryAction action, int slot){
        if(clickType == ClickType.LEFT){
            gui.close();
        }
    }


}
