package com.daxton.fancychat.listener;

import com.daxton.fancychat.FancyChat;
import com.daxton.fancychat.api.ChatConversion;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;


public class FancyChatListener implements PluginMessageListener {



    @Override
    public void onPluginMessageReceived(@NotNull String channel, @NotNull Player player, @NotNull byte[] message){

        if (!channel.equals("BungeeCord")) {
            return;
        }

        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
        if (subchannel.equals("FancyChat")) {

            forward(player, in);
            //forwardToPlayer(player, in);
        }

    }

    //發送給全部玩家
    public void forward(Player player, ByteArrayDataInput in){
        short len = in.readShort();
        byte[] msgbytes = new byte[len];
        in.readFully(msgbytes);

        DataInputStream msgin = new DataInputStream(new ByteArrayInputStream(msgbytes));
        try {
            String somedata = msgin.readUTF(); // 以與寫入數據相同的方式讀取數據

            short somenumber = msgin.readShort();

            BaseComponent[] baseComponents = ComponentSerializer.parse(somedata);
            player.sendMessage(baseComponents);
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public void forwardToPlayer(Player player, ByteArrayDataInput in){
        short len = in.readShort();
        byte[] msgbytes = new byte[len];
        in.readFully(msgbytes);

        DataInputStream msgin = new DataInputStream(new ByteArrayInputStream(msgbytes));
        try {
            String somedata = msgin.readUTF(); // Read the data in the same way you wrote it
            short somenumber = msgin.readShort();

            BaseComponent[] baseComponents = ComponentSerializer.parse(somedata);
            //Player player = Bukkit.getPlayerExact("md_5");

            player.sendMessage(baseComponents);

        }catch (IOException exception){
            exception.printStackTrace();
        }

    }

}
