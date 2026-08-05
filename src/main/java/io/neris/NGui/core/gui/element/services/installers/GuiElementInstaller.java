package io.neris.NGui.core.gui.element.services.installers;

import io.neris.NGui.core.gui.element.element.GuiElement;
import io.neris.NGui.core.gui.menu.Menu;
import org.bukkit.inventory.Inventory;

public interface GuiElementInstaller {

    void install(Menu menu, GuiElement element, int... slots);
    void install(Inventory inventory, GuiElement element, int... slots);
}
