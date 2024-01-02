package com.artillexstudios.axkills.commands;

import com.artillexstudios.axkills.AxKills;
import com.artillexstudios.axkills.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class MainCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if (!sender.hasPermission("axkills.reload") && !sender.hasPermission("axkills.admin") && !sender.hasPermission("axkills.*")) {
            MessageUtils.sendMsgP(sender, "errors.no-permission");
            return true;
        }
        
        AxKills.getAbstractConfig().reloadConfig();
        MessageUtils.sendMsgP(sender, "reload");
        return true;
    }
}
