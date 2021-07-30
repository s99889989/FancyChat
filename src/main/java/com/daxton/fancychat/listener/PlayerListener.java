package com.daxton.fancychat.listener;

import com.daxton.fancychat.FancyChat;
import com.daxton.fancychat.api.ChatConversion;
import com.daxton.fancychat.config.FileConfig;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
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
        //Player player = event.getPlayer();

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
        //后缀
        String suffix = config.getString("suffix");

        TextComponent prefixComponent = new TextComponent();

        TextComponent suffixComponent = new TextComponent();

        if(prefix != null && !prefix.isEmpty()){
            prefixComponent = ChatConversion.valueOf(player, prefix);
        }
        TextComponent messageComponent = ChatConversion.valueOf2(player, message);
        if(suffix != null && !suffix.isEmpty()){
            suffixComponent = ChatConversion.valueOf(player, prefix);
        }
        prefixComponent.addExtra(messageComponent);
        prefixComponent.addExtra(suffixComponent);

        player.spigot().sendMessage(prefixComponent);

    }


    public static void serverTp(Player player, String server){
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("Connect");
        output.writeUTF(server);
        output.writeUTF(player.getName());

        player.sendPluginMessage(FancyChat.fancyChat, "BungeeCord", output.toByteArray());

    }

    public static void serverMessage(Player player){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Message");
        out.writeUTF("roblabla");
        out.writeUTF(ChatColor.RED + "Congrats, you just won 1$!");
        player.sendPluginMessage(FancyChat.fancyChat, "BungeeCord", out.toByteArray());
    }

    public static void mm(Player player){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();

        out.writeUTF("Forward"); // So BungeeCord knows to forward it
        out.writeUTF("ONLINE");
        out.writeUTF("FancyChat"); // The channel name to check if this your data

        ByteArrayOutputStream msgbytes = new ByteArrayOutputStream();
        DataOutputStream msgout = new DataOutputStream(msgbytes);
        try {
            msgout.writeUTF("Some kind of data here"); // You can do anything you want with msgout
            msgout.writeShort(123);
        } catch (IOException exception){
            exception.printStackTrace();
        }

        out.writeShort(msgbytes.toByteArray().length);
        out.write(msgbytes.toByteArray());

        player.sendPluginMessage(FancyChat.fancyChat, "BungeeCord", out.toByteArray());
    }

}
