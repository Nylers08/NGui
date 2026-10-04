package io.neris.NGui.core.gui.button.services.installers;

import io.neris.NGui.core.gui.button.button.Button;
import io.neris.NGui.core.gui.menu.Menu;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class BaseButtonInstaller implements ButtonInstaller {
    @Override
    public void install(@NotNull Menu menu, @NotNull Button element, int... slots) {
        Inventory inventory = menu.getInventory();
        install(inventory, element, slots);
    }

    @Override
    public void install(@NotNull Inventory inventory, @NotNull Button element, int... slots) {
        ItemStack item = element.getItem();
        for (int slot : slots){
            inventory.setItem(slot, item);
        }
    }
}
