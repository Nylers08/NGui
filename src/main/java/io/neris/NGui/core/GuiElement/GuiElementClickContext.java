package io.neris.NGui.core.guiElement;

import lombok.Getter;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GuiElementClickContext {

    @Getter private final InventoryClickEvent event;

    public GuiElementClickContext(InventoryClickEvent event) {
        this.event = event;
    }
}
