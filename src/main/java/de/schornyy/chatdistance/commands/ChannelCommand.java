package de.schornyy.chatdistance.commands;

import de.schornyy.chatdistance.ChatDistance;
import de.schornyy.chatdistance.channel.Channel;
import de.schornyy.chatdistance.channelplayer.ChannelPlayer;
import de.schornyy.chatdistance.utils.MessageBuilder;
import net.md_5.bungee.api.chat.ClickEvent;
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
        ChannelPlayer channelPlayer = ChannelPlayer.getChannelPlayerByPlayer(p);

        /*
        channel list
        channel join <ChannelName>
        channel delete <ChannelName>
        channel create <Channelname>
        channel <ChannelName> setDistance <Zahl>
        channel <ChannelName> Mute
        channel <ChannelName> Global

         */

        if(p.hasPermission("ChatDistance.edit")){
            if(args.length == 1) {
                if(args[0].equalsIgnoreCase("list")) {
                    Channel.getStoredChannel().forEach(channel -> {

                        TextComponent channelName = new MessageBuilder("§fChannel: §b" + channel.getChannelName()).addHover("§fDistance: §b" + channel.getDistanze()
                                + "\n" + "§fIsMuted: §b" + channel.isMuted()
                                + "\n" + "§fIsGlobal: §b" + channel.isMuted()
                                + "\n" + "§fPermissions: §b" + channel.getPermissions()).getTextComponent();
                        TextComponent delete = new MessageBuilder(" §c[§4Delete§c]")
                                .addHover("§fKlicken um §b" + channel.getChannelName() + "§f zu löschen")
                                .addClick(ClickEvent.Action.RUN_COMMAND, "/channel delete " + channel.getChannelName())
                                .getTextComponent();
                        p.spigot().sendMessage(channelName, delete);
                    });

                }
            } else if(args.length == 2) {
                if(args[0].equalsIgnoreCase("delete")) {
                    if(Channel.getChannelByName(args[1]) == null) {
                        p.sendMessage(ChatDistance.getPlugin(ChatDistance.class).getMessagesConfig().channelDosntExists);
                        return true;
                    }
                    Channel.getChannelByName(args[1]).delete();
                    p.sendMessage(ChatDistance.getPlugin(ChatDistance.class).getMessagesConfig().channelDeleted);
                } else if (args[0].equalsIgnoreCase("create")) {
                    if(Channel.getChannelByName(args[1]) != null) {
                        p.sendMessage(ChatDistance.getPlugin(ChatDistance.class).getMessagesConfig().channelAlreadyExists);
                        return true;
                    }
                    Channel channel = new Channel(args[1]);
                    channel.create();
                    p.sendMessage(ChatDistance.getPlugin(ChatDistance.class).getMessagesConfig().channelCraeted);

                } else if (args[0].equalsIgnoreCase("join")) {
                    if(Channel.getChannelByName(args[1]) == null) {
                        p.sendMessage(ChatDistance.getPlugin(ChatDistance.class).getMessagesConfig().channelDosntExists);
                        return true;
                    }
                    channelPlayer.setChannel(Channel.getChannelByName(args[1]));
                    p.sendMessage(ChatDistance.getPlugin(ChatDistance.class).getMessagesConfig().channelJoined.replaceAll("%channel%", args[1]));
                }
            }
        }

        return false;
    }
}
