package com.artillexstudios.axkills.hooks;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlaceholderAPIHook {

    public static @NotNull String setPlaceholders(@NotNull Player player, @NotNull String msg) {
        return PlaceholderAPI.setPlaceholders(player, msg);
    }
}
