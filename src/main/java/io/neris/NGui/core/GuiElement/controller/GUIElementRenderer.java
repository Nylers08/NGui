package io.neris.NGui.core.GuiElement.controller;

import org.bukkit.inventory.ItemStack;

public interface GUIElementRenderer<T> {

    ItemStack render(T context);
}
