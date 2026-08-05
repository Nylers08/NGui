package io.neris.NGui.core.gui.element.controller.actions.nbt;

import io.neris.NGui.core.gui.element.controller.actions.NbtAction;
import io.neris.NGui.core.gui.element.controller.actions.NbtActionKeys;
import io.neris.NGui.core.gui.element.controller.actions.base.CancelPutAction;

public class CancelPutNbtAction extends CancelPutAction implements NbtAction {

    @Override
    public String key() {
        return NbtActionKeys.CANCEL_PUT;
    }

    @Override
    public String value() {
        return "true";
    }
}
