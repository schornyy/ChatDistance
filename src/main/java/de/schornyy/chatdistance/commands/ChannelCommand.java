package de.schornyy.chatdistance.commands;

import de.schornyy.chatdistance.channel.Channel;
import de.schornyy.chatdistance.channelplayer.ChannelPlayer;
import de.schornyy.chatdistance.utils.MessageBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.*;

public class ChannelCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player)sender;

        if(p.hasPermission("ChatDistance.edit")){
            if(args.length == 1) {
                if(args[0].equalsIgnoreCase("list")) {
                    Channel.getStoredChannel().forEach(channel -> {

                        TextComponent channelName = new MessageBuilder("§fChannel: §b" + channel.getChannelName()).addHover("§fDistance: §b" + channel.getDistanze()
                                + "\n" + "§fIsMuted: §b" + channel.isMuted()
                                + "\n" + "§fIsGlobal: §b" + channel.isMuted()
                                + "\n" + "§fPermissions: §b" + channel.getPermissions()).getTextComponent();
                        p.spigot().sendMessage(channelName);
                    });

                    for (Player all : Bukkit.getOnlinePlayers()) {
                        ChannelPlayer channelPlayer = ChannelPlayer.getChannelPlayerByPlayer(all);
                        String channelName = channelPlayer.getChannel().getChannelName();
                        TextComponent playerChannel = new MessageBuilder("§f" + all.getName() + "´s Channel").addHover("§fChannel: §b" + channelName).getTextComponent();
                        p.spigot().sendMessage(playerChannel);
                    }
                }
            }
        }

        return false;
    }
}
