package io.neris.NGui.core.gui.menu.services;

import io.neris.NGui.core.gui.menu.Menu;

import java.util.*;

public class OpenedMenuRegistry {

    private final Map<Menu, Set<UUID>> openedMenu = new HashMap<>();
    private final Map<UUID, Menu> viewers = new HashMap<>();


    public void register(Menu menu, UUID playerId) {
        openedMenu
                .computeIfAbsent(menu, k -> new HashSet<>())
                .add(playerId);

        viewers.put(playerId, menu);
    }

    public void unregister(Menu menu){
        Set<UUID> playersIds = openedMenu.get(menu);
        for (UUID id : playersIds){
            viewers.remove(id);
        }

        openedMenu.remove(menu);
    }

    public void unregister(UUID playerId){
        Menu menu = viewers.get(playerId);
        viewers.remove(playerId);

        Set<UUID> menuViewers = openedMenu.get(menu);
        menuViewers.remove(playerId);
        if(menuViewers.isEmpty()){
            openedMenu.remove(menu);
        }
    }


    public Set<UUID> getViewers(Menu menu) {
        return openedMenu.getOrDefault(menu, Set.of());
    }

    public Set<UUID> getAllViewers(){
        return viewers.keySet();
    }

    public Menu getMenu(UUID playerId) {
        return viewers.get(playerId);
    }


    public int countViewers(){
        return viewers.size();
    }
}
