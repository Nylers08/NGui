package io.neris.NGui.core.gui.menu.services;

import io.neris.NGui.core.gui.menu.Menu;

public interface MenuOpener<T> {

    void open(T whom, Menu menu);
}
