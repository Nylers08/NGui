package io.neris.NGui.core.gui.element.controller.renderers;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class PlayerNicknameRenderer implements GUIElementRenderer {

    private final Material material;
    private final int amount;

    public PlayerNicknameRenderer(Material material, int amount) {
        this.material = material;
        this.amount = amount;
    }

    @Override
    public ItemStack render(GuiElementRenderContext context) {
        UUID playerUUID = context.getPlayerUUID();
        String playerName = Bukkit.getOfflinePlayer(playerUUID).getName();

        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta meta = itemStack.getItemMeta();
        meta.displayName(Component.text("§6Ты §0" + playerName));
        itemStack.setItemMeta(meta);

        return itemStack;
    }
}
