package io.neris.NGui.core.gui.button.button;

import io.neris.NGui.core.gui.button.controller.renderers.ButtonRenderContext;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public interface Button {

    void render(ButtonRenderContext context);
    UUID uuid();
    ItemStack getItem();

}
