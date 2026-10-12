package io.neris.NGui.core.gui.menu.services;

import io.neris.NGui.core.gui.menu.Menu;

public interface MenuFactory<T> {

    String getMenuName();
    Menu create(T context);
}
