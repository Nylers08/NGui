package io.neris.NGui.core.command;

import io.neris.NGui.core.gui.element.factory.GuiElementFactory;
import io.neris.NGui.core.gui.element.element.GuiElement;
import io.neris.NGui.core.services.ServiceController;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

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
        GuiElementFactory<UUID> elementFactory = new TestGuiElementFactory(serviceController.getNbtTagger());

        if(args.length == 0){
            Inventory inventory = player.getInventory();
            GuiElement element = elementFactory.create(player.getUniqueId());
            serviceController.getBaseElementInstaller().install(inventory, element, 0, 8);
            return true;
        } else if(args.length == 1 && args[0].equalsIgnoreCase("gc")){
            System.gc();
            player.sendMessage("Garbage collector called");
            return true;
        }

        return false;
    }


}
