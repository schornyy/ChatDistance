package de.schornyy.chatdistance.configs;

import de.schornyy.chatdistance.ChatDistance;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MessagesConfig {

    private File file;
    private FileConfiguration cfg;

    public String prefix, channelIsMuted;

    public MessagesConfig() {
        file = new File("plugins/" + ChatDistance.getPlugin(ChatDistance.class).getName() + "/Messages.yml");
        cfg = YamlConfiguration.loadConfiguration(file);
        load();
    }

    private void load() {
        if(!getFile().exists()) {
            getCfg().set("Prefix", "&aChatDistance &f>> ");
            getCfg().set("ChannelIsMuted", "&cDer Channel ist gemuted!");

            try {
                getCfg().save(getFile());
            }catch (IOException e){}
        }
        prefix = getCfg().getString("Prefix").replaceAll("&", "§");
        channelIsMuted = prefix + getCfg().getString("ChannelIsMuted").replaceAll("&", "§");
    }


    public FileConfiguration getCfg() {
        return cfg;
    }

    public File getFile() {
        return file;
    }
}
