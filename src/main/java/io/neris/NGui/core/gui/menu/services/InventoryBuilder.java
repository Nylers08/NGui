package io.neris.NGui.core.gui.menu.services;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class InventoryBuilder {

    private InventoryType type = InventoryType.CHEST;
    private int size = type.getDefaultSize();
    private Component title = type.defaultTitle();
    private InventoryHolder holder = null;

    public void type(InventoryType type){
        this.type = type;
    }

    public void size(int size){
        this.size = size;
    }

    public void title(Component title){
        this.title = title;
    }

    public void holder(InventoryHolder holder){
        this.holder = holder;
    }


    public Inventory build(){
        if(type != InventoryType.CHEST){
            return Bukkit.createInventory(holder, size, title);
        } else {
            if(title.equals(InventoryType.CHEST.defaultTitle())){
                title = type.defaultTitle();
            }
            return Bukkit.createInventory(holder, type, title);
        }
    }
}
