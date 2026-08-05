package io.neris.NGui.core.gui.element.services.installers;

import io.neris.NGui.core.gui.element.element.GuiElement;
import io.neris.NGui.core.gui.element.services.slotBinding.GuiElementBindingSystem;
import io.neris.NGui.core.gui.element.services.slotBinding.GuiElementPosition;
import io.neris.NGui.core.gui.menu.Menu;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class SlotBindingGuiElementInstaller implements GuiElementInstaller{

    private final GuiElementBindingSystem elementRegistry;

    public SlotBindingGuiElementInstaller(GuiElementBindingSystem elementRegistry) {
        this.elementRegistry = elementRegistry;
    }


    public void install(@NotNull Menu menu, @NotNull GuiElement element, int... slots){
        for (int slot : slots){
            menu.setItem(element.getItem(), slot);
            register(menu.getInventory(), slot, element);
        }
    }

    public void install(@NotNull Inventory inventory, @NotNull GuiElement element, int... slots){
        for (int slot : slots){
            inventory.setItem(slot, element.getItem());
            register(inventory, slot, element);
        }
    }


    private void register(Inventory inventory, int slot, GuiElement element){
        GuiElementPosition position = new GuiElementPosition(inventory, slot, element.uuid());
        elementRegistry.register(position, element);
    }


}
