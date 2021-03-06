package com.ullarah.upostal.command;

import com.ullarah.upostal.function.CommonString;
import com.ullarah.upostal.function.PlayerProfile;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

import static com.ullarah.upostal.PostalInit.getInboxDataPath;
import static com.ullarah.upostal.PostalInit.getPlugin;

public class Blacklist {

    public void toggle(CommandSender sender, String[] args) {

        CommonString commonString = new CommonString();

        if (!sender.hasPermission("postal.blacklist")) {
            commonString.messagePermDeny(getPlugin(), sender);
            return;
        }

        if (args.length >= 2) {

            try {

                File inboxFile = new File(getInboxDataPath(), new PlayerProfile().lookup(args[1]).getId().toString() + ".yml");

                if (inboxFile.exists()) {

                    FileConfiguration inboxConfig = YamlConfiguration.loadConfiguration(inboxFile);

                    String inboxPlayerName = (String) inboxConfig.get("name");
                    Boolean inboxBlacklist = (Boolean) inboxConfig.get("blacklist");

                    if (inboxBlacklist) {
                        inboxConfig.set("blacklist", false);
                        commonString.messageSend(getPlugin(), (Player) sender, ChatColor.GREEN + inboxPlayerName + " no longer blacklisted.");
                    } else {
                        inboxConfig.set("blacklist", true);
                        commonString.messageSend(getPlugin(), (Player) sender, ChatColor.RED + inboxPlayerName + " is now blacklisted.");
                    }

                    inboxConfig.save(inboxFile);

                } else
                    commonString.messageSend(getPlugin(), (Player) sender, "That player does not have an inbox!");

            } catch (IOException e) {

                e.printStackTrace();

            }

        } else
            commonString.messageSend(getPlugin(), (Player) sender, ChatColor.YELLOW + "/postal blacklist <player>");

    }


}
