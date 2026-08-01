package io.neris.NGui.core.gui.element.services.slotBinding;

import io.neris.NGui.core.gui.element.GuiElementPosition;
import io.neris.NGui.core.gui.element.view.GuiElement;
import io.neris.NGui.core.gui.menu.Menu;
import org.bukkit.inventory.Inventory;

public class GuiElementInstaller {

    private final GuiElementBindingSystem elementRegistry;

    public GuiElementInstaller(GuiElementBindingSystem elementRegistry) {
        this.elementRegistry = elementRegistry;
    }

    public void install(Menu menu, int slot, GuiElement element){
        menu.setItem(element.getItem(), slot);
        register(menu.getInventory(), slot, element);
    }

    public void install(Inventory inventory, int slot, GuiElement element){
        inventory.setItem(slot, element.getItem());
        register(inventory, slot, element);
    }


    private void register(Inventory inventory, int slot, GuiElement element){
        GuiElementPosition position = new GuiElementPosition(inventory, slot, element.uuid());
        elementRegistry.register(position, element);
    }


}
