package de.schornyy.chatdistance;

import de.schornyy.chatdistance.channel.Channel;
import de.schornyy.chatdistance.configs.Config;
import de.schornyy.chatdistance.configs.MessagesConfig;
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
    }

    @Override
    public void onDisable() {

    }

    private void loadInits() {
        messagesConfig = new MessagesConfig();
        config = new Config();
        Channel channel = new Channel("Test");
        channel.create();
    }

    private void loadCommands() {

    }

    private void loadListener() {
        PluginManager pluginManager = Bukkit.getPluginManager();
    }

    public MessagesConfig getMessagesConfig() {
        return messagesConfig;
    }

    public Config getChatDistanceConfig() {
        return config;
    }
}
