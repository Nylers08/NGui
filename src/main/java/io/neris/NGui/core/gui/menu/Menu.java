package io.neris.NGui.core.gui.menu;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface Menu {

    void setItems(@NotNull ItemStack... items);

    ItemStack getItem(int slot);
}
