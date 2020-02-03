package de.schornyy.chatdistance.configs;

import de.schornyy.chatdistance.ChatDistance;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MessagesConfig {

    private File file;
    private FileConfiguration cfg;

    public String prefix,
            channelIsMuted, channelDosntExists, channelDeleted, channelAlreadyExists, channelCraeted, channelJoined;

    public MessagesConfig() {
        file = new File("plugins/" + ChatDistance.getPlugin(ChatDistance.class).getName() + "/Messages.yml");
        cfg = YamlConfiguration.loadConfiguration(file);
        load();
    }

    private void load() {
        if(!getFile().exists()) {
            getCfg().set("Prefix", "&aChatDistance &f>> ");
            getCfg().set("ChannelIsMuted", "&cDer Channel ist gemuted!");
            getCfg().set("ChannelDeleted", "&aDu hast erfolgreich den Channel gelöscht!");
            getCfg().set("ChannelDosntExists", "&cDer Channel existiert nicht!");
            getCfg().set("ChannelAlreadyExists", "&cDer Channel existiert bereits!");
            getCfg().set("ChannelCreated", "&aDu hast den Channel erfolgreich erstellt!");
            getCfg().set("ChannelJoined", "&aDu bist &b%channel% &agejoined!");

            try {
                getCfg().save(getFile());
            }catch (IOException e){}
        }
        prefix = getCfg().getString("Prefix").replaceAll("&", "§");
        channelCraeted = prefix + getCfg().getString("ChannelCreated").replaceAll("&", "§");
        channelAlreadyExists = prefix + getCfg().getString("ChannelAlreadyExists").replaceAll("&", "§");
        channelDeleted = prefix + getCfg().getString("ChannelDeleted").replaceAll("&", "§");
        channelDosntExists = prefix + getCfg().getString("ChannelDosntExists").replaceAll("&", "§");
        channelIsMuted = prefix + getCfg().getString("ChannelIsMuted").replaceAll("&", "§");
        channelJoined = prefix + getCfg().getString("ChannelJoined").replaceAll("&", "§");
    }


    public FileConfiguration getCfg() {
        return cfg;
    }

    public File getFile() {
        return file;
    }
}
