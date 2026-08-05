package io.neris.NGui.core.gui.element.services.slotBinding;

import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GuiElementInventoryIndex {

    private final Map<Inventory, GuiElementSlotIndex> registry = new HashMap<>();


    public void register(@NotNull GuiElementPosition position){
        Inventory inventory = position.getInventory();
        int slot = position.getSlot();
        UUID uuid = position.getUuid();

        addMenuIfNotExist(inventory);
        registry.get(inventory).register(slot, uuid);
    }

    public void unregister(@NotNull Inventory inventory, int slot){
        if(!registry.containsKey(inventory)){
            return;
        }

        removeFromUuidMenuMap(inventory, slot);
        getSlotsRegistry(inventory).unregister(slot);
        clearMenuIfNotExistsValues(inventory);
    }

    public void unregister(@NotNull Inventory inventory, @NotNull UUID elementUuid){
        if(!registry.containsKey(inventory)){
            return;
        }
        clearMenuIfNotExistsValues(inventory);
    }


    public GuiElementSlotIndex getSlotsRegistry(@NotNull Inventory inventory){
        return registry.get(inventory);
    }

    public UUID getGuiElementUUID(@NotNull Inventory inventory, int slot) {
        GuiElementSlotIndex slotsRegistry = getSlotsRegistry(inventory);
        if(slotsRegistry == null){
            return null;
        }
        return slotsRegistry.getUUID(slot);
    }

    public int getSize(){
        return registry.size();
    }


    public boolean containsInventory(Inventory inventory){
        return registry.containsKey(inventory);
    }


    private void clearMenuIfNotExistsValues(Inventory inventory){
        if(getSlotsRegistry(inventory).getSize() == 0){
            registry.remove(inventory);
        }

    }

    private void removeFromUuidMenuMap(Inventory inventory, int slot){
        UUID elementUUID = getGuiElementUUID(inventory, slot);
    }

    private void addMenuIfNotExist(@NotNull Inventory inventory){
        if(!registry.containsKey(inventory))
            registry.put(inventory, new GuiElementSlotIndex());
    }

}
