package io.neris.NGui.core.GuiElement.services;

import io.neris.NGui.core.GuiElement.view.GuiElement;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class GuiElementRegistry {

    @Getter private final Map<UUID, GuiElement> registry = new HashMap<>();
    @Getter private final Map<WeakReference<ItemStack>, UUID> weakItemStackRegistry = new HashMap<>();

    @Getter private final JavaPlugin plugin;

    @Getter private final int clearMapIntervalInTicks = 20 * 300;

    public GuiElementRegistry(JavaPlugin plugin){
        this.plugin = plugin;
        clearMapInterval();
    }


    public void register(GuiElement guiElement){
        registry.put(guiElement.uuid(), guiElement);
        weakItemStackRegistry.put(new WeakReference<>(guiElement.getItem()), guiElement.uuid());
    }


    public GuiElement get(UUID uuid){
        return registry.get(uuid);
    }


    private void clearMap(){
        Set<WeakReference<ItemStack>> itemSet = weakItemStackRegistry.keySet();
        for (WeakReference<ItemStack> item : itemSet){
            if(item.get() == null){
                UUID uuid = weakItemStackRegistry.get(item);
                registry.remove(uuid);
                weakItemStackRegistry.remove(item);
            }
        }
    }

    private void clearMapInterval(){
        Bukkit.getScheduler().runTaskTimer(plugin, this::clearMap, 0, clearMapIntervalInTicks);
    }


}
