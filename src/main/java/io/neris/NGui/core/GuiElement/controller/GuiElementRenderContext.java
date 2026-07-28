package io.neris.NGui.core.guiElement.controller;

import lombok.Getter;

import java.util.UUID;

public class GuiElementRenderContext {

    @Getter private final UUID playerUUID;

    public GuiElementRenderContext(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }
}
