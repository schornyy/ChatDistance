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
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ChannelCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player)sender;
        ChannelPlayer channelPlayer = ChannelPlayer.getChannelPlayerByPlayer(p);

        /*
        channel list -X
        channel join <ChannelName> -X
        channel delete <ChannelName> -X
        channel create <Channelname> - X
        channel <ChannelName> setDistance <Zahl> -X
        channel <ChannelName> Mute - X
        channel <ChannelName> Global - X
        channel <Player> setChannel <ChannelName> - X
         */

        if(p.hasPermission("ChatDistance.edit")){
            if(args.length == 1) {
                if(args[0].equalsIgnoreCase("list")) {
                    Channel.getStoredChannel().forEach(channel -> {

                        TextComponent channelName = new MessageBuilder("§fChannel: §b" + channel.getChannelName()).addHover("§fDistance: §b" + channel.getDistanze()
                                + "\n" + "§fIsMuted: §b" + channel.isMuted()
                                + "\n" + "§fIsGlobal: §b" + channel.isMuted()
                                + "\n" + "§fPermissions: §b" + channel.getPermissions()).getTextComponent();
                        TextComponent delete = new MessageBuilder("§c[§4Delete§c]")
                                .addHover("§fKlicken um §b" + channel.getChannelName() + "§f zu löschen")
                                .addClick(ClickEvent.Action.RUN_COMMAND, "/channel delete " + channel.getChannelName())
                                .getTextComponent();
                        TextComponent mute = new MessageBuilder("§e[§6Mute§e]")
                                .addClick(ClickEvent.Action.RUN_COMMAND, "/channel " + channel.getChannelName() + " Mute")
                                .getTextComponent();
                        TextComponent global = new MessageBuilder("§b[§9Global§b]")
                                .addClick(ClickEvent.Action.RUN_COMMAND, "/channel " + channel.getChannelName() + " Global")
                                .getTextComponent();
                        TextComponent abstand = new MessageBuilder(" ").getTextComponent();
                        p.spigot().sendMessage(channelName, abstand, delete, abstand, mute, abstand, global);
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

                } else if(args[1].equalsIgnoreCase("Mute")) {
                    if(Channel.getChannelByName(args[0]) == null) {
                        p.sendMessage(ChatDistance.getPlugin(ChatDistance.class).getMessagesConfig().channelDosntExists);
                        return true;
                    }
                    Channel channel = Channel.getChannelByName(args[0]);
                    if(!channel.isMuted()) {
                        channel.setMuted(true);
                        p.sendMessage(ChatDistance.getPlugin(ChatDistance.class).getMessagesConfig().channelIsMuted);
                    } else {
                        channel.setMuted(false);
                        p.sendMessage(ChatDistance.getPlugin(ChatDistance.class).getMessagesConfig().channelIsnotLongerMuted);
                    }
                    return true;
                } else if(args[1].equalsIgnoreCase("Global")) {
                    if(Channel.getChannelByName(args[0]) == null) {
                        p.sendMessage(ChatDistance.getPlugin(ChatDistance.class).getMessagesConfig().channelDosntExists);
                        return true;
                    }
                    Channel channel = Channel.getChannelByName(args[0]);
                    if(!channel.isGlobal()) {
                        channel.setGlobal(true);
                        p.sendMessage(ChatDistance.getPlugin(ChatDistance.class).getMessagesConfig().channelisGlobal);
                    } else {
                        channel.setGlobal(false);
                        p.sendMessage(ChatDistance.getPlugin(ChatDistance.class).getMessagesConfig().channelIsnotLongerGlobal);
                    }
                    return true;
                } else if(args[1].equalsIgnoreCase("setChannel")) {
                    if(Bukkit.getPlayer(args[0]) == null) {
                        p.sendMessage(ChatDistance.getPlugin(ChatDistance.class).getMessagesConfig().playerIsNotOnline);
                        return true;
                    }
                    if(Channel.getChannelByName(args[2]) == null) {
                        p.sendMessage(ChatDistance.getPlugin(ChatDistance.class).getMessagesConfig().channelDosntExists);
                        return true;
                    }
                    ChannelPlayer targetChannelPlayer = ChannelPlayer.getChannelPlayerByPlayer(Bukkit.getPlayer(args[0]));
                    targetChannelPlayer.setChannel(Channel.getChannelByName(args[2]));
                    p.sendMessage(ChatDistance.getPlugin(ChatDistance.class)
                            .getMessagesConfig().playerSetPlayersChannel
                            .replaceAll("%player%", args[0])
                            .replaceAll("%channel%", args[2]));

                    return true;
                }
            } else if(args.length == 3) {
                if(args[1].equalsIgnoreCase("setDistance")) {
                    if(Channel.getChannelByName(args[0]) == null) {
                        p.sendMessage(ChatDistance.getPlugin(ChatDistance.class).getMessagesConfig().channelDosntExists);
                        return true;
                    }
                    Channel.getChannelByName(args[0]).setDistanze(Integer.valueOf(args[2]));
                    p.sendMessage(ChatDistance.getPlugin(ChatDistance.class).getMessagesConfig().channelChangeDistance);
                }
            }
        }

       if(args.length == 2) {
           if (args[0].equalsIgnoreCase("join")) {
               if(Channel.getChannelByName(args[1]) == null) {
                   p.sendMessage(ChatDistance.getPlugin(ChatDistance.class).getMessagesConfig().channelDosntExists);
                   return true;
               }
               channelPlayer.setChannel(Channel.getChannelByName(args[1]));
               p.sendMessage(ChatDistance.getPlugin(ChatDistance.class).getMessagesConfig().channelJoined.replaceAll("%channel%", args[1]));
           }
       }

        return false;
    }

        /*
        channel list
        channel join <ChannelName>
        channel delete <ChannelName>
        channel create <Channelname>
        channel <ChannelName> setDistance <Zahl>
        channel <ChannelName> Mute
        channel <ChannelName> Global
        channel <Player> setChannel <ChannelName>
         */

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        Player p = (Player)sender;
        List<String> tabs = new ArrayList<>();

        if(p.hasPermission("chatdistance.edit")) {
            switch (args.length) {
                case 1:
                    tabs.add("list");
                    tabs.add("delete");
                    tabs.add("join");
                    tabs.add("create");
                    Bukkit.getOnlinePlayers().forEach(all -> tabs.add(all.getName()));
                    Channel.getStoredChannel().forEach(channel -> tabs.add(channel.getChannelName()));
                    break;
                case 2:
                    if(args[0].equalsIgnoreCase("delete")) {
                        Channel.getStoredChannel().forEach(channel -> tabs.add(channel.getChannelName()));
                    } else if(args[0].equalsIgnoreCase("create")) {
                        tabs.add("<ChannelName>");
                    } else if(Channel.getChannelByName(args[0]) != null) {
                        tabs.add("Mute");
                        tabs.add("Global");
                        tabs.add("SetDistance");
                    } else if(Bukkit.getPlayer(args[0]) != null) {
                        tabs.add("setChannel");
                    } else if(args[0].equalsIgnoreCase("join")) {
                        Channel.getStoredChannel().forEach(channel -> {
                            if(p.hasPermission(channel.getPermissions())) {
                                tabs.add(channel.getChannelName());
                            }
                        });
                    }
                    break;
                case 3:
                    if(args[1].equalsIgnoreCase("setChannel")) {
                        Channel.getStoredChannel().forEach(channel -> {
                            if(p.hasPermission(channel.getPermissions())) {
                                tabs.add(channel.getChannelName());
                            }
                        });
                    } else if(args[1].equalsIgnoreCase("SetDistance")) {
                        tabs.add("<Distance>");
                    }
                    break;
            }
        } else {
            switch (args.length) {
                case 2:
                    if(args[0].equalsIgnoreCase("Join")) {
                        Channel.getStoredChannel().forEach(channel -> {
                            if(p.hasPermission(channel.getPermissions())) {
                                tabs.add(channel.getChannelName());
                            }
                        });
                    }
                    break;
            }
        }

        return tabs;
    }
}
