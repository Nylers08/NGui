package io.neris.NGui.core.gui.menu.services;

import io.neris.NGui.core.gui.menu.Menu;

public interface MenuCloser<T> {

    void close(Menu menu);
    void close(T whom);
    void closeAll();
}
