package io.neris.NGui.core.listeners.guiElement;

import io.neris.NGui.core.gui.element.services.slotBinding.GuiElementBindingSystem;
import io.neris.NGui.core.gui.element.view.GuiElement;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;



public class GuiElementClickListener implements Listener {

    private final GuiElementBindingSystem elementRegistry;

    public GuiElementClickListener(@NotNull GuiElementBindingSystem elementRegistry) {
        this.elementRegistry = elementRegistry;
    }

    @EventHandler
    public void guiElementClick(InventoryClickEvent event){
        Inventory inventory = event.getClickedInventory();
        if(inventory == null){
            return;
        }
        int slot = event.getSlot();

        GuiElement element = elementRegistry.getGuiElement(inventory, slot);
        if(element == null){
            return;
        }

        element.click(event);
    }


}
