package io.neris.NGui.core.guiElement.controller;

import io.neris.NGui.core.guiElement.GuiElementClickContext;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GuiElementController {

    private final List<GUIElementAction> actionList;
    private final GUIElementRenderer renderer;

    public GuiElementController(List<GUIElementAction> actionList, GUIElementRenderer renderer) {
        this.actionList = actionList;
        this.renderer = renderer;
    }

    public GuiElementController(GUIElementRenderer renderer) {
        this.actionList = new ArrayList<>();
        this.renderer = renderer;
    }


    public void execute(GuiElementClickContext clickContext){
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
