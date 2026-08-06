package io.neris.NGui.core.gui.element;

import io.neris.NGui.core.utils.event.ItemStackEventUtils;
import io.neris.NGui.core.utils.itemStack.ItemStackUtils;
import io.papermc.paper.event.player.PlayerSwapWithEquipmentSlotEvent;
import org.bukkit.entity.Item;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class EventContextBuilder {

    public static EventContext by(@NotNull InventoryClickEvent event){
        EventContext context = new EventContext(event);

        context.whoClicked = event.getWhoClicked();
        context.inventory = event.getClickedInventory();
        context.clickType = event.getClick();
        context.items = ItemStackEventUtils.extractItemStack(event);

        return context;
    }

    public static EventContext by(@NotNull PlayerDropItemEvent event){
        EventContext context = byPlayerEvent(event);

        Item item = event.getItemDrop();
        ItemStack itemStack = item.getItemStack();
        addItemToListIfNotEmpty(context, itemStack);

        return context;
    }

    public static EventContext by(@NotNull PlayerSwapHandItemsEvent event){
        EventContext context = byPlayerEvent(event);

        ItemStack mainItem = event.getMainHandItem();
        ItemStack offItem = event.getOffHandItem();

        addItemToListIfNotEmpty(context, mainItem, offItem);

        return context;
    }

    public static EventContext by(@NotNull PlayerSwapWithEquipmentSlotEvent event){
        EventContext context = byPlayerEvent(event);

        ItemStack handItem = event.getItemInHand();
        ItemStack equipItem = event.getItemToSwap();
        addItemToListIfNotEmpty(context, handItem, equipItem);

        return context;
    }

    public static EventContext by(@NotNull PlayerItemMendEvent event){
        EventContext context = byPlayerEvent(event);

        ItemStack itemStack = event.getItem();
        addItemToListIfNotEmpty(context, itemStack);

        return context;
    }

    public static EventContext by(@NotNull PlayerItemDamageEvent event) {
        EventContext context = byPlayerEvent(event);

        ItemStack itemStack = event.getItem();
        addItemToListIfNotEmpty(context, itemStack);

        return context;
    }

    public static EventContext by(@NotNull PlayerInteractEvent event){
        EventContext context = byPlayerEvent(event);

        ItemStack itemStack = event.getItem();
        addItemToListIfNotEmpty(context, itemStack);

        return context;
    }

    public static EventContext byPlayerEvent(@NotNull PlayerEvent event){
        EventContext context = new EventContext(event);

        context.whoClicked = event.getPlayer();
        context.inventory = event.getPlayer().getInventory();

        return context;
    }


    protected static void addItemToListIfNotEmpty(EventContext context, ItemStack... itemStacks){
        for (ItemStack item : itemStacks){
            addItemToListIfNotEmpty(context, item);
        }
    }

    protected static void addItemToListIfNotEmpty(EventContext context, ItemStack itemStack){
        if(!ItemStackUtils.isItemStackEmpty(itemStack)){
            context.items.add(itemStack);
        }
    }
}
