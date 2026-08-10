package io.neris.NGui.core.listeners.gui.menu;

import io.neris.NGui.core.gui.menu.services.OpenedMenuRegistry;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.UUID;

public class MenuClosedListener implements Listener {

    private final OpenedMenuRegistry menuRegistry;

    public MenuClosedListener(OpenedMenuRegistry menuRegistry) {
        this.menuRegistry = menuRegistry;
    }

    @EventHandler
    public void invClosed(InventoryCloseEvent event){
        UUID playerId = event.getPlayer().getUniqueId();
        menuRegistry.unregister(playerId);
    }
}
