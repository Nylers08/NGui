package io.neris.NGui.core.gui.element.controller;

import io.neris.NGui.core.gui.element.EventContext;
import io.neris.NGui.core.gui.element.controller.actions.GUIElementAction;
import io.neris.NGui.core.gui.element.controller.renderers.GUIElementRenderer;
import io.neris.NGui.core.gui.element.controller.renderers.GuiElementRenderContext;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GuiElementController {

    @Getter private final List<GUIElementAction> actionList;
    @Getter private final GUIElementRenderer renderer;

    public GuiElementController(List<GUIElementAction> actionList, GUIElementRenderer renderer) {
        this.actionList = actionList;
        this.renderer = renderer;
    }

    public GuiElementController(GUIElementRenderer renderer) {
        this.actionList = new ArrayList<>();
        this.renderer = renderer;
    }


    public void execute(EventContext clickContext){
        actionList.forEach(action -> action.execute(clickContext));
    }

    public ItemStack render(GuiElementRenderContext context){
        return renderer.render(context);
    }


    public void addActions(GUIElementAction... actions){
        actionList.addAll(Arrays.asList(actions));
    }

    public void removeActions(GUIElementAction... actions){
        actionList.removeAll(Arrays.asList(actions));
    }
}
