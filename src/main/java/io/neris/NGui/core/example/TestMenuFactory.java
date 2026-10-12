package io.neris.NGui.core.example;

import io.neris.NGui.core.gui.button.button.Button;
import io.neris.NGui.core.gui.button.controller.renderers.ButtonRenderContext;
import io.neris.NGui.core.gui.menu.BaseMenu;
import io.neris.NGui.core.gui.menu.Menu;
import io.neris.NGui.core.gui.menu.services.MenuFactory;
import io.neris.NGui.core.services.MenuServiceController;
import io.neris.NGui.core.services.StaticMenuServiceController;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;

import java.util.UUID;

public class TestMenuFactory implements MenuFactory<UUID> {

    @Override
    public Menu create(UUID context) {
        String  playerName = Bukkit.getPlayer(context).getName();
        TestButton testButton = new TestButton(context);
        Menu menu = new BaseMenu(27, Component.text(playerName));
        menu.setItem(testButton.getItem(), 0,1,2,3);

        MenuServiceController serviceController = StaticMenuServiceController.getServiceController();
        Button testBtnToMenu = new TestOpenMenuFactoryButton(new ButtonRenderContext(context), serviceController.getPlayerMenuOpener());
        menu.setItem(testBtnToMenu.getItem(), 18);

        return menu;
    }
}
