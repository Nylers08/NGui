package io.neris.NGui.core.gui.button.controller.renderers;

import org.bukkit.inventory.ItemStack;

public interface ButtonRenderer {

    ItemStack render(ButtonRenderContext context);
}
