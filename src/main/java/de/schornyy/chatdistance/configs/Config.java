package de.schornyy.chatdistance.configs;

import de.schornyy.chatdistance.ChatDistance;
import de.schornyy.chatdistance.channel.Channel;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {

    private File file;
    private FileConfiguration cfg;

    public boolean channelForwarding, allowCustomName;
    public Channel defaultChannel;

    public Config() {
        file = new File("plugins/" + ChatDistance.getPlugin(ChatDistance.class).getName() + "/Config.yml");
        cfg = YamlConfiguration.loadConfiguration(file);
        load();
    }

    private void load() {
        if(!getFile().exists()) {
            getCfg().set("ChannelForwarding", true);
            getCfg().set("AllowCustomName", true);
            getCfg().set("DefaultChannel", "Default");
            try {
                getCfg().save(getFile());
            }catch (IOException e){}

        }
        channelForwarding = getCfg().getBoolean("ChannelForwarding");
        allowCustomName = getCfg().getBoolean("AllowCustomName");
        defaultChannel = Channel.getChannelByName(getCfg().getString("DefaultChannel"));
    }

    public File getFile() {
        return file;
    }

    public FileConfiguration getCfg() {
        return cfg;
    }
}
