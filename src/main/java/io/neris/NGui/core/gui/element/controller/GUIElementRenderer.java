package io.neris.NGui.core.gui.element.controller;

import org.bukkit.inventory.ItemStack;

public interface GUIElementRenderer {

    ItemStack render(GuiElementRenderContext context);
}
