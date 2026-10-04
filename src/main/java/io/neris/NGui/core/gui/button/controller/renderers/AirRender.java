package io.neris.NGui.core.gui.button.controller.renderers;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class AirRender implements ButtonRenderer {
    @Override
    public ItemStack render(ButtonRenderContext context) {
        return new ItemStack(Material.AIR);
    }
}
