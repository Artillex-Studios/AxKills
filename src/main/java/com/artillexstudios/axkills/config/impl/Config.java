package com.artillexstudios.axkills.config.impl;

import com.artillexstudios.axkills.AxKills;
import com.artillexstudios.axkills.config.AbstractConfig;
import dev.dejvokep.boostedyaml.YamlDocument;
import dev.dejvokep.boostedyaml.dvs.versioning.BasicVersioning;
import dev.dejvokep.boostedyaml.settings.dumper.DumperSettings;
import dev.dejvokep.boostedyaml.settings.general.GeneralSettings;
import dev.dejvokep.boostedyaml.settings.loader.LoaderSettings;
import dev.dejvokep.boostedyaml.settings.updater.UpdaterSettings;
import org.bukkit.event.entity.EntityDamageEvent;

import java.io.File;
import java.io.IOException;

public class Config implements AbstractConfig {
    private YamlDocument file = null;

    @Override
    public void setup() {
        try {
            file = YamlDocument.create(new File(AxKills.getInstance().getDataFolder(), "config.yml"), AxKills.getInstance().getResource("config.yml"), GeneralSettings.builder().setUseDefaults(false).build(), LoaderSettings.DEFAULT, DumperSettings.DEFAULT, UpdaterSettings.builder().setKeepAll(true).setVersioning(new BasicVersioning("version")).build());
            file.update();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        for (EntityDamageEvent.DamageCause cause : EntityDamageEvent.DamageCause.values()) {
            if (file.isString("death-messages." + cause.toString())) continue;
            file.set("death-messages." + cause, "&#33ff33%victim% &fdied!");
        }

        saveConfig();
    }

    @Override
    public YamlDocument getConfig() {
        return file;
    }

    @Override
    public void reloadConfig() {
        try {
            file.reload();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void saveConfig() {
        try {
            file.save();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
