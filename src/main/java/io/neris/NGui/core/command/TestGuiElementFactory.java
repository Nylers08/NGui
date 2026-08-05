package io.neris.NGui.core.command;

import io.neris.NGui.core.gui.element.controller.actions.nbt.CancelPutNbtAction;
import io.neris.NGui.core.gui.element.controller.actions.nbt.MsgPlayerNbtAction;
import io.neris.NGui.core.gui.element.controller.renderers.GUIElementRenderer;
import io.neris.NGui.core.gui.element.controller.GuiElementController;
import io.neris.NGui.core.gui.element.controller.renderers.GuiElementRenderContext;
import io.neris.NGui.core.gui.element.controller.actions.base.CancelPutAction;
import io.neris.NGui.core.gui.element.controller.actions.base.HelloAction;
import io.neris.NGui.core.gui.element.controller.renderers.AirRender;
import io.neris.NGui.core.gui.element.controller.renderers.PlayerNicknameRenderer;
import io.neris.NGui.core.gui.element.element.NbtGuiElement;
import io.neris.NGui.core.gui.element.factory.GuiElementFactory;
import io.neris.NGui.core.gui.element.element.BaseGuiElement;
import io.neris.NGui.core.gui.element.element.GuiElement;
import io.neris.NGui.core.services.nbtTagger.NBTTagger;

import java.util.UUID;

public class TestGuiElementFactory implements GuiElementFactory<UUID> {

    private final NBTTagger nbtTagger;

    public TestGuiElementFactory(NBTTagger nbtTagger) {
        this.nbtTagger = nbtTagger;
    }

    @Override
    public GuiElement create(UUID playerUUID) {
        return new NbtGuiElement(buildRenderContext(playerUUID), buildController(), nbtTagger);
    }

    private GuiElementController buildController(){
        GUIElementRenderer renderer = new PlayerNicknameRenderer();
        GuiElementController controller = new GuiElementController(renderer);

        controller.addActions(new CancelPutNbtAction(),
                new MsgPlayerNbtAction(nbtTagger, "Ты лох"));
        return controller;
    }

    private GuiElementRenderContext buildRenderContext(UUID playerUUID){
        return new GuiElementRenderContext(playerUUID);
    }
}
