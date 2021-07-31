package com.daxton.fancychat.listener;

import com.daxton.fancychat.FancyChat;
import com.daxton.fancychat.api.ChatConversion;
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

        //player.sendMessage(PlayerData.getColor(player)+message);
        player.spigot().sendMessage(prefixComponent);
        FancyChat.fancyChat.getLogger().info(prefixComponent.toLegacyText());

        String json = ComponentSerializer.toString(prefixComponent);

        BaseComponent[] baseComponents = ComponentSerializer.parse(json);

        boolean bungeeCoreEnable = config.getBoolean("bungeecore.enable");
        if(bungeeCoreEnable){
            sendBungeeMessage(player, json);
            //getServerList(player);
            //sendBungeeMessage2(player, json);
        }
    }

    public static void getServerList(Player player){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("GetServers");
        player.sendPluginMessage(FancyChat.fancyChat, "BungeeCord", out.toByteArray());
    }

    public static void getPlaerList(Player player){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("PlayerList");
        out.writeUTF("pvp");
        player.sendPluginMessage(FancyChat.fancyChat, "BungeeCord", out.toByteArray());
    }

    public static void serverTp(Player player, String server){
        FancyChat.fancyChat.getLogger().info(server);
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("Connect");
        output.writeUTF(server);
        output.writeUTF(player.getName());

        player.sendPluginMessage(FancyChat.fancyChat, "BungeeCord", output.toByteArray());

    }

    public static void sendBungeeMessage2(Player player, String message){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();

        out.writeUTF("ForwardToPlayer");
        out.writeUTF("Pocaca");
        out.writeUTF("FancyChat");

        ByteArrayOutputStream msgbytes = new ByteArrayOutputStream();
        DataOutputStream msgout = new DataOutputStream(msgbytes);
        try {
            msgout.writeUTF(message);
            msgout.writeShort(123);
        } catch (IOException exception){
            exception.printStackTrace();
        }

        out.writeShort(msgbytes.toByteArray().length);
        out.write(msgbytes.toByteArray());

        player.sendPluginMessage(FancyChat.fancyChat, "BungeeCord", out.toByteArray());
    }

    public static void sendBungeeMessage(Player player, String message){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();

        out.writeUTF("Forward"); // So BungeeCord knows to forward it
        out.writeUTF("ONLINE");
        out.writeUTF("FancyChat"); // The channel name to check if this your data

        ByteArrayOutputStream msgbytes = new ByteArrayOutputStream();
        DataOutputStream msgout = new DataOutputStream(msgbytes);
        try {
            msgout.writeUTF(message); // You can do anything you want with msgout
            msgout.writeShort(123);
        } catch (IOException exception){
            exception.printStackTrace();
        }

        out.writeShort(msgbytes.toByteArray().length);
        out.write(msgbytes.toByteArray());

        player.sendPluginMessage(FancyChat.fancyChat, "BungeeCord", out.toByteArray());
    }

}
