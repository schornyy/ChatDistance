package de.schornyy.chatdistance.channelplayer;

import de.schornyy.chatdistance.ChatDistance;
import de.schornyy.chatdistance.channel.Channel;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class ChannelPlayer {

    private Player player;
    private String latestChannel;
    private Channel channel;

    private File file;
    private FileConfiguration cfg;

    private static HashMap<Player, ChannelPlayer> storedChannelPlayer = new HashMap<>();

    public ChannelPlayer(Player player) {
        this.player = player;
        file = new File("plugins/" + ChatDistance.getPlugin(ChatDistance.class).getName() + "/Player/" + player.getUniqueId() + ".yml");
        cfg = YamlConfiguration.loadConfiguration(file);
    }

    public void load() {
        if(!getFile().exists()) {
            getCfg().set("LatestChannel", ChatDistance.getPlugin(ChatDistance.class).getChatDistanceConfig().defaultChannel.getChannelName());

            try {
                getCfg().save(getFile());
            }catch (IOException e){}
            setLatestChannel(getCfg().getString("LatestChannel"));
        }
        setChannel(Channel.getChannelByName(getCfg().getString("LatestChannel")));
        storedChannelPlayer.put(player, this);
    }

    public void save() {
        getCfg().set("LatestChannel", getChannel().getChannelName());
        try {
            getCfg().save(getFile());
        }catch (IOException e){}
        storedChannelPlayer.remove(player);
    }

    public String getLatestChannel() {
        return latestChannel;
    }

    public void setLatestChannel(String latestChannel) {
        this.latestChannel = latestChannel;
    }

    public static ChannelPlayer getChannelPlayerByPlayer(Player player) {
        return storedChannelPlayer.get(player);
    }

    public FileConfiguration getCfg() {
        return cfg;
    }

    public File getFile() {
        return file;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
