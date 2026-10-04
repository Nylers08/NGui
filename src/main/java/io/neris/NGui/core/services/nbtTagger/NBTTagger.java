package io.neris.NGui.core.services.nbtTagger;

import io.neris.NGui.core.utils.nbt.NBTData;
import io.neris.NGui.core.utils.nbt.NBTUtils;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

import static io.neris.NGui.core.utils.nbt.NBTUtils.addCustomStringNBT;

public class NBTTagger {

    @Getter private final JavaPlugin plugin;

    public NBTTagger(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void addStringNBT(@NotNull ItemStack itemStack, @NotNull String key, String value){
        NBTData nbtData = new NBTData(plugin, key, value);
        addCustomStringNBT(itemStack, nbtData);
    }

    public void removeStringNBT(@NonNull ItemStack itemStack, @NonNull String key){
        NBTData nbtData = new NBTData(plugin, key, "");
        NBTUtils.removeStringNBT(itemStack, nbtData);
    }

    public String getValueStringNBT(@NotNull ItemStack itemStack, @NotNull String key){
        NBTData nbtData = new NBTData(plugin, key, "");
        return NBTUtils.getCustomStringNBT(itemStack, nbtData);
    }

    public Set<String> getKeys(@NotNull ItemStack itemStack){
        return NBTUtils.getKeys(itemStack);
    }

    public Set<NamespacedKey> getNamespacedKeys(@NotNull ItemStack itemStack){
        return NBTUtils.getNamespacedKeys(itemStack);
    }
}
