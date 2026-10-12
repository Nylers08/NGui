package io.neris.NGui.core.example;

import io.neris.NGui.core.gui.button.ClickEventContext;
import io.neris.NGui.core.gui.button.button.Button;
import io.neris.NGui.core.gui.menu.BaseMenu;
import io.neris.NGui.core.gui.menu.Menu;
import io.neris.NGui.core.gui.menu.services.MenuFactory;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public class TestMenuFactoryWithClickContext implements MenuFactory<ClickEventContext> {

    @Override
    public String getMenuName() {
        return "TestMenuFactoryWithClickContext";
    }

    @Override
    public Menu create(ClickEventContext context) {
        Player player = (Player) context.whoClicked;
        Button testButton = new TestButton(player.getUniqueId());

        Menu menu = new BaseMenu(27, Component.text("TestMenuFactoryWithClickContext"));
        menu.setItem(testButton.getItem(), 18,20,21);

        return menu;
    }
}
