package io.neris.NGui.core.gui.menu;

import io.neris.NGui.core.utils.InventoryUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BaseMenu implements Menu{

    protected Inventory inventory;
    protected Component title;

    public BaseMenu(Inventory inventory, Component title){
        this.inventory = inventory;
        this.title = title;
    }

    public BaseMenu(Inventory inventory){
        this(inventory, null);
    }


    @Override
    public void setItem(ItemStack item, int... slots) {
        for (int slot : slots){
            inventory.setItem(slot, item);
        }
    }

    @Override
    public ItemStack getItem(int slot) {
        return inventory.getItem(slot);
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public Component getTitle() {
        return title;
    }


    @Override
    public void changeName(Component title) {
        this.title = title;
        reopen();
    }

    @Override
    public void changeSize(int size) {
        if(title == null){
            inventory = InventoryUtils.copyInv(inventory, size);
        } else {
            inventory = InventoryUtils.copyInv(inventory, size, title);
        }
        reopen();
    }


    @Override
    public void reopen(){
        inventory.getViewers().forEach(v->v.openInventory(inventory));
    }

    @Override
    public void clear() {
        inventory.clear();
    }
}
