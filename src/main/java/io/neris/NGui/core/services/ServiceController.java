package io.neris.NGui.core.services;

import io.neris.NGui.core.gui.element.services.GuiElementInstaller;
import io.neris.NGui.core.gui.element.services.GuiElementPosRegistry;
import io.neris.NGui.core.gui.element.services.GuiElementRegistry;
import io.neris.NGui.core.gui.element.services.GuiUuidElementRegistry;
import io.neris.NGui.core.services.nbtTagger.GuiElementTagger;
import io.neris.NGui.core.services.nbtTagger.NBTTagger;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class ServiceController {

    @Getter private final JavaPlugin plugin;

    @Getter private final NBTTagger nbtTagger;
    @Getter private final GuiElementTagger guiElementTagger;

    @Getter private final GuiElementPosRegistry elementPosRegistry;
    @Getter private final GuiUuidElementRegistry uuidElementRegistry;
    @Getter private final GuiElementRegistry elementRegistry;
    @Getter private final GuiElementInstaller elementInstaller;

    public ServiceController(JavaPlugin plugin) {
        this.plugin = plugin;

        this.nbtTagger = new NBTTagger(plugin);
        this.guiElementTagger = new GuiElementTagger(nbtTagger);

        this.elementPosRegistry = new GuiElementPosRegistry();
        this.uuidElementRegistry = new GuiUuidElementRegistry();
        this.elementRegistry = new GuiElementRegistry(elementPosRegistry, uuidElementRegistry);
        this.elementInstaller = new GuiElementInstaller(elementRegistry);
    }
}
