package io.neris.NGui.core.guiElement.controller;

import org.bukkit.inventory.ItemStack;

public interface GUIElementRenderer {

    ItemStack render(GuiElementRenderContext context);
}
