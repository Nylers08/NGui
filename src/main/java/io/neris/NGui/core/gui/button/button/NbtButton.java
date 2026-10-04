package io.neris.NGui.core.gui.button.button;

import io.neris.NGui.core.gui.button.controller.ButtonController;
import io.neris.NGui.core.gui.button.controller.actions.ButtonAction;
import io.neris.NGui.core.gui.button.controller.renderers.ButtonRenderContext;
import io.neris.NGui.core.services.nbtTagger.NBTTagger;

import java.util.ArrayList;
import java.util.List;

public class NbtButton extends AbstractButton {

    protected final NBTTagger nbtTagger;

    public NbtButton(ButtonController controller, ButtonRenderContext renderContext, NBTTagger nbtTagger) {
        this.nbtTagger = nbtTagger;
        super(renderContext, controller);
    }

    @Override
    public void render(ButtonRenderContext context){
        itemStack = controller.render(context);
        addNbtKeyValueToItemStack();
    }


    private void addNbtKeyValueToItemStack(){
        List<ButtonAction> buttonActions = getNbtActions();
        for (ButtonAction buttonAction : buttonActions){
            nbtTagger.addStringNBT(itemStack, buttonAction.key(), buttonAction.value());
        }
    }

    private List<ButtonAction> getNbtActions(){
        List<ButtonAction> buttonActions = new ArrayList<>();

        List<ButtonAction> elementActions = controller.getActionList();
        for (ButtonAction elementAction : elementActions){
            if(elementAction instanceof ButtonAction buttonAction){
                buttonActions.add(buttonAction);
            }
        }

        return buttonActions;
    }
}
