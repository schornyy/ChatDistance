package de.schornyy.chatdistance.listener;

import de.schornyy.chatdistance.ChatDistance;
import de.schornyy.chatdistance.channelplayer.ChannelPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        ChannelPlayer channelPlayer = ChannelPlayer.getChannelPlayerByPlayer(p);

        e.setCancelled(true);

        if(channelPlayer.getChannel().isMuted()) {
            p.sendMessage("§cDer Channel ist gemuted");
            return;
        }

        String msg = "";
        if(ChatDistance.getPlugin(ChatDistance.class).getChatDistanceConfig().allowCustomName) {
            if(p.getCustomName() != null) {
                msg = channelPlayer.getChannel().getMessageDesign().replaceAll("%player%", p.getCustomName()).replaceAll("%message%", e.getMessage());
            } else {
                msg = channelPlayer.getChannel().getMessageDesign().replaceAll("%player%", p.getName()).replaceAll("%message%", e.getMessage());
            }
        } else {
            msg = channelPlayer.getChannel().getMessageDesign().replaceAll("%player%", p.getName()).replaceAll("%message%", e.getMessage());
        }
        final String finalMessage = msg;
        p.sendMessage(finalMessage);

        if(!channelPlayer.getChannel().isGlobal()) {
            if(ChatDistance.getPlugin(ChatDistance.class).getChatDistanceConfig().channelForwarding) {
                int distance = channelPlayer.getChannel().getDistanze();
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (p.getLocation().distance(all.getLocation()) <= distance) {
                        if(all != p) {
                            all.sendMessage(finalMessage);
                        }
                    }
                }
                return;
            }
            for (Player all : Bukkit.getOnlinePlayers()) {
                if (p.getLocation().distance(all.getLocation()) <= channelPlayer.getChannel().getDistanze()) {
                    ChannelPlayer targetChannelPlayer = ChannelPlayer.getChannelPlayerByPlayer(all);
                    if (targetChannelPlayer.getChannel().getChannelName().equalsIgnoreCase(channelPlayer.getChannel().getChannelName())) {
                        if(all != p) {
                            all.sendMessage(finalMessage);
                        }
                    }
                }
            }
            return;
        } else if (channelPlayer.getChannel().isGlobal()){
            if(ChatDistance.getPlugin(ChatDistance.class).getChatDistanceConfig().channelForwarding) {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if(all != p) {
                        all.sendMessage(finalMessage);
                    }
                }
                return;
            }
            for (Player all : Bukkit.getOnlinePlayers()) {
                ChannelPlayer targetChannelPlayer = ChannelPlayer.getChannelPlayerByPlayer(all);
                if (targetChannelPlayer.getChannel().getChannelName().equalsIgnoreCase(channelPlayer.getChannel().getChannelName())) {
                    if(all != p) {
                        all.sendMessage(finalMessage);
                    }
                }
            }
            return;
        }
    }
}
