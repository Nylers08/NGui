package io.neris.NGui.core.gui.element.controller.actions.base;

import io.neris.NGui.core.gui.element.EventContext;
import io.neris.NGui.core.gui.element.controller.actions.GUIElementAction;
import org.bukkit.event.Cancellable;

public class CancelPutAction implements GUIElementAction {

    @Override
    public void execute(EventContext clickContext) {
        if(clickContext.getEvent() instanceof Cancellable cancellable){
            cancellable.setCancelled(true);
        }
    }
}
