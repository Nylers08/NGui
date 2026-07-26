package io.neris.NGui.core.GuiElement.view;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public interface GuiElement {

    UUID uuid();
    void click(InventoryClickEvent event);
    ItemStack render();

}
