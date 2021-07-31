package com.daxton.fancychat.listener;

import com.daxton.fancychat.FancyChat;
import com.daxton.fancychat.api.ChatConversion;
import com.daxton.fancychat.bungee.task.OutTask;
import com.daxton.fancychat.config.FileConfig;
import com.daxton.fancychat.config.PlayerData;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.google.gson.Gson;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PlayerListener implements Listener {

    //登入時
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        PlayerData.execute(player);
    }

    //當玩家聊天
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        event.setCancelled(true);
        Player player = event.getPlayer();
        String message = event.getMessage();

        FileConfiguration config = FileConfig.config_Map.get("config.yml");
        //前缀
        String prefix = config.getString("prefix");
        if(prefix == null){
            prefix = "";
        }
        //后缀
        String suffix = config.getString("suffix");
        if(suffix == null){
            suffix = "";
        }

        TextComponent prefixComponent = ChatConversion.valueOf(player, prefix);

        TextComponent messageComponent = ChatConversion.valueOf2(player, message);

        messageComponent.setColor(ChatColor.of(PlayerData.getColor(player)));

        TextComponent suffixComponent = ChatConversion.valueOf(player, suffix);

        prefixComponent.addExtra(messageComponent);
        prefixComponent.addExtra(suffixComponent);

        player.spigot().sendMessage(prefixComponent);
        FancyChat.fancyChat.getLogger().info(prefixComponent.toLegacyText());

        String json = ComponentSerializer.toString(prefixComponent);

        BaseComponent[] baseComponents = ComponentSerializer.parse(json);

        boolean bungeeCoreEnable = config.getBoolean("bungeecore.enable");
        if(bungeeCoreEnable){
            OutTask.sendBungeeMessageAll(player, json);

        }
    }




}
