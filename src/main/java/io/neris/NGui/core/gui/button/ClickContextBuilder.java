package io.neris.NGui.core.gui.button;

import io.neris.NGui.core.utils.event.ItemStackEventUtils;
import io.neris.NGui.core.utils.itemStack.ItemStackUtils;
import io.papermc.paper.event.player.PlayerSwapWithEquipmentSlotEvent;
import org.bukkit.entity.Item;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ClickContextBuilder {

    public static ClickEventContext by(@NotNull InventoryClickEvent event){
        ClickEventContext context = new ClickEventContext(event);

        context.whoClicked = event.getWhoClicked();
        context.inventory = event.getClickedInventory();
        context.clickType = event.getClick();
        context.items = ItemStackEventUtils.extractItemStack(event);

        return context;
    }

    public static ClickEventContext by(@NotNull PlayerDropItemEvent event){
        ClickEventContext context = byPlayerEvent(event);

        Item item = event.getItemDrop();
        ItemStack itemStack = item.getItemStack();
        addItemToListIfNotEmpty(context, itemStack);

        return context;
    }

    public static ClickEventContext by(@NotNull PlayerSwapHandItemsEvent event){
        ClickEventContext context = byPlayerEvent(event);

        ItemStack mainItem = event.getMainHandItem();
        ItemStack offItem = event.getOffHandItem();

        addItemToListIfNotEmpty(context, mainItem, offItem);

        return context;
    }

    public static ClickEventContext by(@NotNull PlayerSwapWithEquipmentSlotEvent event){
        ClickEventContext context = byPlayerEvent(event);

        ItemStack handItem = event.getItemInHand();
        ItemStack equipItem = event.getItemToSwap();
        addItemToListIfNotEmpty(context, handItem, equipItem);

        return context;
    }

    public static ClickEventContext by(@NotNull PlayerItemMendEvent event){
        ClickEventContext context = byPlayerEvent(event);

        ItemStack itemStack = event.getItem();
        addItemToListIfNotEmpty(context, itemStack);

        return context;
    }

    public static ClickEventContext by(@NotNull PlayerItemDamageEvent event) {
        ClickEventContext context = byPlayerEvent(event);

        ItemStack itemStack = event.getItem();
        addItemToListIfNotEmpty(context, itemStack);

        return context;
    }

    public static ClickEventContext by(@NotNull PlayerInteractEvent event){
        ClickEventContext context = byPlayerEvent(event);

        ItemStack itemStack = event.getItem();
        addItemToListIfNotEmpty(context, itemStack);

        return context;
    }

    public static ClickEventContext byPlayerEvent(@NotNull PlayerEvent event){
        ClickEventContext context = new ClickEventContext(event);

        context.whoClicked = event.getPlayer();
        context.inventory = event.getPlayer().getInventory();

        return context;
    }


    protected static void addItemToListIfNotEmpty(ClickEventContext context, ItemStack... itemStacks){
        for (ItemStack item : itemStacks){
            addItemToListIfNotEmpty(context, item);
        }
    }

    protected static void addItemToListIfNotEmpty(ClickEventContext context, ItemStack itemStack){
        if(!ItemStackUtils.isItemStackEmpty(itemStack)){
            context.items.add(itemStack);
        }
    }
}
