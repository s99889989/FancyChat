package com.daxton.fancychat;

import com.daxton.fancychat.command.MainCommand;
import com.daxton.fancychat.command.TabCommand;
import com.daxton.fancychat.config.FileConfig;
import com.daxton.fancychat.listener.FancyChatListener;
import com.daxton.fancychat.listener.PlayerListener;
import com.daxton.fancychat.task.Start;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

import static com.daxton.fancychat.config.FileConfig.languageConfig;

public final class FancyChat extends JavaPlugin {

    public static FancyChat fancyChat;

    @Override
    public void onEnable() {
        fancyChat = this;
        //設定檔
        FileConfig.execute();
        //依賴插件
        if(!DependPlugins.depend()){
            fancyChat.setEnabled(false);
            return;
        }
        //指令
        Objects.requireNonNull(Bukkit.getPluginCommand("fancychat")).setExecutor(new MainCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("fancychat")).setTabCompleter(new TabCommand());
        //監聽
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), fancyChat);
        //開服執行程序
        Start.execute();
    }

    @Override
    public void onDisable() {
        fancyChat.getLogger().info(languageConfig.getString("LogMessage.Disable"));
    }
}
