package io.neris.NGui.core.utils.nbt;

import lombok.NonNull;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NBTUtils {

    public static void addCustomStringNBT(@NotNull ItemStack itemStack, @NotNull NBTData nbtData){
        ItemMeta meta = itemStack.getItemMeta();
        if (meta == null) return;

        PersistentDataContainer dataContainer = meta.getPersistentDataContainer();

        NamespacedKey namespacedKey = buildNamespacedKeyFrom(nbtData);
        dataContainer.set(namespacedKey, PersistentDataType.STRING, nbtData.getValue());

        itemStack.setItemMeta(meta);
    }

    public static void removeStringNBT(@NonNull ItemStack itemStack, @NonNull NBTData nbtData){
        ItemMeta meta = itemStack.getItemMeta();
        if(meta == null) return;

        PersistentDataContainer dataContainer = meta.getPersistentDataContainer();
        NamespacedKey key = buildNamespacedKeyFrom(nbtData);
        dataContainer.remove(key);

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

    public static Set<NamespacedKey> getNamespacedKeys(@NotNull ItemStack itemStack){
        ItemMeta meta = itemStack.getItemMeta();
        if(meta == null){
            return Set.of();
        }

        PersistentDataContainer dataContainer = meta.getPersistentDataContainer();
        return dataContainer.getKeys();
    }

    public static Set<String> getKeys(ItemStack itemStack){
        Set<String> keys = new HashSet<>();
        Set<NamespacedKey> namespacedKeys = getNamespacedKeys(itemStack);
        for (NamespacedKey namespacedKey : namespacedKeys){
            keys.add(namespacedKey.getKey());
        }

        return keys;
    }

    public static boolean hasKey(ItemStack itemStack, String key){
        Set<String> keys = getKeys(itemStack);
        return keys.contains(key);
    }
}
