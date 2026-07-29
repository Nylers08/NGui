package io.neris.NGui.core.command;

import io.neris.NGui.core.GuiElement.controller.GUIElementRenderer;
import io.neris.NGui.core.GuiElement.controller.GuiElementController;
import io.neris.NGui.core.GuiElement.controller.GuiElementRenderContext;
import io.neris.NGui.core.GuiElement.controller.actions.CancelPutAction;
import io.neris.NGui.core.GuiElement.controller.actions.HelloAction;
import io.neris.NGui.core.GuiElement.controller.renderers.PlayerNicknameRenderer;
import io.neris.NGui.core.GuiElement.factory.GuiElementFactory;
import io.neris.NGui.core.GuiElement.services.GuiElementRegistry;
import io.neris.NGui.core.GuiElement.view.BaseGuiElement;
import io.neris.NGui.core.GuiElement.view.GuiElement;
import io.neris.NGui.core.services.nbtTagger.GuiElementTagger;

import java.util.UUID;

public class TestGuiElementFactory implements GuiElementFactory<UUID> {

    private final GuiElementTagger elementTagger;
    private final GuiElementRegistry elementRegistry;

    public TestGuiElementFactory(GuiElementTagger elementTagger, GuiElementRegistry elementRegistry) {
        this.elementTagger = elementTagger;
        this.elementRegistry = elementRegistry;
    }

    @Override
    public GuiElement create(UUID playerUUID) {
        return new BaseGuiElement(buildRenderContext(playerUUID), buildController(), elementTagger, elementRegistry);
    }

    private GuiElementController buildController(){
        GUIElementRenderer renderer = new PlayerNicknameRenderer();
        GuiElementController controller = new GuiElementController(renderer);
        controller.addActions(new CancelPutAction(), new HelloAction());
        return controller;
    }

    private GuiElementRenderContext buildRenderContext(UUID playerUUID){
        return new GuiElementRenderContext(playerUUID);
    }
}
