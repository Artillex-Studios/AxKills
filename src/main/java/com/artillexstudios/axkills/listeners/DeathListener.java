package com.artillexstudios.axkills.listeners;

import com.artillexstudios.axkills.hooks.InteractiveChatHook;
import com.artillexstudios.axkills.utils.ColorUtils;
import com.artillexstudios.axkills.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.NotNull;

import static com.artillexstudios.axkills.AxKills.CONFIG;


public class DeathListener implements Listener {

    @EventHandler
    public void onDeath(@NotNull PlayerDeathEvent event) {
        final Player p = event.getEntity();

        String msg;

        if (p.getKiller() != null) {
            final Player killer = p.getKiller();

            msg = CONFIG.getString("death-messages.KILLED");
            msg = msg.replace("%attacker%", killer.getName());
            msg = msg.replace("%victim%", p.getName());

            if (Bukkit.getPluginManager().getPlugin("InteractiveChat") != null)
                msg = msg.replace("%item%", InteractiveChatHook.markSender(killer));
            else
                msg = msg.replace("%item%", "<You need InteractiveChat for the item feature>");

            StringBuilder finalTxt = new StringBuilder();
            String[] message = msg.split("");
            String tempPlaceholder = "";
            boolean canbePlaceholder = true;
            for (String str : message) {

                if (str.equals("%") && canbePlaceholder) {
                    if (tempPlaceholder.equals("")) {
                        tempPlaceholder += str;
                    } else {
                        tempPlaceholder += str;
                        if (tempPlaceholder.contains("[ATTACKER]")) {
                            tempPlaceholder = tempPlaceholder.replace("[ATTACKER] ", "");
                            tempPlaceholder = tempPlaceholder.replace("[ATTACKER]", "");
                            finalTxt.append(Utils.setPlaceholders(killer, tempPlaceholder));
                        } else {
                            finalTxt.append(Utils.setPlaceholders(p, tempPlaceholder));
                        }
                        tempPlaceholder = "";
                    }
                    continue;
                }

                if (!tempPlaceholder.equals("")) {
                    tempPlaceholder += str;
                    continue;
                }

                canbePlaceholder = true;

                if (str.equals("\\")) canbePlaceholder = false;
                finalTxt.append(str);
            }

            msg = finalTxt.toString();

        } else if (event.getEntity().getLastDamageCause() != null && CONFIG.isString("death-messages." + event.getEntity().getLastDamageCause().getCause())) {
            msg = CONFIG.getString("death-messages." + event.getEntity().getLastDamageCause().getCause());
        } else {
            msg = "";
        }

        msg = msg.replace("%victim%", p.getName());
        msg = Utils.setPlaceholders(p, msg);

        event.setDeathMessage(ColorUtils.format(msg));
    }
}
