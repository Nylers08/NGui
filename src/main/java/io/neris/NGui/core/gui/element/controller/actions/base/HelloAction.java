package io.neris.NGui.core.gui.element.controller.actions.base;

import io.neris.NGui.core.gui.element.GuiElementClickContext;
import io.neris.NGui.core.gui.element.controller.actions.GUIElementAction;
import org.bukkit.event.inventory.InventoryClickEvent;

public class HelloAction implements GUIElementAction {

    @Override
    public void execute(GuiElementClickContext clickContext) {
        if(clickContext.getEvent() instanceof InventoryClickEvent clickEvent){
            clickEvent.getWhoClicked().sendMessage("Hello, World!");
        }

    }
}
