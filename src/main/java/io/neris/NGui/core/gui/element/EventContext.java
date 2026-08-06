package io.neris.NGui.core.gui.element;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class EventContext {

    public Event event;

    public HumanEntity whoClicked;
    public Inventory inventory;
    public ClickType clickType;
    public List<ItemStack> items = new ArrayList<>();

    public EventContext(@NotNull Event event){
        this.event = event;
    }

    public EventContext(@NotNull EventContext other){
        this.event = other.event;
        this.whoClicked = other.whoClicked;
        this.inventory = other.inventory;
        this.clickType = other.clickType;
        this.items = other.items;
    }
}
