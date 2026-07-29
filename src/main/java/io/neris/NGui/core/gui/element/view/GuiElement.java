package io.neris.NGui.core.gui.element.view;

import io.neris.NGui.core.gui.element.controller.GuiElementRenderContext;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public interface GuiElement {

    void click(InventoryClickEvent event);
    void render(GuiElementRenderContext context);
    UUID uuid();
    ItemStack getItem();

}
