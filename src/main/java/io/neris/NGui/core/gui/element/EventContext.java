package io.neris.NGui.core.gui.element;

import io.neris.NGui.core.utils.event.ItemStackEventUtils;
import lombok.Getter;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class EventContext {

    @Getter protected Event event;

    @Getter protected HumanEntity whoClicked;
    @Getter protected Inventory inventory;
    @Getter protected ClickType clickType;
    @Getter protected List<ItemStack> items = new ArrayList<>();

    public EventContext(@NotNull Event event) {
        this.event = event;
        tryInit(event);
    }

    public EventContext(EventContext other){
        this.event = other.event;
        this.whoClicked = other.whoClicked;
        this.inventory = other.inventory;
        this.clickType = other.clickType;
        this.items = other.items;
    }


    public void tryInit(Event event){
        if(initIfInventoryClickEvent(event)){
            return;
        }
    }

    protected boolean initIfInventoryClickEvent(Event event){
        if(!(event instanceof InventoryClickEvent inventoryClickEvent)){
            return false;
        }

        whoClicked = inventoryClickEvent.getWhoClicked();
        inventory = inventoryClickEvent.getClickedInventory();
        clickType = inventoryClickEvent.getClick();
        items = ItemStackEventUtils.extractItemStack(event);

        return true;
    }

}
