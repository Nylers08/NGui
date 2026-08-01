package io.neris.NGui.core.gui.element.controller.actions.base;

import io.neris.NGui.core.gui.element.GuiElementClickContext;
import io.neris.NGui.core.gui.element.controller.actions.GUIElementAction;
import org.bukkit.event.Cancellable;
import org.bukkit.event.inventory.InventoryClickEvent;

public class CancelPutAction implements GUIElementAction {

    @Override
    public void execute(GuiElementClickContext clickContext) {
        if(clickContext.getEvent() instanceof Cancellable cancellable){
            cancellable.setCancelled(true);
        }
    }
}
