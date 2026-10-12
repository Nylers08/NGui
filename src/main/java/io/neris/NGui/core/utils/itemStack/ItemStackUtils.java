package io.neris.NGui.core.utils.itemStack;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
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

    public static void rename(@NotNull ItemStack itemStack, @NotNull Component name){
        ItemMeta meta = itemStack.getItemMeta();
        if(meta == null){
            return;
        }

        meta.displayName(name);
        itemStack.setItemMeta(meta);
    }
}
