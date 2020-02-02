package de.schornyy.chatdistance.configs;

import de.schornyy.chatdistance.ChatDistance;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Config {

    private File file;
    private FileConfiguration cfg;

    public boolean channelForwarding, allowCustomName;

    public Config() {
        file = new File("plugins/" + ChatDistance.getPlugin(ChatDistance.class).getName() + "/Config.yml");
        cfg = YamlConfiguration.loadConfiguration(file);
        load();
    }

    private void load() {
        if(!getFile().exists()) {
            getCfg().set("ChannelForwarding", true);
            getCfg().set("AllowCustomName", true);
        }
        channelForwarding = getCfg().getBoolean("ChannelForwarding");
        allowCustomName = getCfg().getBoolean("AllowCustomName");
    }

    public File getFile() {
        return file;
    }

    public FileConfiguration getCfg() {
        return cfg;
    }
}
