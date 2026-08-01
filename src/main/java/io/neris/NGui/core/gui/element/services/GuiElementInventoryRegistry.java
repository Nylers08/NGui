package io.neris.NGui.core.gui.element.services;

import io.neris.NGui.core.gui.element.GuiElementPosition;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GuiElementInventoryRegistry {

    private final Map<Inventory, GuiElementSlotRegistry> registry = new HashMap<>();
    private final Map<UUID, Inventory> uuidInventoryMap = new HashMap<>();


    public void register(@NotNull GuiElementPosition position){
        Inventory inventory = position.getInventory();
        int slot = position.getSlot();
        UUID uuid = position.getUuid();

        addMenuIfNotExist(inventory);
        registry.get(inventory).register(slot, uuid);
        uuidInventoryMap.put(uuid, inventory);
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

        uuidInventoryMap.remove(elementUuid);

        getSlotsRegistry(inventory).unregister(elementUuid);
        clearMenuIfNotExistsValues(inventory);
    }


    public GuiElementSlotRegistry getSlotsRegistry(@NotNull Inventory inventory){
        return registry.get(inventory);
    }

    public Inventory getInventory(@NotNull UUID elementUuid){
        return uuidInventoryMap.get(elementUuid);
    }

    public UUID getGuiElementUUID(@NotNull Inventory inventory, int slot) {
        GuiElementSlotRegistry slotsRegistry = getSlotsRegistry(inventory);
        if(slotsRegistry == null){
            return null;
        }
        return slotsRegistry.getUUID(slot);
    }

    public int getSlotUUID(@NotNull Inventory inventory, @NotNull UUID elementUuid){
        return getSlotsRegistry(inventory).getSlot(elementUuid);
    }

    public int getSize(){
        return registry.size();
    }


    public boolean containsInventory(Inventory inventoryu){
        return registry.containsKey(inventoryu);
    }


    private void clearMenuIfNotExistsValues(Inventory inventory){
        if(getSlotsRegistry(inventory).getSize() == 0){
            registry.remove(inventory);
        }

    }

    private void removeFromUuidMenuMap(Inventory inventory, int slot){
        UUID elementUUID = getGuiElementUUID(inventory, slot);
        uuidInventoryMap.remove(elementUUID);
    }

    private void addMenuIfNotExist(@NotNull Inventory inventory){
        if(!registry.containsKey(inventory))
            registry.put(inventory, new GuiElementSlotRegistry());
    }

}
