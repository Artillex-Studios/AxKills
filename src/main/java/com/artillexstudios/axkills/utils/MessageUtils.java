package com.artillexstudios.axkills.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static com.artillexstudios.axkills.AxKills.CONFIG;

public class MessageUtils {

    public static void sendMsgP(@NotNull CommandSender p, String path) {
        p.sendMessage(ColorUtils.format(CONFIG.getString("prefix") + CONFIG.getString(path)));
    }

    public static void sendMsgP(@NotNull Player p, String path) {
        p.sendMessage(ColorUtils.format(CONFIG.getString("prefix") + CONFIG.getString(path)));
    }

    public static void sendMsgP(@NotNull Player p, String path, @NotNull Map<String, String> replacements) {
        AtomicReference<String> message = new AtomicReference<>(CONFIG.getString(path));
        replacements.forEach((key, value) -> message.set(message.get().replace(key, value)));
        p.sendMessage(ColorUtils.format(CONFIG.getString("prefix") + message));
    }

    public static void sendMsgP(@NotNull CommandSender p, String path, @NotNull Map<String, String> replacements) {
        AtomicReference<String> message = new AtomicReference<>(CONFIG.getString(path));
        replacements.forEach((key, value) -> message.set(message.get().replace(key, value)));
        p.sendMessage(ColorUtils.format(CONFIG.getString("prefix") + message));
    }

    public static void sendMsg(@NotNull Player p, String path) {
        p.sendMessage(ColorUtils.format(CONFIG.getString(path)));
    }

    public static void sendListMsg(Player p, String path) {
        for (String m : CONFIG.getStringList(path)) {
            p.sendMessage(ColorUtils.format(m));
        }
    }

    public static void sendListMsg(CommandSender p, String path) {
        for (String m : CONFIG.getStringList(path)) {
            p.sendMessage(ColorUtils.format(m));
        }
    }
}