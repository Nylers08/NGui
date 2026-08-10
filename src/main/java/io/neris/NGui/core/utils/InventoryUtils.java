package io.neris.NGui.core.utils;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class InventoryUtils {

    public static Inventory copyInv(Inventory other, int size){
        throwIfNotInvType(other, InventoryType.CHEST);

        InventoryHolder holder = other.getHolder();
        Inventory inventory = Bukkit.createInventory(holder, size);
        copyContentsTo(inventory, other);
        return inventory;
    }

    public static Inventory copyInv(Inventory other, int size, Component title){
        throwIfNotInvType(other, InventoryType.CHEST);

        InventoryHolder holder = other.getHolder();
        Inventory inventory = Bukkit.createInventory(holder, size, title);
        copyContentsTo(inventory, other);
        return inventory;
    }

    public static Inventory copyInv(Inventory other, Component title){
        Inventory inventory;

        InventoryType type = other.getType();
        InventoryHolder owner = other.getHolder();

        if(type != InventoryType.CHEST){
            inventory = Bukkit.createInventory(owner, type, title);
        } else {
            int size = other.getSize();
            inventory = Bukkit.createInventory(owner, size, title);
        }

        copyContentsTo(inventory, other);

        return inventory;
    }

    public static Inventory copyInv(Inventory other){
        Inventory inventory;

        InventoryType type = other.getType();
        InventoryHolder owner = other.getHolder();

        if(type != InventoryType.CHEST){
            inventory = Bukkit.createInventory(owner, type);
        } else {
            int size = other.getSize();
            inventory = Bukkit.createInventory(owner, size);
        }

        copyContentsTo(inventory, other);

        return inventory;
    }


    public static void copyContentsTo(Inventory target, Inventory source){
        ItemStack[] contents = getCopyContents(target, source);
        target.setContents(contents);
    }

    public static ItemStack[] getCopyContents(Inventory source, int sizeLimit){
        return Arrays.stream(source.getContents())
                .limit(sizeLimit)
                .map(item -> item == null ? null : item.clone())
                .toArray(ItemStack[]::new);
    }

    public static ItemStack[] getCopyContents(Inventory source){
        return Arrays.stream(source.getContents())
                .map(item -> item == null ? null : item.clone())
                .toArray(ItemStack[]::new);
    }


    private static ItemStack[] getCopyContents(Inventory target, Inventory source){
        if(target.getType() != source.getType()){
            throw new RuntimeException("Неверный тип инвентарей, при копировании содержимого");
        }

        ItemStack[] contents;
        if(target.getType() == InventoryType.CHEST){
            int limit = target.getSize();
            contents = getCopyContents(source, limit);
        }
        else {
            contents = getCopyContents(source);
        }

        return contents;
    }

    private static void throwIfNotInvType(Inventory inventory, InventoryType type){
        if(inventory.getType() != type){
            throw new RuntimeException(String.format("Копируемый инвентарь, должен мыть типа \"%s\"", type.toString()));
        }
    }
}
