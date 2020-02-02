package de.schornyy.chatdistance.configs;

import de.schornyy.chatdistance.ChatDistance;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class MessagesConfig {

    private File file;
    private FileConfiguration cfg;

    public String prefix;

    public MessagesConfig() {
        file = new File("plugins/" + ChatDistance.getPlugin(ChatDistance.class).getName() + "/Messages.yml");
        cfg = YamlConfiguration.loadConfiguration(file);
        load();
    }

    private void load() {
        if(!getFile().exists()) {
            getCfg().set("Prefix", "&aChatDistance &f>> ");
        }
        prefix = getCfg().getString("Prefix").replaceAll("&", "§");
    }

    public FileConfiguration getCfg() {
        return cfg;
    }

    public File getFile() {
        return file;
    }
}
