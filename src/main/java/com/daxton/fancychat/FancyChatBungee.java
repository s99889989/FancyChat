package com.daxton.fancychat;

import com.daxton.fancychat.command.BungeeCommand;
import com.daxton.fancychat.listener.BungeeCoreListener;
import net.md_5.bungee.api.plugin.Plugin;

public class FancyChatBungee extends Plugin{

    public static FancyChatBungee fancyChatBungee;

    @Override
    public void onEnable() {
        fancyChatBungee = this;
        getProxy().getPluginManager().registerListener(this, new BungeeCoreListener());
        getProxy().getPluginManager().registerCommand(this, new BungeeCommand());
    }

    @Override
    public void onDisable() {

    }

}
