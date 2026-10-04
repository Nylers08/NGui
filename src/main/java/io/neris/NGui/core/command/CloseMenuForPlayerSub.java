package io.neris.NGui.core.command;

import io.neris.NGui.core.gui.menu.services.MenuCloser;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CloseMenuForPlayerSub implements SubCommand{

    private final MenuCloser<UUID> menuCloser;

    public CloseMenuForPlayerSub(MenuCloser<UUID> menuCloser) {
        this.menuCloser = menuCloser;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!sender.hasPermission("ngui.closefor")){
            return;
        }

        if(args.length < 2){
            sender.sendMessage("Укажите имя игрока. /menu closeall <ник>");
            return;
        }

        String targetName = args[1];
        Player targetPlayer = Bukkit.getPlayer(targetName);
        if(targetPlayer == null){
            sender.sendMessage("Игрок не найден!");
            return;
        }

        menuCloser.close(targetPlayer.getUniqueId());
    }
}
