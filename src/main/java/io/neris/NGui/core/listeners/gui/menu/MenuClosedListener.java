package io.neris.NGui.core.listeners.gui.menu;

import io.neris.NGui.core.gui.menu.services.OpenedMenuRegistry;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;

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

    @EventHandler
    public void playerQuit(PlayerQuitEvent quitEvent){
        Player player = quitEvent.getPlayer();
        UUID playerId = player.getUniqueId();
        menuRegistry.unregister(playerId);
    }

}
