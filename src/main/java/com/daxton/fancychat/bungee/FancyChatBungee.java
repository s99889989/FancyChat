package com.daxton.fancychat.bungee;

import com.daxton.fancychat.bungee.command.BungeeCommand;
import com.daxton.fancychat.bungee.listener.BungeeCoreListener;
import net.md_5.bungee.api.plugin.Plugin;

public class FancyChatBungee extends Plugin{

    public static FancyChatBungee fancyChatBungee;

    @Override
    public void onEnable() {
        fancyChatBungee = this;
        //getProxy().registerChannel("BungeeCord");
        getProxy().getPluginManager().registerListener(this, new BungeeCoreListener());
        getProxy().getPluginManager().registerCommand(this, new BungeeCommand());
    }

    @Override
    public void onDisable() {

    }

}
