package io.neris.NGui.core.gui.element.controller.renderers;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class PlayerNicknameRenderer implements GUIElementRenderer {

    @Override
    public ItemStack render(GuiElementRenderContext context) {
        UUID playerUUID = context.getPlayerUUID();
        String playerName = Bukkit.getOfflinePlayer(playerUUID).getName();

        ItemStack itemStack = new ItemStack(Material.EMERALD);
        ItemMeta meta = itemStack.getItemMeta();
        meta.displayName(Component.text("§6Ты §0" + playerName));
        itemStack.setItemMeta(meta);

        return itemStack;
    }
}
