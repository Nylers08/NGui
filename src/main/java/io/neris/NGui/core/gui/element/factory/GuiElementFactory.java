package io.neris.NGui.core.gui.element.factory;

import io.neris.NGui.core.gui.element.view.GuiElement;

public interface GuiElementFactory<T> {

    GuiElement create(T context);
}
