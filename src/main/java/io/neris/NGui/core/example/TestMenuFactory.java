package io.neris.NGui.core.example;

import io.neris.NGui.core.gui.menu.BaseMenu;
import io.neris.NGui.core.gui.menu.Menu;
import io.neris.NGui.core.gui.menu.services.MenuFactory;
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
        return menu;
    }
}
