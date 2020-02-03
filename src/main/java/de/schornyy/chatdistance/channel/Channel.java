package de.schornyy.chatdistance.channel;

import de.schornyy.chatdistance.ChatDistance;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Channel {

    private String channelName, permissions, messageDesign;
    private boolean muted, global;
    private int distanze;

    private File file;
    private FileConfiguration cfg;

    private static ArrayList<Channel> storedChannel = new ArrayList<>();

    public Channel(String channelName){
        this.channelName = channelName;
        file = new File("plugins/" + ChatDistance.getPlugin(ChatDistance.class).getName() + "/Channel/" + channelName + ".yml");
        cfg = YamlConfiguration.loadConfiguration(file);
    }

    public void create() {
        setDistanze(10);
        setMuted(false);
        setGlobal(false);
        setPermissions("ChatDistance." + getChannelName());
        setMessageDesign("%player% §8: §f%message%");
        storedChannel.add(this);
    }

    public void delete() {
        getFile().delete();
        getStoredChannel().remove(this);
    }

    public void save() {
        getCfg().set("Distance", getDistanze());
        getCfg().set("Muted", isMuted());
        getCfg().set("Global", isGlobal());

        try {
            getCfg().save(getFile());
        } catch (IOException e){}
    }

    public void load() {
        setDistanze(getCfg().getInt("Distance"));
        setMuted(getCfg().getBoolean("Muted"));
        setGlobal(getCfg().getBoolean("Global"));
        setPermissions(getCfg().getString("Permissions"));
        setMessageDesign(getCfg().getString("MessageDesign").replaceAll("&", "§"));
        storedChannel.add(this);
    }

    public static void loadAllChannel() {
        File path = new File("plugins/" + ChatDistance.getPlugin(ChatDistance.class).getName() + "/Channel/");

        if(path.listFiles() != null) {
            for(File channelFile : path.listFiles()) {
                if(channelFile == null) return;;
                Channel channel = new Channel(channelFile.getName().replaceAll(".yml", ""));
                channel.load();
            }
        }
    }

    public static void saveAllChannel() {
        for(Channel channel : storedChannel) {
            if(channel == null) return;
            channel.save();
        }
    }

    public static Channel getChannelByName(String channelName) {
        for (Channel channel : storedChannel) {
            if (channel.getChannelName().equalsIgnoreCase(channelName)) {
                return channel;
            }
        }
        return null;
    }

    public static ArrayList<Channel> getStoredChannel() {
        return storedChannel;
    }

    public String getPermissions() {
        return permissions;
    }

    public String getMessageDesign() {
        return messageDesign;
    }

    public String getChannelName() {
        return channelName;
    }

    public boolean isMuted() {
        return muted;
    }

    public boolean isGlobal() {
        return global;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }

    public void setMessageDesign(String messageDesign) {
        this.messageDesign = messageDesign;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public File getFile() {
        return file;
    }

    public FileConfiguration getCfg() {
        return cfg;
    }

    public int getDistanze() {
        return distanze;
    }

    public void setDistanze(int distanze) {
        this.distanze = distanze;
    }
}
