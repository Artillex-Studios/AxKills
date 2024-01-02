package com.artillexstudios.axkills.utils;

import com.artillexstudios.axkills.hooks.InteractiveChatHook;
import com.artillexstudios.axkills.hooks.PlaceholderAPIHook;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import static com.artillexstudios.axkills.AxKills.CONFIG;

public class Utils {
    public static String setPlaceholders(@NotNull Player player, @NotNull String msg) {

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            return PlaceholderAPIHook.setPlaceholders(player, msg);
        } else {
            return msg;
        }
    }
    public static String setItem(@NotNull Player player) {

        if (Bukkit.getPluginManager().getPlugin("InteractiveChat") != null) {
            return InteractiveChatHook.markSender(player);
        } else {
            if (player.getInventory().getItemInHand() == null) return "air";
            final ItemStack it = player.getInventory().getItemInHand();
            String typeStr = it.getType().toString().replace("_", " ").toLowerCase();

            typeStr = typeStr.replace("air", CONFIG.getString("empty-hand-text"));

            if (it.getItemMeta() == null) return CONFIG.getString("item-format").replace("%item%", typeStr);
            final ItemMeta meta = it.getItemMeta();

            return CONFIG.getString("item-format").replace("%item%", meta.hasDisplayName() ? meta.getDisplayName() : typeStr);
        }
    }
}
