package io.neris.NGui.core.gui.element.controller.actions;

import io.neris.NGui.core.gui.element.GuiElementClickContext;
import io.neris.NGui.core.gui.element.controller.GUIElementAction;

public class HelloAction implements GUIElementAction {

    @Override
    public void execute(GuiElementClickContext clickContext) {
        clickContext.getEvent().getWhoClicked().sendMessage("Hello, World!");
    }
}
