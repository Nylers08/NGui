package io.neris.NGui.core.gui.menu.services;

import io.neris.NGui.core.gui.menu.Menu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.UUID;

public class PlayerMenuCloser implements MenuCloser<UUID>{

    protected final OpenedMenuRegistry menuRegistry;

    public PlayerMenuCloser(OpenedMenuRegistry menuRegistry) {
        this.menuRegistry = menuRegistry;
    }

    @Override
    public void close(Menu menu) {
        Set<UUID> menuViewers = menuRegistry.getViewers(menu);
        if(menuViewers == null || menuViewers.isEmpty()){
            return;
        }

        for (UUID viewerId : menuViewers){
            Player player = Bukkit.getPlayer(viewerId);
            if(player == null){
                continue;
            }
            player.closeInventory();
        }

        menuRegistry.unregister(menu);
    }

    @Override
    public void close(UUID playerId) {
        menuRegistry.unregister(playerId);

        Player player = Bukkit.getPlayer(playerId);
        if(player == null){
            return;
        }
        player.closeInventory();
    }

    @Override
    public void closeAll() {
        Set<UUID> allViewers = menuRegistry.getAllViewers();
        if(allViewers.isEmpty()){
            return;
        }

        Set<UUID> viewersCopy = Set.copyOf(allViewers);
        for (UUID viewer : viewersCopy){
            close(viewer);
        }
    }
}
