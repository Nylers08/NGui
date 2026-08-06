package io.neris.NGui.core.command;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class TestInventoryRegistry {

    public Set<WeakReference<Inventory>> invetorySet = new HashSet<>();

    private final JavaPlugin plugin;

    public TestInventoryRegistry(JavaPlugin plugin) {
        this.plugin = plugin;
        startCleanCycle();
    }


    public void register(Inventory inventory){
        invetorySet.add(new WeakReference<>(inventory));
    }

    public int size(){
        return invetorySet.size();
    }

    public void cleanEmptyReference(){
        invetorySet.removeIf(ref->ref.get()==null);
    }

    public void startCleanCycle(){
        Bukkit.getScheduler().runTaskTimer(
                plugin,
                this::cleanEmptyReference,
                20, 20);
    }
}
