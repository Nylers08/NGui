package io.neris.NGui.core.command;

import io.neris.NGui.core.gui.menu.services.PlayerMenuCloser;
import lombok.Getter;
import org.bukkit.command.CommandSender;

public class AllMenuCloserSub implements SubCommand{

    @Getter private final PlayerMenuCloser menuCloser;

    public AllMenuCloserSub(PlayerMenuCloser menuCloser) {
        this.menuCloser = menuCloser;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!sender.hasPermission("ngui.closeall")){
            return;
        }

        menuCloser.closeAll();
    }
}
