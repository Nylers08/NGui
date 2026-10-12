package io.neris.NGui;

import io.neris.NGui.core.command.GuiCommand;
import io.neris.NGui.core.listeners.gui.button.ItemInteractListener;
import io.neris.NGui.core.listeners.gui.menu.MenuClosedListener;
import io.neris.NGui.core.services.MenuServiceController;
import io.neris.NGui.core.services.StaticMenuServiceController;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class NGui extends JavaPlugin {

    @Getter private MenuServiceController menuServiceController;

    @Override
    public void onEnable() {
        // Plugin startup logic

        menuServiceController = new MenuServiceController(this);
        StaticMenuServiceController.init(menuServiceController);

        this.getServer().getPluginManager().registerEvents(new ItemInteractListener(menuServiceController.getNbtActionExecutor()), this);

        this.getServer().getPluginManager().registerEvents(
                new MenuClosedListener(menuServiceController.getOpenedMenuRegistry()),
                this
        );

        this.getCommand("menu").setExecutor(new GuiCommand(menuServiceController));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }
}
