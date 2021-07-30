package com.daxton.fancychat.listener;

import com.daxton.fancychat.FancyChat;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;


public class FancyChatListener implements PluginMessageListener {


    @Override
    public void onPluginMessageReceived(@NotNull String channel, @NotNull Player player, @NotNull byte[] message){

        if (!channel.equals("BungeeCord")) {
            return;
        }

        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
        if (subchannel.equals("FancyChat")) {

            short len = in.readShort();
            byte[] msgbytes = new byte[len];
            in.readFully(msgbytes);

            DataInputStream msgin = new DataInputStream(new ByteArrayInputStream(msgbytes));
            try {
                String somedata = msgin.readUTF(); // Read the data in the same way you wrote it
                short somenumber = msgin.readShort();
                FancyChat.fancyChat.getLogger().info(somedata);
            }catch (IOException exception){
                exception.printStackTrace();
            }

        }

    }

}
