package io.neris.NGui.core.utils.nbt;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class NBTUtils {

    public static void addCustomStringNBT(@NotNull ItemStack itemStack, @NotNull NBTData nbtData){
        ItemMeta meta = itemStack.getItemMeta();
        if (meta == null) return;

        PersistentDataContainer dataContainer = meta.getPersistentDataContainer();

        NamespacedKey namespacedKey = buildNamespacedKeyFrom(nbtData);
        dataContainer.set(namespacedKey, PersistentDataType.STRING, nbtData.getValue());

        itemStack.setItemMeta(meta);
    }

    public static String getCustomStringNBT(@NotNull ItemStack itemStack, NBTData nbtData){
        ItemMeta meta = itemStack.getItemMeta();
        if (meta == null) return null;

        PersistentDataContainer dataContainer = meta.getPersistentDataContainer();
        NamespacedKey namespacedKey = buildNamespacedKeyFrom(nbtData);

        return dataContainer.get(namespacedKey, PersistentDataType.STRING);
    }

    public static NamespacedKey buildNamespacedKeyFrom(@NotNull NBTData nbtData){
        return new NamespacedKey(nbtData.getPlugin(), nbtData.getKey());
    }
}
