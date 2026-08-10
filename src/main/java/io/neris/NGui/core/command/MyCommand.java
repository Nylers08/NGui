package io.neris.NGui.core.command;

import io.neris.NGui.core.gui.element.factory.GuiElementFactory;
import io.neris.NGui.core.gui.element.element.GuiElement;
import io.neris.NGui.core.gui.menu.BaseMenu;
import io.neris.NGui.core.gui.menu.Menu;
import io.neris.NGui.core.gui.menu.services.MenuOpener;
import io.neris.NGui.core.services.ServiceController;
import io.neris.NGui.core.utils.itemStack.InventoryBuilder;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.UUID;

public class MyCommand implements CommandExecutor {

    private final ServiceController serviceController;

    public MyCommand(ServiceController serviceController) {
        this.serviceController = serviceController;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if(!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;

        TestFactoryContext context = new TestFactoryContext(player.getUniqueId(), Material.DIAMOND, 6);
        TestGuiElementFactory factory = new TestGuiElementFactory(serviceController.getNbtTagger());
        GuiElement element = factory.create(context);

        MenuOpener<HumanEntity> menuOpener = serviceController.getPlayerMenuOpener();
        Component title = Component.text("StrelikLox");
        Inventory inv = new InventoryBuilder()
                .size(27)
                .title(title)
                .build();

        Menu menu = new BaseMenu(inv, title);
        serviceController.getBaseElementInstaller().install(menu, element, 0,8,18,26);

        if(args.length == 0){
            menuOpener.open(player, menu);
            return true;
        } else if (args[0].equalsIgnoreCase("size")) {
            int size = serviceController.getOpenedMenuRegistry().countViewers();
            player.sendMessage("Size opened menu: " + size);
            return true;
        }

        return false;
    }


}
