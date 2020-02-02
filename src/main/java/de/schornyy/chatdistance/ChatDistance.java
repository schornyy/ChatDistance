package de.schornyy.chatdistance;

import de.schornyy.chatdistance.configs.MessagesConfig;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatDistance extends JavaPlugin {

    private MessagesConfig messagesConfig;

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
    }

    private void loadCommands() {

    }

    private void loadListener() {
        PluginManager pluginManager = Bukkit.getPluginManager();
    }

}
