package io.neris.NGui.core.gui.element.controller.renderers;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class AirRender implements GUIElementRenderer {
    @Override
    public ItemStack render(GuiElementRenderContext context) {
        return new ItemStack(Material.AIR);
    }
}
