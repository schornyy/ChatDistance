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
            channelIsMuted, channelDosntExists, channelDeleted, channelAlreadyExists, channelCraeted, channelJoined,
            channeIsNowMuted, channelIsnotLongerMuted, channelisGlobal, channelIsnotLongerGlobal, channelChangeDistance,
            playerIsNotOnline, playerSetPlayersChannel;

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
            getCfg().set("ChannelIsnotLongerGlobal", "&aDer Channel ist nicht mehr Global!");
            getCfg().set("ChannelisGlobal", "&aDer Channel ist nun Global!");
            getCfg().set("ChannelIsnotLongerMuted", "&aDer Channel ist nicht länger gemutet!");
            getCfg().set("ChanneIsNowMuted", "&cDer Channel ist nun gemuted!");
            getCfg().set("ChannelChangeDistance", "&aDu hast erfolgreich die Channel Distance geändert!");
            getCfg().set("PlayerIsNotOnline", "&cDer Spieler ist nicht online!");
            getCfg().set("PlayerSetPlayersChannel", "&aDu hast den Channel von dem Spieler &b%player% &aauf &b%channel% &agesetzt");

            try {
                getCfg().save(getFile());
            }catch (IOException e){}
        }
        prefix = getCfg().getString("Prefix").replaceAll("&", "§");
        channelChangeDistance= prefix + getCfg().getString("ChannelChangeDistance").replaceAll("&", "§");
        playerSetPlayersChannel= prefix + getCfg().getString("PlayerSetPlayersChannel").replaceAll("&", "§");
        playerIsNotOnline = prefix + getCfg().getString("PlayerIsNotOnline").replaceAll("&", "§");
        channelIsnotLongerGlobal = prefix + getCfg().getString("ChannelIsnotLongerGlobal").replaceAll("&", "§");
        channelisGlobal = prefix + getCfg().getString("ChannelisGlobal").replaceAll("&", "§");
        channelIsnotLongerMuted = prefix + getCfg().getString("ChannelIsnotLongerMuted").replaceAll("&", "§");
        channeIsNowMuted = prefix + getCfg().getString("ChanneIsNowMuted").replaceAll("&", "§");
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
