package io.neris.NGui.core.GuiElement.controller;

import io.neris.NGui.core.GuiElement.GuiElementClickContext;
import org.bukkit.inventory.ItemStack;

public interface GuiElementController {

    void execute(GuiElementClickContext clickContext);
    ItemStack render();
}
