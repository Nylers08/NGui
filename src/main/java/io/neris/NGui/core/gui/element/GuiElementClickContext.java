package io.neris.NGui.core.gui.element;

import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.EventListener;

public class GuiElementClickContext {

    @Getter private final Event event;

    public GuiElementClickContext(Event event) {
        this.event = event;
    }
}
