package io.neris.NGui.core.gui.element.element;

import io.neris.NGui.core.gui.element.controller.GuiElementController;
import io.neris.NGui.core.gui.element.controller.actions.GUIElementAction;
import io.neris.NGui.core.gui.element.controller.actions.NbtAction;
import io.neris.NGui.core.gui.element.controller.renderers.GuiElementRenderContext;
import io.neris.NGui.core.services.nbtTagger.NBTTagger;

import java.util.ArrayList;
import java.util.List;

public class NbtGuiElement extends BaseGuiElement{

    protected final NBTTagger nbtTagger;

    public NbtGuiElement(GuiElementRenderContext renderContext, GuiElementController controller, NBTTagger nbtTagger) {
        this.nbtTagger = nbtTagger;
        super(renderContext, controller);
    }

    @Override
    public void render(GuiElementRenderContext context){
        itemStack = controller.render(context);
        addNbtKeyValueToItemStack();
    }


    private void addNbtKeyValueToItemStack(){
        List<NbtAction> nbtActions = getNbtActions();
        for (NbtAction nbtAction : nbtActions){
            nbtTagger.addStringNBT(itemStack, nbtAction.key(), nbtAction.value());
        }
    }

    private List<NbtAction> getNbtActions(){
        List<NbtAction> nbtActions = new ArrayList<>();

        List<GUIElementAction> elementActions = controller.getActionList();
        for (GUIElementAction elementAction : elementActions){
            if(elementAction instanceof NbtAction nbtAction){
                nbtActions.add(nbtAction);
            }
        }

        return nbtActions;
    }
}
