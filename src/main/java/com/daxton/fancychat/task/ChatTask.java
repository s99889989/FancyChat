package com.daxton.fancychat.task;

import com.daxton.fancychat.api.ChatConversion;
import com.daxton.fancychat.api.PrefixSuffixConversion;
import com.daxton.fancychat.config.FileConfig;
import com.daxton.fancycore.api.config.SearchConfigFile;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ChatTask {

    public static TextComponent onChat(Player player , String message){
        FileConfiguration config = FileConfig.config_Map.get("config.yml");
        //前缀
        String prefix = config.getString("prefix");
        if(prefix == null){
            prefix = "";
        }
        for(String s : SearchConfigFile.sectionList(config,"PermissionPrefix")){
            if(player.hasPermission("fancychat.prefix."+s)){
                prefix = config.getString("PermissionPrefix."+s);
            }
        }
        //后缀
        String suffix = config.getString("suffix");
        if(suffix == null){
            suffix = "";
        }
        for(String s : SearchConfigFile.sectionList(config,"PermissionSuffix")){
            if(player.hasPermission("fancychat.suffix."+s)){
                suffix = config.getString("PermissionSuffix."+s);
            }
        }
        //前輟
        TextComponent prefixComponent = PrefixSuffixConversion.valueOf(player, prefix);
        //說的話
        TextComponent messageComponent = ChatConversion.valueOf(player, message);

       // messageComponent.setColor(PlayerData.getColor(player));
        //後輟
        TextComponent suffixComponent = PrefixSuffixConversion.valueOf(player, suffix);

        prefixComponent.addExtra(messageComponent);
        prefixComponent.addExtra(suffixComponent);

        return prefixComponent;

    }

}
