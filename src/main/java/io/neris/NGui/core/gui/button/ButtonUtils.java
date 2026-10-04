package io.neris.NGui.core.gui.button;

import org.bukkit.inventory.ItemStack;

import java.util.Optional;

import static io.neris.NGui.core.utils.itemStack.ItemStackUtils.parseToOptional;

public class ButtonUtils {

    public static Optional<ItemStack> extractMainItemStack(ClickEventContext context){
        if(context instanceof MainItemEventContext mainItemEventContext){
            ItemStack itemStack = mainItemEventContext.getMainItem();
            return parseToOptional(itemStack);
        }

        ItemStack itemStack = context.items.getFirst();
        return Optional.ofNullable(itemStack);
    }
}
