package com.daxton.fancychat;

import com.daxton.fancychat.command.MainCommand;
import com.daxton.fancychat.command.TabCommand;
import com.daxton.fancychat.listener.PlayerListener;
import com.daxton.fancychat.task.Start;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class FancyChat extends JavaPlugin {

    public static FancyChat fancyChat;

    @Override
    public void onEnable() {
        fancyChat = this;
        if(!DependPlugins.depend()){
            fancyChat.setEnabled(false);
            fancyChat.onDisable();
        }
        //指令
        Objects.requireNonNull(Bukkit.getPluginCommand("fancychat")).setExecutor(new MainCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("fancychat")).setTabCompleter(new TabCommand());
        //監聽
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), fancyChat);
        Start.execute();
    }

    @Override
    public void onDisable() {

    }
}
