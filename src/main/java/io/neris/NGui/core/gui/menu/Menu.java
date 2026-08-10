package io.neris.NGui.core.gui.menu;

import net.kyori.adventure.text.Component;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface Menu {

    void setItem(ItemStack item, int... slots);

    ItemStack getItem(int slot);
    Inventory getInventory();
    Component getTitle();

    void changeName(Component name);
    void changeSize(int size);

    void reopen();
    void clear();
}
