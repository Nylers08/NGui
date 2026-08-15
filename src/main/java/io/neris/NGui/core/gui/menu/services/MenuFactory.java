package io.neris.NGui.core.gui.menu.services;

import io.neris.NGui.core.gui.menu.Menu;

public interface MenuFactory<T> {

    Menu create(T context);
}
