package io.neris.NGui.core.gui.element;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class GuiElementPosition {

    @Getter @Setter private Inventory inventory;
    @Getter @Setter private int slot;
    @Getter @Setter private UUID uuid;

    public GuiElementPosition(){

    }

    public GuiElementPosition(Inventory inventory, int slot, UUID uuid){
        this.inventory = inventory;
        this.slot = slot;
        this.uuid = uuid;
    }

}
