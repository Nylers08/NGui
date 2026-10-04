package io.neris.NGui.core.gui.button.button;

import io.neris.NGui.core.gui.button.controller.ButtonController;
import io.neris.NGui.core.gui.button.controller.renderers.ButtonRenderContext;
import io.neris.NGui.core.utils.gui.action.NBTActionUtils;

public class StaticNbtButton extends NbtButton{

    public StaticNbtButton(ButtonController controller, ButtonRenderContext renderContext){
        super(controller, renderContext, NBTActionUtils.getTagger());
    }
}
