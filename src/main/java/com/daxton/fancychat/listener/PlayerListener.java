package com.daxton.fancychat.listener;

import com.daxton.fancychat.FancyChat;
import com.daxton.fancychat.bungee.task.OutTask;
import com.daxton.fancychat.config.FileConfig;
import com.daxton.fancychat.config.PlayerData;
import com.daxton.fancychat.manager.Manager;
import com.daxton.fancychat.task.ChatTask;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler//玩家登入
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        PlayerData.execute(player);
    }

    @EventHandler//玩家登出
    public void onPlayerQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        String uuidString = player.getUniqueId().toString();
        if(Manager.gui_Map.get(uuidString) != null){
            Manager.gui_Map.remove(uuidString);
        }
    }

    @EventHandler(priority = EventPriority.LOW)//當玩家聊天
    public void onChat2(PlayerChatEvent event){

    }

    @EventHandler(priority = EventPriority.LOW)//當玩家聊天
    public void onChat(AsyncPlayerChatEvent event){
        FileConfiguration config = FileConfig.config_Map.get("config.yml");

        boolean plainText = config.getBoolean("SendPlainText");

        Player player = event.getPlayer();
        String message = event.getMessage();
        //聊天對話處理
        TextComponent textComponent = ChatTask.onChat(player, message);
        if(plainText){
            event.setMessage(textComponent.toLegacyText());
        }else {
            event.setCancelled(true);
//            FancyChatEvent fancyChatEvent = new FancyChatEvent(player, textComponent);
//            Bukkit.getPluginManager().callEvent(fancyChatEvent);

            Bukkit.getOnlinePlayers().stream().filter(player1 -> player1 != player).forEach(player1 -> player1.spigot().sendMessage(textComponent));
            player.spigot().sendMessage(textComponent);

            FancyChat.fancyChat.getLogger().info(textComponent.toLegacyText());

            String json = ComponentSerializer.toString(textComponent);

            //BaseComponent[] baseComponents = ComponentSerializer.parse(json);

            boolean bungeeCoreEnable = config.getBoolean("bungeecore.enable");
            if(bungeeCoreEnable){
                OutTask.sendBungeeMessageAll(player, json);

            }
        }




    }



}
