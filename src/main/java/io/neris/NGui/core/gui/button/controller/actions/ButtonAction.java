package io.neris.NGui.core.gui.button.controller.actions;

import io.neris.NGui.core.gui.button.ClickEventContext;

public interface ButtonAction {

    String key();
    String value();

    void execute(ClickEventContext clickContext);
}
