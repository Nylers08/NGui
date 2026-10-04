package io.neris.NGui.core.command;

import org.bukkit.command.CommandSender;

public interface SubCommand {

    void execute(CommandSender sender, String[] args);
}
