package io.neris.NGui.core.command;

import io.neris.NGui.core.gui.element.factory.GuiElementFactory;
import io.neris.NGui.core.gui.element.element.GuiElement;
import io.neris.NGui.core.services.ServiceController;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class MyCommand implements CommandExecutor {

    private final ServiceController serviceController;

    private final TestInventoryRegistry inventoryRegistry;
    private final Inventory testInventory;


    public MyCommand(ServiceController serviceController) {
        this.serviceController = serviceController;
        this.inventoryRegistry = new TestInventoryRegistry(serviceController.getPlugin());
        this.testInventory = Bukkit.createInventory(null, 27);
        inventoryRegistry.register(testInventory);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if(!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;
        TestFactoryContext factoryContext1 = new TestFactoryContext(player.getUniqueId(), Material.GOLDEN_APPLE, 1);
        TestFactoryContext factoryContext2 = new TestFactoryContext(player.getUniqueId(), Material.CHAINMAIL_HELMET, 1);
        TestFactoryContext factoryContext3 = new TestFactoryContext(player.getUniqueId(), Material.DIAMOND_PICKAXE, 1);
        TestFactoryContext factoryContext4 = new TestFactoryContext(player.getUniqueId(), Material.DIAMOND, 64);
        GuiElementFactory<TestFactoryContext> elementFactory = new TestGuiElementFactory(serviceController.getNbtTagger());

        if(args.length == 0){
            Inventory invWithOwner = Bukkit.createInventory(player, 27);
            Inventory invEmpty = Bukkit.createInventory(null, 27);
            inventoryRegistry.register(invWithOwner);
            inventoryRegistry.register(invEmpty);

            player.openInventory(invEmpty);
            return true;
        } else if(args.length == 1 && args[0].equalsIgnoreCase("gc")){
            System.gc();
            player.sendMessage("Garbage collector called");
            return true;
        } else if(args.length == 1 && args[0].equalsIgnoreCase("size")){
            player.sendMessage("Size: " + inventoryRegistry.size());
            return true;
        } else if(args.length == 1 && args[0].equalsIgnoreCase("item")){
            GuiElement element1 = elementFactory.create(factoryContext1);
            GuiElement element2 = elementFactory.create(factoryContext2);
            GuiElement element3 = elementFactory.create(factoryContext3);
            GuiElement element4 = elementFactory.create(factoryContext4);
            serviceController.getBaseElementInstaller().install(player.getInventory(), element1, 0);
            serviceController.getBaseElementInstaller().install(player.getInventory(), element2, 1);
            serviceController.getBaseElementInstaller().install(player.getInventory(), element3, 2);
            serviceController.getBaseElementInstaller().install(player.getInventory(), element4, 3);
            return true;
        }

        return false;
    }


}
