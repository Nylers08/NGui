package io.neris.NGui.core.listeners.guiElement;

import io.neris.NGui.core.gui.element.EventContext;
import io.neris.NGui.core.gui.element.EventContextBuilder;
import io.neris.NGui.core.gui.element.services.nbtAction.NbtActionExecutor;
import io.papermc.paper.event.player.PlayerSwapWithEquipmentSlotEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;

public class ItemEventNbtActionListener implements Listener {

    private final NbtActionExecutor nbtActionExecutor;

    public ItemEventNbtActionListener(NbtActionExecutor nbtActionExecutor) {
        this.nbtActionExecutor = nbtActionExecutor;
    }

    @EventHandler
    public void inventoryClick(InventoryClickEvent event){
        EventContext context = EventContextBuilder.by(event);
        nbtActionExecutor.execute(context);
    }

    @EventHandler
    public void dropItem(PlayerDropItemEvent event){
        EventContext context = EventContextBuilder.by(event);
        nbtActionExecutor.execute(context);
    }

    @EventHandler
    private void swapHandItems(PlayerSwapHandItemsEvent event){
        EventContext context = EventContextBuilder.by(event);
        nbtActionExecutor.execute(context);
    }

    @EventHandler
    private void swapWithEquipItems(PlayerSwapWithEquipmentSlotEvent event){
        EventContext context = EventContextBuilder.by(event);
        nbtActionExecutor.execute(context);
    }

    @EventHandler
    private void itemMend(PlayerItemMendEvent event){
        EventContext context = EventContextBuilder.by(event);
        nbtActionExecutor.execute(context);
    }

    @EventHandler
    private void itemDamage(PlayerItemDamageEvent event){
        EventContext context = EventContextBuilder.by(event);
        nbtActionExecutor.execute(context);
    }

    @EventHandler
    private void interactItem(PlayerInteractEvent event){
        EventContext context = EventContextBuilder.by(event);
        nbtActionExecutor.execute(context);
    }
}
