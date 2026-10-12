package io.neris.NGui.core.example;

import io.neris.NGui.core.gui.button.button.OpenMenuFactoryButton;
import io.neris.NGui.core.gui.button.controller.ButtonController;
import io.neris.NGui.core.gui.button.controller.SimpleBtnController;
import io.neris.NGui.core.gui.button.controller.actions.CancelPutAction;
import io.neris.NGui.core.gui.button.controller.renderers.ButtonRenderContext;
import io.neris.NGui.core.gui.menu.services.PlayerMenuOpener;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;

public class TestOpenMenuFactoryButton extends OpenMenuFactoryButton {

    public TestOpenMenuFactoryButton(ButtonRenderContext renderContext, PlayerMenuOpener menuOpener) {
        ButtonController btnController = new SimpleBtnController(Material.ARROW, 1, Component.text("ToMenu"));
        btnController.addAction(new CancelPutAction());
        super(btnController, renderContext, menuOpener, new TestMenuFactoryWithClickContext());
    }
}
