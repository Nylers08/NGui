package io.neris.NGui.core.gui.button.services.nbtAction;

import io.neris.NGui.core.gui.button.ClickEventContext;
import io.neris.NGui.core.gui.button.MainItemEventContext;
import io.neris.NGui.core.gui.button.controller.actions.ButtonAction;
import io.neris.NGui.core.utils.nbt.NBTUtils;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class NbtActionExecutor {

    private final NbtActionRegistry registry;

    public NbtActionExecutor(NbtActionRegistry registry) {
        this.registry = registry;
    }


    public void execute(ClickEventContext context){
        for (ItemStack itemStack : context.items){
            MainItemEventContext mainItemEventContext = new MainItemEventContext(context, itemStack);
            execute(mainItemEventContext);
        }
    }

    public void execute(MainItemEventContext mainItemEventContext){
        execute(mainItemEventContext, mainItemEventContext.getMainItem());
    }

    public void execute(ClickEventContext clickContext, ItemStack itemStack){
        Set<String> keys = NBTUtils.getKeys(itemStack);
        execute(clickContext, keys);
    }

    public void execute(ClickEventContext clickContext, String... keys){
        execute(clickContext, List.of(keys));
    }

    public void execute(ClickEventContext clickContext, Collection<String> keys){
        for (String key : keys){
            ButtonAction action = registry.getAction(key);
            if(action != null){
                action.execute(clickContext);
            }
        }
    }
}
