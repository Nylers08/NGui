package io.neris.NGui.core.GuiElement.services;

import io.neris.NGui.core.GuiElement.view.GuiElement;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class GuiElementRegistry {

    @Getter private final Map<UUID, GuiElement> registry = new HashMap<>();

    @Getter private final JavaPlugin plugin;

    @Getter private final int clearMapIntervalInTicks = 20 * 3;

    public GuiElementRegistry(@NotNull JavaPlugin plugin){
        this.plugin = plugin;
    }


    public void register(@NotNull GuiElement guiElement){
        registry.put(guiElement.uuid(), guiElement);
    }

    public void unregister(@NotNull UUID uuid) {
        registry.remove(uuid);
    }

    public void unregister(@NotNull GuiElement element){
        registry.remove(element.uuid());
    }

    public GuiElement get(UUID uuid){
        return registry.get(uuid);
    }



}
