package io.neris.NGui.core.GuiElement.factory;

import io.neris.NGui.core.GuiElement.view.GuiElement;

public interface GuiElementFactory<T> {

    GuiElement create(T context);
}
