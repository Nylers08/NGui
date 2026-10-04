package io.neris.NGui.core.gui.button.controller;

import io.neris.NGui.core.gui.button.ClickEventContext;
import io.neris.NGui.core.gui.button.controller.actions.ButtonAction;
import io.neris.NGui.core.gui.button.controller.renderers.ButtonRenderer;
import io.neris.NGui.core.gui.button.controller.renderers.ButtonRenderContext;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ButtonController {

    @Getter private final List<ButtonAction> actionList;
    @Getter private final ButtonRenderer renderer;

    public ButtonController(ButtonRenderer renderer, List<ButtonAction> actionList) {
        this.actionList = actionList;
        this.renderer = renderer;
    }

    public ButtonController(ButtonRenderer renderer) {
        this.actionList = new ArrayList<>();
        this.renderer = renderer;
    }


    public void execute(ClickEventContext clickContext){
        actionList.forEach(action -> action.execute(clickContext));
    }

    public ItemStack render(ButtonRenderContext context){
        return renderer.render(context);
    }


    public void addActions(ButtonAction... actions){
        actionList.addAll(Arrays.asList(actions));
    }

    public void removeActions(ButtonAction... actions){
        actionList.removeAll(Arrays.asList(actions));
    }
}
