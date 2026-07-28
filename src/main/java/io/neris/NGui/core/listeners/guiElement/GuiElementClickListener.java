package io.neris.NGui.core.listeners.guiElement;

import io.neris.NGui.core.GuiElement.services.GuiElementRegistry;
import io.neris.NGui.core.services.nbtTagger.NBTKeys;
import io.neris.NGui.core.utils.nbt.NBTData;
import io.neris.NGui.core.utils.nbt.NBTUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;


public class GuiElementClickListener implements Listener {

    private final JavaPlugin plugin;
    private final GuiElementRegistry elementRegistry;

    public GuiElementClickListener(@NotNull JavaPlugin plugin, @NotNull GuiElementRegistry elementRegistry) {
        this.plugin = plugin;
        this.elementRegistry = elementRegistry;
    }

    @EventHandler
    public void guiElementClick(InventoryClickEvent event){
        ItemStack item = event.getCurrentItem();

        if(item == null){
            return;
        }

        NBTData nbtData = new NBTData(plugin, NBTKeys.GUI_ELEMENT, " ");
        String strUuid = NBTUtils.getCustomStringNBT(item, nbtData);

        if(strUuid == null || strUuid.isEmpty()){
            return;
        }

        UUID uuid = UUID.fromString(strUuid);
        elementRegistry.get(uuid).click(event);
    }


}
