package io.neris.NGui.core.listeners.guiElement;

import io.neris.NGui.core.gui.element.EventContext;
import io.neris.NGui.core.gui.element.MainItemEventContext;
import io.neris.NGui.core.gui.element.services.nbtAction.NbtActionExecutor;
import io.neris.NGui.core.gui.element.services.slotBinding.GuiElementBindingSystem;
import io.neris.NGui.core.gui.element.element.GuiElement;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class GuiElementClickListener implements Listener {

    private final GuiElementBindingSystem elementRegistry;
    private final NbtActionExecutor nbtActionExecutor;

    public GuiElementClickListener(@NotNull GuiElementBindingSystem elementRegistry,
                                   @NotNull NbtActionExecutor nbtActionExecutor) {
        this.elementRegistry = elementRegistry;
        this.nbtActionExecutor = nbtActionExecutor;
    }

    @EventHandler
    public void guiElementClick(InventoryClickEvent event){
        GuiElement element = tryGetGuiElement(event);
        if(element == null){
            return;
        }

        element.click(event);
    }

    private @Nullable GuiElement tryGetGuiElement(InventoryClickEvent event){
        GuiElement element = tryGetElementByClick(event);
        if(element != null){
            return element;
        }

        return tryGetElementByHotbar(event);
    }

    private @Nullable GuiElement tryGetElementByClick(InventoryClickEvent event){
        Inventory inventory = event.getClickedInventory();
        if(inventory == null){
            return null;
        }
        int slot = event.getSlot();
        return elementRegistry.getGuiElement(inventory, slot);
    }

    private @Nullable GuiElement tryGetElementByHotbar(InventoryClickEvent event){
        Inventory inventory = event.getClickedInventory();
        if(inventory == null){
            return null;
        }

        int newHotbarSlot = event.getHotbarButton();
        return elementRegistry.getGuiElement(inventory, newHotbarSlot);
    }


    @EventHandler
    public void nbtActionExecute(InventoryClickEvent event){
        EventContext clickContext = new EventContext(event);
        for (ItemStack itemStack : clickContext.getItems()){
            MainItemEventContext mainItemEventContext = new MainItemEventContext(clickContext, itemStack);
            nbtActionExecutor.execute(mainItemEventContext, itemStack);
        }
    }

}
