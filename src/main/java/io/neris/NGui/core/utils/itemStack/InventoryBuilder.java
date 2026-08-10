package io.neris.NGui.core.utils.itemStack;

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

    public InventoryBuilder type(InventoryType type){
        this.type = type;
        return this;
    }

    public InventoryBuilder size(int size){
        this.size = size;
        return this;
    }

    public InventoryBuilder title(Component title){
        this.title = title;
        return this;
    }

    public InventoryBuilder holder(InventoryHolder holder){
        this.holder = holder;
        return this;
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
