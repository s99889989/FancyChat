package com.daxton.fancychat.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    //登入時
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

    }

    //當玩家聊天
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();


    }

}
