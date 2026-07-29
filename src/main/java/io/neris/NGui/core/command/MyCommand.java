package io.neris.NGui.core.command;

import io.neris.NGui.core.GuiElement.factory.GuiElementFactory;
import io.neris.NGui.core.GuiElement.view.GuiElement;
import io.neris.NGui.core.services.ServiceController;
import io.neris.NGui.core.services.nbtTagger.GuiElementTagger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
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
        GuiElementTagger elementTagger = serviceController.getGuiElementTagger();
        GuiElementFactory<UUID> elementFactory = new TestGuiElementFactory(elementTagger, serviceController.getElementRegistry());

        if(args.length == 0){
            GuiElement element = elementFactory.create(player.getUniqueId());
            player.getInventory().setItem(0, element.getItem());
            return true;
        } else if(args.length == 1 && args[0].equalsIgnoreCase("gc")){
            System.gc();
            player.sendMessage("Garbage collector called");
            return true;
        } else if(args.length == 1 && args[0].equalsIgnoreCase("size")){
            int size = serviceController.getElementRegistry().getRegistry().size();
            player.sendMessage("Registry size: " + size);
            return true;
        }

        return false;
    }


}
