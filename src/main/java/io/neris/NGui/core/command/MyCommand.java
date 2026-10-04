package io.neris.NGui.core.command;

import io.neris.NGui.core.gui.button.controller.actions.NbtActionKeys;
import io.neris.NGui.core.gui.menu.Menu;
import io.neris.NGui.core.gui.menu.TestMenuFactory;
import io.neris.NGui.core.services.ServiceController;
import io.neris.NGui.core.utils.gui.action.NBTActionUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class MyCommand implements CommandExecutor {

    private final ServiceController serviceController;

    public MyCommand(ServiceController serviceController) {
        this.serviceController = serviceController;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        Player player = (Player) sender;
        Menu menu = new TestMenuFactory().create(player.getUniqueId());

        if(args.length == 1){
            ItemStack itemStack = menu.getItem(0);
            NBTActionUtils.setBool(itemStack, NbtActionKeys.CANCEL_PUT, false);
        }
        if(args.length == 2){
            ItemStack itemStack = menu.getItem(0);
            NBTActionUtils.setBool(itemStack, NbtActionKeys.CANCEL_PUT, true);
        }

        serviceController.getPlayerMenuOpener().open(player, menu);

        return true;
    }


}
