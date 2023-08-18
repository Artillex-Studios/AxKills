package com.artillexstudios.axkills;

import com.artillexstudios.axkills.commands.MainCommand;
import com.artillexstudios.axkills.config.AbstractConfig;
import com.artillexstudios.axkills.config.impl.Config;
import com.artillexstudios.axkills.listeners.DeathListener;
import com.artillexstudios.axkills.utils.ColorUtils;
import dev.dejvokep.boostedyaml.YamlDocument;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public final class AxKills extends JavaPlugin {
    private static AbstractConfig abstractConfig;
    public static YamlDocument CONFIG;
    private static AxKills instance;

    public static AxKills getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        int pluginId = 19561;
        new Metrics(this, pluginId);

        abstractConfig = new Config();
        abstractConfig.setup();
        CONFIG = abstractConfig.getConfig();

        new ColorUtils();

        this.getCommand("axkills").setExecutor(new MainCommand());

        getServer().getPluginManager().registerEvents(new DeathListener(), this);
    }

    public static AbstractConfig getAbstractConfig() {
        return abstractConfig;
    }
}
