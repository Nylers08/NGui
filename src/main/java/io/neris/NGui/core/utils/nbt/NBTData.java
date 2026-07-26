package io.neris.NGui.core.utils.nbt;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class NBTData {

    @Getter private final JavaPlugin plugin;
    @Getter private final String key;
    private final String value;

    public NBTData(@NotNull JavaPlugin plugin, @NotNull String key, @NotNull String value) {
        this.plugin = plugin;
        this.key = key;
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
