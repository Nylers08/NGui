package io.neris.NGui.core.gui.element.element;

import io.neris.NGui.core.gui.element.controller.actions.GUIElementAction;
import io.neris.NGui.core.gui.element.controller.renderers.GuiElementRenderContext;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

public interface GuiElement {

    void click(InventoryClickEvent event);
    void render(GuiElementRenderContext context);
    UUID uuid();
    ItemStack getItem();

}
