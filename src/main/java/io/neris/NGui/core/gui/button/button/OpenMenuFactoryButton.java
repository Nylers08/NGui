package io.neris.NGui.core.gui.button.button;

import io.neris.NGui.core.gui.button.ClickEventContext;
import io.neris.NGui.core.gui.button.controller.ButtonController;
import io.neris.NGui.core.gui.button.controller.actions.ButtonAction;
import io.neris.NGui.core.gui.button.controller.actions.ToMenuFactoryAction;
import io.neris.NGui.core.gui.button.controller.renderers.ButtonRenderContext;
import io.neris.NGui.core.gui.menu.services.MenuFactory;
import io.neris.NGui.core.gui.menu.services.PlayerMenuOpener;

public class OpenMenuFactoryButton extends StaticNbtButton{

    public OpenMenuFactoryButton(ButtonController controller, ButtonRenderContext renderContext,
                                 PlayerMenuOpener menuOpener, MenuFactory<ClickEventContext> menuFactory)
    {
        ButtonAction action = new ToMenuFactoryAction(menuOpener, menuFactory);
        controller.addAction(action);
        super(controller, renderContext);

    }
}
