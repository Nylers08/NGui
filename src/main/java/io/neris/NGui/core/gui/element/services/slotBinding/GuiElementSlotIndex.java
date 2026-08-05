package io.neris.NGui.core.gui.element.services.slotBinding;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GuiElementSlotIndex {

    private final Map<Integer, UUID> slots = new HashMap<>();


    public GuiElementSlotIndex(int slot, @NotNull UUID uuid){
        register(slot, uuid);
    }

    public GuiElementSlotIndex(){

    }


    public void register(int slot, @NotNull UUID uuid){
        slots.put(slot, uuid);
    }

    public void unregister(int slot){
        slots.remove(slot);
    }



    public UUID getUUID(int slot){
        return slots.get(slot);
    }


    public int getSize(){
        return slots.size();
    }


    public boolean containsSlot(int slot){
        return slots.containsKey(slot);
    }

    public boolean containsUUID(UUID uuid){
        return slots.containsValue(uuid);
    }
}
