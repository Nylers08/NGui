package io.neris.NGui.core.services;

import io.neris.NGui.core.gui.element.services.GuiElementRegistry;
import io.neris.NGui.core.services.nbtTagger.GuiElementTagger;
import io.neris.NGui.core.services.nbtTagger.NBTTagger;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class ServiceController {

    @Getter private final JavaPlugin plugin;

    @Getter private final NBTTagger nbtTagger;
    @Getter private final GuiElementTagger guiElementTagger;
    @Getter private final GuiElementRegistry elementRegistry;

    public ServiceController(JavaPlugin plugin) {
        this.plugin = plugin;

        this.nbtTagger = new NBTTagger(plugin);
        this.elementRegistry = new GuiElementRegistry(plugin);
        this.guiElementTagger = new GuiElementTagger(nbtTagger);
    }
}
