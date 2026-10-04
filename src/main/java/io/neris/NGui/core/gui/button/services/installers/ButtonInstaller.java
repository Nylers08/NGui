package io.neris.NGui.core.gui.button.services.installers;

import io.neris.NGui.core.gui.button.button.Button;
import io.neris.NGui.core.gui.menu.Menu;
import org.bukkit.inventory.Inventory;

public interface ButtonInstaller {

    void install(Menu menu, Button element, int... slots);
    void install(Inventory inventory, Button element, int... slots);
}
