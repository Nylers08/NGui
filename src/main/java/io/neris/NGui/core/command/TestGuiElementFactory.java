package io.neris.NGui.core.command;

import io.neris.NGui.core.gui.element.controller.renderers.GUIElementRenderer;
import io.neris.NGui.core.gui.element.controller.GuiElementController;
import io.neris.NGui.core.gui.element.controller.renderers.GuiElementRenderContext;
import io.neris.NGui.core.gui.element.controller.actions.base.CancelPutAction;
import io.neris.NGui.core.gui.element.controller.actions.base.HelloAction;
import io.neris.NGui.core.gui.element.controller.renderers.AirRender;
import io.neris.NGui.core.gui.element.factory.GuiElementFactory;
import io.neris.NGui.core.gui.element.view.BaseGuiElement;
import io.neris.NGui.core.gui.element.view.GuiElement;

import java.util.UUID;

public class TestGuiElementFactory implements GuiElementFactory<UUID> {

    @Override
    public GuiElement create(UUID playerUUID) {
        return new BaseGuiElement(buildRenderContext(playerUUID), buildController());
    }

    private GuiElementController buildController(){
        GUIElementRenderer renderer = new AirRender();
        GuiElementController controller = new GuiElementController(renderer);
        controller.addActions(new CancelPutAction(), new HelloAction());
        return controller;
    }

    private GuiElementRenderContext buildRenderContext(UUID playerUUID){
        return new GuiElementRenderContext(playerUUID);
    }
}
