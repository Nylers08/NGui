package io.neris.NGui.core.gui.element.services;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class GuiElementSlotRegistry {

    private final BiMap<Integer, UUID> slots = HashBiMap.create();

    public GuiElementSlotRegistry(int slot, @NotNull UUID uuid){
        register(slot, uuid);
    }

    public GuiElementSlotRegistry(){

    }


    public void register(int slot, @NotNull UUID uuid){
        slots.put(slot, uuid);
    }

    public void unregister(int slot){
        slots.remove(slot);
    }

    public void unregister(@NotNull UUID uuid){
        slots.inverse().remove(uuid);
    }


    public UUID getUUID(int slot){
        return slots.get(slot);
    }

    public int getSlot(@NotNull UUID uuid){
        return slots.inverse().get(uuid);
    }

    public int getSize(){
        return slots.size();
    }


    private boolean containsSlot(int slot){
        return slots.containsKey(slot);
    }

    public boolean containsUUID(UUID uuid){
        return slots.containsValue(uuid);
    }
}
