package com.artillexstudios.axkills.utils;

import com.artillexstudios.axkills.hooks.PlaceholderAPIHook;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Utils {

    public static String setPlaceholders(@NotNull Player player, @NotNull String msg) {

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            return PlaceholderAPIHook.setPlaceholders(player, msg);
        } else {
            return msg;
        }
    }
}
