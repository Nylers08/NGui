package io.neris.NGui.core.gui.button;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

public class MainItemEventContext extends ClickEventContext {

    @Getter @Setter
    private ItemStack mainItem;

    public MainItemEventContext(ClickEventContext other, ItemStack mainItem){
        super(other);
        this.mainItem = mainItem;
    }
}
