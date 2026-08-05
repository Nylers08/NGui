package io.neris.NGui.core.gui.element.services.installers;

import io.neris.NGui.core.gui.element.element.GuiElement;
import io.neris.NGui.core.gui.menu.Menu;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class BaseGuiElementInstaller implements GuiElementInstaller{
    @Override
    public void install(@NotNull Menu menu, @NotNull GuiElement element, int... slots) {
        Inventory inventory = menu.getInventory();
        install(inventory, element, slots);
    }

    @Override
    public void install(@NotNull Inventory inventory, @NotNull GuiElement element, int... slots) {
        ItemStack item = element.getItem();
        for (int slot : slots){
            inventory.setItem(slot, item);
        }
    }
}
