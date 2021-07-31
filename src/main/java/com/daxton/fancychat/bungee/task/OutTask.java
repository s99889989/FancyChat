package com.daxton.fancychat.bungee.task;

import com.daxton.fancychat.FancyChat;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class OutTask {

    //獲取伺服器列表
    public static void getServerList(Player player){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("GetServers");
        player.sendPluginMessage(FancyChat.fancyChat, "BungeeCord", out.toByteArray());
    }
    //獲取玩家列表
    public static void getPlaerList(Player player, String serverName){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("PlayerList");
        out.writeUTF(serverName);
        player.sendPluginMessage(FancyChat.fancyChat, "BungeeCord", out.toByteArray());
    }
    //伺服器傳送
    public static void serverTp(Player player, String server){
        FancyChat.fancyChat.getLogger().info(server);
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("Connect");
        output.writeUTF(server);
        output.writeUTF(player.getName());

        player.sendPluginMessage(FancyChat.fancyChat, "BungeeCord", output.toByteArray());

    }

    //向指定玩家發送消息
    public static void sendBungeeMessage2(Player player, String message, String playerName){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();

        out.writeUTF("ForwardToPlayer");
        out.writeUTF("playerName");
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

    //向所有玩家發送消息
    public static void sendBungeeMessageAll(Player player, String message){
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
