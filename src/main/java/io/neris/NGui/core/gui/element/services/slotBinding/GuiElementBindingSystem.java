package io.neris.NGui.core.gui.element.services.slotBinding;

import io.neris.NGui.core.gui.element.GuiElementPosition;
import io.neris.NGui.core.gui.element.view.GuiElement;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class GuiElementBindingSystem {

    private final GuiElementInventoryIndex inventoryIndex;
    private final GuiElementRegistry elementRegistry;

    public GuiElementBindingSystem(@NotNull GuiElementInventoryIndex inventoryIndex,
                                   @NotNull GuiElementRegistry elementRegistry) {

        this.inventoryIndex = inventoryIndex;
        this.elementRegistry = elementRegistry;
    }


    public void register(GuiElementPosition position, GuiElement element) {
        inventoryIndex.register(position);

        if (!elementRegistry.containsUUID(element.uuid())) {
            elementRegistry.register(element);
        }
    }

    public void unregister(@NotNull Inventory inventory, int slot) {
        UUID uuid = getGuiElementUUID(inventory, slot);
        unregister(inventory, uuid);
    }

    public void unregister(@NotNull Inventory inventory, @NotNull UUID elementUUID) {
        inventoryIndex.unregister(inventory, elementUUID);
        elementRegistry.unregister(elementUUID);
    }


    public GuiElement getGuiElement(@NotNull Inventory inventory, int slot){
        UUID uuidGuiElement = getGuiElementUUID(inventory, slot);
        return elementRegistry.get(uuidGuiElement);
    }

    public UUID getGuiElementUUID(@NotNull Inventory inventory, int slot) {
        return inventoryIndex.getGuiElementUUID(inventory, slot);
    }

    public int getGuiElementSlot(@NotNull Inventory inventory, @NotNull UUID elementUUID) {
        return inventoryIndex.getSlotUUID(inventory, elementUUID);
    }

    public Inventory getInventory(@NotNull UUID elementUUID) {
        return inventoryIndex.getInventory(elementUUID);
    }

}
