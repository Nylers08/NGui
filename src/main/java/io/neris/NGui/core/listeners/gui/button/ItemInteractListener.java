package io.neris.NGui.core.listeners.gui.button;

import io.neris.NGui.core.gui.button.ClickEventContext;
import io.neris.NGui.core.gui.button.ClickContextBuilder;
import io.neris.NGui.core.gui.button.services.nbtAction.NbtActionExecutor;
import io.papermc.paper.event.player.PlayerSwapWithEquipmentSlotEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;

public class ItemInteractListener implements Listener {

    private final NbtActionExecutor nbtActionExecutor;

    public ItemInteractListener(NbtActionExecutor nbtActionExecutor) {
        this.nbtActionExecutor = nbtActionExecutor;
    }

    @EventHandler
    public void inventoryClick(InventoryClickEvent event){
        ClickEventContext context = ClickContextBuilder.by(event);
        nbtActionExecutor.execute(context);
    }

    @EventHandler
    public void dropItem(PlayerDropItemEvent event){
        ClickEventContext context = ClickContextBuilder.by(event);
        nbtActionExecutor.execute(context);
    }

    @EventHandler
    private void swapHandItems(PlayerSwapHandItemsEvent event){
        ClickEventContext context = ClickContextBuilder.by(event);
        nbtActionExecutor.execute(context);
    }

    @EventHandler
    private void swapWithEquipItems(PlayerSwapWithEquipmentSlotEvent event){
        ClickEventContext context = ClickContextBuilder.by(event);
        nbtActionExecutor.execute(context);
    }

    @EventHandler
    private void itemMend(PlayerItemMendEvent event){
        ClickEventContext context = ClickContextBuilder.by(event);
        nbtActionExecutor.execute(context);
    }

    @EventHandler
    private void itemDamage(PlayerItemDamageEvent event){
        ClickEventContext context = ClickContextBuilder.by(event);
        nbtActionExecutor.execute(context);
    }

    @EventHandler
    private void interactItem(PlayerInteractEvent event){
        ClickEventContext context = ClickContextBuilder.by(event);
        nbtActionExecutor.execute(context);
    }
}
