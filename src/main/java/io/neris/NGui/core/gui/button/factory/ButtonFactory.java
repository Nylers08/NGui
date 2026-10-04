package io.neris.NGui.core.gui.button.factory;

import io.neris.NGui.core.gui.button.button.Button;

public interface ButtonFactory<T> {

    Button create(T context);
}
