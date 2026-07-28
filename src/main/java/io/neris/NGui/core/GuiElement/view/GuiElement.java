package io.neris.NGui.core.GuiElement.view;

import io.neris.NGui.core.GuiElement.controller.GuiElementRenderContext;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public interface GuiElement {

    void click(InventoryClickEvent event);
    void render(GuiElementRenderContext context);
    UUID uuid();
    ItemStack getItem();

}
