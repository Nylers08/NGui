package io.neris.NGui.core.gui.menu;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface Menu {

    void setItem(ItemStack item, int... slots);

    ItemStack getItem(int slot);
}
