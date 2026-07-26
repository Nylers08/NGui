package io.neris.NGui.core.services;

import io.neris.NGui.core.services.nbtTagger.NBTTagger;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class ServiceController {

    @Getter private final JavaPlugin plugin;

    @Getter private final NBTTagger nbtTagger;

    public ServiceController(JavaPlugin plugin) {
        this.plugin = plugin;

        this.nbtTagger = new NBTTagger(plugin);
    }
}
