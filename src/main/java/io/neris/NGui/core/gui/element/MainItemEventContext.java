package io.neris.NGui.core.gui.element;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class MainItemEventContext extends EventContext {

    @Getter @Setter
    private ItemStack mainItem;

    public MainItemEventContext(@NotNull Event event, ItemStack mainItem) {
        super(event);
        this.mainItem = mainItem;
    }

    public MainItemEventContext(EventContext other, ItemStack mainItem){
        super(other);
        this.mainItem = mainItem;
    }
}
