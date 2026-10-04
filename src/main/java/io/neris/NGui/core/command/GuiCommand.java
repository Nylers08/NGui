package io.neris.NGui.core.command;

import io.neris.NGui.core.services.ServiceController;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class GuiCommand implements CommandExecutor {

    private final ServiceController serviceController;

    public GuiCommand(ServiceController serviceController) {
        this.serviceController = serviceController;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {


        return true;
    }


}
