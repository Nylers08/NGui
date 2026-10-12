package io.neris.NGui.core.gui.button.button;

import io.neris.NGui.core.gui.button.controller.ButtonController;
import io.neris.NGui.core.gui.button.controller.actions.ButtonAction;
import io.neris.NGui.core.gui.button.controller.actions.ToMenuAction;
import io.neris.NGui.core.gui.button.controller.renderers.ButtonRenderContext;
import io.neris.NGui.core.gui.menu.Menu;
import io.neris.NGui.core.gui.menu.services.PlayerMenuOpener;

public abstract class OpenMenuButton extends StaticNbtButton{

    public OpenMenuButton(ButtonController controller, ButtonRenderContext renderContext, PlayerMenuOpener menuOpener, Menu menu) {
        super(controller, renderContext);
        ButtonAction toMenuAction = new ToMenuAction(menuOpener, menu);
        this.controller.addActions(toMenuAction);
        updateNbts();
    }
}
