package io.neris.NGui.core.utils.event;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.neris.NGui.core.utils.itemStack.ItemStackUtils.isItemStackEmpty;
import static io.neris.NGui.core.utils.itemStack.ItemStackUtils.parseToOptional;

public class ItemStackEventUtils {

    public static List<ItemStack> extractItemStack(Event event){
        return tryGetItemStackIfInventoryClickEvent(event);
    }


    private static List<ItemStack> tryGetItemStackIfInventoryClickEvent(Event event){
        if(event instanceof InventoryClickEvent clickEvent){
            return tryGetItemStack(clickEvent);
        }

        return List.of();
    }

    private static List<ItemStack> tryGetItemStack(InventoryClickEvent event){
        List<ItemStack> items = new ArrayList<>();

        // Если нажали на предмет
        ItemStack itemStack = event.getCurrentItem();
        if(!isItemStackEmpty(itemStack)){
            items.add(itemStack);
        }

        // Если предмет переместили из хотбара в слот крафта
        Optional<ItemStack> optItemStack = tryGetItemByHotToCrafting(event);
        optItemStack.ifPresent(items::add);

        // Если переместили из хотбара в инвентарь
        optItemStack = tryGetItemByHotbar(event);
        optItemStack.ifPresent(items::add);

        return items;
    }

    private static Optional<ItemStack> tryGetItemByHotbar(InventoryClickEvent event){
        Inventory inventory = event.getClickedInventory();
        int hotbarSlot = event.getHotbarButton();
        if(inventory == null || hotbarSlot == -1){
            return Optional.empty();
        }

        ItemStack itemStack = inventory.getItem(hotbarSlot);
        return parseToOptional(itemStack);
    }

    private static Optional<ItemStack> tryGetItemByHotToCrafting(InventoryClickEvent event){
        if(!isHotCraftMoved(event)){
            return Optional.empty();
        }

        HumanEntity human = event.getWhoClicked();
        Inventory playerInv = human.getInventory();

        int hotbarSlot = event.getHotbarButton();
        ItemStack itemStack = playerInv.getItem(hotbarSlot);

        return parseToOptional(itemStack);
    }

    private static boolean isHotCraftMoved(InventoryClickEvent event){
        return  event.getClick() == ClickType.NUMBER_KEY &&
                event.getSlotType() == InventoryType.SlotType.CRAFTING;
    }

}
