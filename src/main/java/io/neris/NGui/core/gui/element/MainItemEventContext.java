package io.neris.NGui.core.gui.element;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

public class MainItemEventContext extends EventContext {

    @Getter @Setter
    private ItemStack mainItem;

    public MainItemEventContext(EventContext other, ItemStack mainItem){
        super(other);
        this.mainItem = mainItem;
    }
}
