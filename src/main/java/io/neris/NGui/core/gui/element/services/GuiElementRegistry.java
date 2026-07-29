package io.neris.NGui.core.gui.element.services;

import io.neris.NGui.core.gui.element.GuiElementPosition;
import io.neris.NGui.core.gui.element.view.GuiElement;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public record GuiElementRegistry(GuiElementPosRegistry posRegistry, GuiUuidElementRegistry uuidRegistry) {

    public GuiElementRegistry(@NotNull GuiElementPosRegistry posRegistry,
                              @NotNull GuiUuidElementRegistry uuidRegistry) {
        this.posRegistry = posRegistry;
        this.uuidRegistry = uuidRegistry;
    }


    public void register(GuiElementPosition position, GuiElement element) {
        posRegistry.register(position);

        if (!uuidRegistry.containsUUID(element.uuid())) {
            uuidRegistry.register(element);
        }
    }

    public void unregister(@NotNull Inventory inventory, int slot) {
        UUID uuid = getGuiElementUUID(inventory, slot);
        unregister(inventory, uuid);
    }

    public void unregister(@NotNull Inventory inventory, @NotNull UUID elementUUID) {
        posRegistry.unregister(inventory, elementUUID);
        uuidRegistry.unregister(elementUUID);
    }


    public GuiElement getGuiElement(@NotNull Inventory inventory, int slot){
        UUID uuidGuiElement = getGuiElementUUID(inventory, slot);
        return uuidRegistry.get(uuidGuiElement);
    }

    public UUID getGuiElementUUID(@NotNull Inventory inventory, int slot) {
        return posRegistry.getGuiElementUUID(inventory, slot);
    }

    public int getGuiElementSlot(@NotNull Inventory inventory, @NotNull UUID elementUUID) {
        return posRegistry.getSlotUUID(inventory, elementUUID);
    }

    public Inventory getInventory(@NotNull UUID elementUUID) {
        return posRegistry.getInventory(elementUUID);
    }

}
