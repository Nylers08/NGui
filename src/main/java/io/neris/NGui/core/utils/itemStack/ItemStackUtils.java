package io.neris.NGui.core.utils.itemStack;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class ItemStackUtils {

    public static boolean isItemStackEmpty(ItemStack itemStack){
        return itemStack == null || itemStack.isEmpty();
    }

    public static Optional<ItemStack> parseToOptional(@Nullable ItemStack itemStack){
        if(isItemStackEmpty(itemStack)){
            return Optional.empty();
        }

        return Optional.of(itemStack);
    }
}
