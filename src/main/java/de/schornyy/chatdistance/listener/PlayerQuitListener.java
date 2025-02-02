package de.schornyy.chatdistance.listener;

import de.schornyy.chatdistance.channelplayer.ChannelPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        ChannelPlayer channelPlayer = ChannelPlayer.getChannelPlayerByPlayer(p);
        channelPlayer.save();
    }
}
