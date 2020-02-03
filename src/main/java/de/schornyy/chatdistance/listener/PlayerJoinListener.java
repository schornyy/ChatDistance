package de.schornyy.chatdistance.listener;

import de.schornyy.chatdistance.channelplayer.ChannelPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        ChannelPlayer channelPlayer = new ChannelPlayer(p);
        channelPlayer.load();
    }
}
