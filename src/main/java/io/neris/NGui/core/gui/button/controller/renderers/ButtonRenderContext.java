package io.neris.NGui.core.gui.button.controller.renderers;

import lombok.Getter;

import java.util.UUID;

public class ButtonRenderContext {

    @Getter private final UUID playerUUID;

    public ButtonRenderContext(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }
}
