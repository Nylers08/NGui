package io.neris.NGui.core.gui.button.controller.actions;

import io.neris.NGui.core.gui.button.ClickEventContext;
import io.neris.NGui.core.gui.menu.Menu;
import io.neris.NGui.core.gui.menu.services.PlayerMenuOpener;
import io.neris.NGui.core.services.StaticMenuServiceController;

import static io.neris.NGui.core.gui.button.controller.actions.NbtActionKeys.GO_TO_MENU;

public class ToMenuAction implements ButtonAction {

    private final PlayerMenuOpener menuOpener;
    private final Menu menu;

    public ToMenuAction(PlayerMenuOpener menuOpener, Menu menu) {
        this.menuOpener = menuOpener;
        this.menu = menu;
        StaticMenuServiceController.getServiceController().getNbtActionRegistry().register(this);
    }

    @Override
    public String key() {
        return GO_TO_MENU;
    }

    @Override
    public String value() {
        return "true";
    }

    @Override
    public void execute(ClickEventContext clickContext) {
        menuOpener.open(clickContext.whoClicked, menu);
    }
}
