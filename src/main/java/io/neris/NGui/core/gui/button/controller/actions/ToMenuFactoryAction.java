package io.neris.NGui.core.gui.button.controller.actions;

import io.neris.NGui.core.gui.button.ClickEventContext;
import io.neris.NGui.core.gui.menu.services.MenuFactory;
import io.neris.NGui.core.gui.menu.services.PlayerMenuOpener;
import io.neris.NGui.core.services.StaticMenuServiceController;

import static io.neris.NGui.core.gui.button.controller.actions.NbtActionKeys.GO_TO_MENU_FACTORY;

public class ToMenuFactoryAction implements ButtonAction {

    private final PlayerMenuOpener menuOpener;
    private final MenuFactory<ClickEventContext> menuFactory;

    public ToMenuFactoryAction(PlayerMenuOpener menuOpener, MenuFactory<ClickEventContext> menuFactory) {
        this.menuOpener = menuOpener;
        this.menuFactory = menuFactory;
        StaticMenuServiceController.getServiceController().getNbtActionRegistry().register(this);
    }

    @Override
    public String key() {
        return GO_TO_MENU_FACTORY;
    }

    @Override
    public String value() {
        return "true";
    }

    @Override
    public void execute(ClickEventContext clickContext) {
        menuOpener.open(clickContext.whoClicked, menuFactory.create(clickContext));
    }
}
