package io.neris.NGui.core.gui.button.factory;

import io.neris.NGui.core.gui.button.button.Button;
import io.neris.NGui.core.gui.button.button.StaticNbtButton;
import io.neris.NGui.core.gui.button.controller.ButtonController;
import io.neris.NGui.core.gui.button.controller.actions.ButtonAction;
import io.neris.NGui.core.gui.button.controller.actions.ToMenuAction;
import io.neris.NGui.core.gui.menu.services.PlayerMenuOpener;

import java.util.List;

public class ButtonToMenuFactory implements ButtonFactory<ButtonToMenuContext> {

    private final PlayerMenuOpener menuOpener;

    public ButtonToMenuFactory(PlayerMenuOpener menuOpener) {
        this.menuOpener = menuOpener;
    }

    @Override
    public Button create(ButtonToMenuContext context) {
        ButtonAction openMenuAction = new ToMenuAction(menuOpener, context.menu());
        List<ButtonAction> actions = List.of(openMenuAction);
        ButtonController controller = new ButtonController( context.renderer(), actions);
        return new StaticNbtButton(controller, context.renderContext());
    }
}
