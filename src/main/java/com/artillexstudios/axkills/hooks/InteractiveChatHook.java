package com.artillexstudios.axkills.hooks;

import com.loohp.interactivechat.api.InteractiveChatAPI;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class InteractiveChatHook {

    public static String markSender(@NotNull Player killer) {
        return InteractiveChatAPI.markSender("[item]", killer.getUniqueId());
    }
}
