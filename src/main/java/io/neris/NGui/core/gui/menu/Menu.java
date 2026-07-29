package io.neris.NGui.core.gui.menu;

import io.neris.NGui.core.gui.element.view.GuiElement;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface Menu {

    void setItems(@NotNull ItemStack... items);
    void setGuiElements(@NotNull GuiElement... elements);

    ItemStack getItem(int slot);
}
