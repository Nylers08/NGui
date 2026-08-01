package io.neris.NGui.core.services;

import io.neris.NGui.core.gui.element.services.slotBinding.GuiElementInstaller;
import io.neris.NGui.core.gui.element.services.slotBinding.GuiElementInventoryIndex;
import io.neris.NGui.core.gui.element.services.slotBinding.GuiElementBindingSystem;
import io.neris.NGui.core.gui.element.services.slotBinding.GuiElementRegistry;
import io.neris.NGui.core.services.nbtTagger.GuiElementTagger;
import io.neris.NGui.core.services.nbtTagger.NBTTagger;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class ServiceController {

    @Getter private final JavaPlugin plugin;

    @Getter private final NBTTagger nbtTagger;
    @Getter private final GuiElementTagger guiElementTagger;

    @Getter private final GuiElementInventoryIndex elementPosRegistry;
    @Getter private final GuiElementRegistry uuidElementRegistry;
    @Getter private final GuiElementBindingSystem elementRegistry;
    @Getter private final GuiElementInstaller elementInstaller;

    public ServiceController(JavaPlugin plugin) {
        this.plugin = plugin;

        this.nbtTagger = new NBTTagger(plugin);
        this.guiElementTagger = new GuiElementTagger(nbtTagger);

        this.elementPosRegistry = new GuiElementInventoryIndex();
        this.uuidElementRegistry = new GuiElementRegistry();
        this.elementRegistry = new GuiElementBindingSystem(elementPosRegistry, uuidElementRegistry);
        this.elementInstaller = new GuiElementInstaller(elementRegistry);
    }
}
