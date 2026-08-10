package io.neris.NGui.core.gui.menu.services;

import io.neris.NGui.core.gui.menu.Menu;
import org.bukkit.entity.HumanEntity;

public class PlayerMenuOpener implements MenuOpener<HumanEntity>{

    protected final OpenedMenuRegistry menuRegistry;

    public PlayerMenuOpener(OpenedMenuRegistry menuRegistry) {
        this.menuRegistry = menuRegistry;
    }

    @Override
    public void open(HumanEntity whom, Menu menu) {
        menuRegistry.register(menu, whom.getUniqueId());
        whom.openInventory(menu.getInventory());
    }
}
