package io.neris.NGui.core.command;

import io.neris.NGui.core.example.TestMenuFactory;
import io.neris.NGui.core.gui.menu.services.MenuFactory;
import io.neris.NGui.core.gui.menu.services.MenuOpener;
import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TestMenuOpenSub implements SubCommand{

    @Getter private final MenuOpener<HumanEntity> menuOpener;
    @Getter private final MenuFactory<UUID> menuFactory;

    public TestMenuOpenSub(MenuOpener<HumanEntity> menuOpener) {
        this.menuOpener = menuOpener;
        this.menuFactory = new TestMenuFactory();
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!sender.hasPermission("ngui.test")){
            return;
        }

        if(!(sender instanceof HumanEntity player)){
            sender.sendMessage("Только HumanEntity может открыть меню");
            return;
        }

        menuOpener.open(player, menuFactory.create(player.getUniqueId()));
    }
}
