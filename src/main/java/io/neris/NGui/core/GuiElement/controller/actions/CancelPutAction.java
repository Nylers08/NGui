package io.neris.NGui.core.GuiElement.controller.actions;

import io.neris.NGui.core.GuiElement.GuiElementClickContext;
import io.neris.NGui.core.GuiElement.controller.GUIElementAction;

public class CancelPutAction implements GUIElementAction {

    @Override
    public void execute(GuiElementClickContext clickContext) {
        clickContext.getEvent().setCancelled(true);
    }
}
