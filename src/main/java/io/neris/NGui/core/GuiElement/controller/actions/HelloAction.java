package io.neris.NGui.core.GuiElement.controller.actions;

import io.neris.NGui.core.GuiElement.GuiElementClickContext;
import io.neris.NGui.core.GuiElement.controller.GUIElementAction;

public class HelloAction implements GUIElementAction {

    @Override
    public void execute(GuiElementClickContext clickContext) {
        clickContext.getEvent().getWhoClicked().sendMessage("Hello, World!");
    }
}
