package io.neris.NGui.core.gui.element.factory;

import io.neris.NGui.core.gui.element.element.GuiElement;

public interface GuiElementFactory<T> {

    GuiElement create(T context);
}
