package de.schornyy.chatdistance;

import de.schornyy.chatdistance.channel.Channel;
import de.schornyy.chatdistance.commands.ChannelCommand;
import de.schornyy.chatdistance.configs.Config;
import de.schornyy.chatdistance.configs.MessagesConfig;
import de.schornyy.chatdistance.listener.PlayerChatListener;
import de.schornyy.chatdistance.listener.PlayerJoinListener;
import de.schornyy.chatdistance.listener.PlayerQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatDistance extends JavaPlugin {

    private MessagesConfig messagesConfig;
    private Config config;

    @Override
    public void onEnable() {
        loadInits();
        loadCommands();
        loadListener();

        Bukkit.getConsoleSender().sendMessage(getMessagesConfig().prefix + "§awurde geladen");
    }

    @Override
    public void onDisable() {
        Channel.saveAllChannel();
        Bukkit.getConsoleSender().sendMessage(getMessagesConfig().prefix + "§cwurde deaktiviert");
    }

    private void loadInits() {
        messagesConfig = new MessagesConfig();
        Channel.loadAllChannel();

        if(Channel.getChannelByName("Default") == null) {
            Channel channel = new Channel("Default");
            channel.create();
        }

        config = new Config();
    }

    private void loadCommands() {
        getCommand("channel").setExecutor(new ChannelCommand());

        getCommand("channel").setTabCompleter(new ChannelCommand());
    }

    private void loadListener() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerChatListener(), this);
        pluginManager.registerEvents(new PlayerJoinListener(), this);
        pluginManager.registerEvents(new PlayerQuitListener(), this);
    }

    public MessagesConfig getMessagesConfig() {
        return messagesConfig;
    }

    public Config getChatDistanceConfig() {
        return config;
    }
}
