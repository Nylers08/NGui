package io.neris.NGui.core.guiElement.services;

import io.neris.NGui.core.guiElement.controller.GuiElementController;
import io.neris.NGui.core.guiElement.view.BaseGuiElement;
import io.neris.NGui.core.services.nbtTagger.GuiElementTagger;
import org.jetbrains.annotations.NotNull;

public class BaseGuiElementBuilder {
    private GuiElementController controller;
    private GuiElementTagger nbtTagger;

    public BaseGuiElementBuilder controller(@NotNull GuiElementController controller) {
        this.controller = controller;
        return this;
    }

    public BaseGuiElementBuilder nbtTagger(@NotNull GuiElementTagger nbtTagger) {
        this.nbtTagger = nbtTagger;
        return this;
    }

    public BaseGuiElement build() {
        if (controller == null) {
            throw new IllegalStateException("GuiElementController must be set for BaseGuiElement.");
        }
        if (nbtTagger == null) {
            throw new IllegalStateException("GuiElementTagger must be set for BaseGuiElement.");
        }
        // Используем приватный конструктор BaseGuiElement
        return new BaseGuiElement(controller, nbtTagger);
    }
}
