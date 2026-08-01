package io.neris.NGui.core.gui.element.controller.actions.nbt;

import io.neris.NGui.core.gui.element.GuiElementClickContext;
import io.neris.NGui.core.gui.element.controller.actions.NbtAction;
import io.neris.NGui.core.gui.element.controller.actions.NbtActionKeys;

public class CancelPutNbtAction implements NbtAction {

    @Override
    public String key() {
        return NbtActionKeys.CANCEL_PUT;
    }

    @Override
    public void execute(GuiElementClickContext clickContext) {

    }
}
