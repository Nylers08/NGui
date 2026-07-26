package io.neris.NGui.core.services.nbtTagger;

import io.neris.NGui.core.utils.nbt.NBTData;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class NBTTagger {

    @Getter private final JavaPlugin plugin;

    public NBTTagger(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void addStringNBT(ItemStack itemStack, String key, String value){
        NBTData nbtData = new NBTData(plugin, key, value);
        io.neris.NGui.core.utils.nbt.NBTUtils.addCustomStringNBT(itemStack, nbtData);
    }
}
