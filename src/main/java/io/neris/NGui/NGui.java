package io.neris.NGui;

import io.neris.NGui.core.command.GuiCommand;
import io.neris.NGui.core.listeners.gui.button.ItemInteractListener;
import io.neris.NGui.core.listeners.gui.menu.MenuClosedListener;
import io.neris.NGui.core.services.ServiceController;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class NGui extends JavaPlugin {

    @Getter private ServiceController serviceController;

    @Override
    public void onEnable() {
        // Plugin startup logic

        serviceController = new ServiceController(this);

        this.getServer().getPluginManager().registerEvents(new ItemInteractListener(serviceController.getNbtActionExecutor()), this);

        this.getServer().getPluginManager().registerEvents(
                new MenuClosedListener(serviceController.getOpenedMenuRegistry()),
                this
        );

        this.getCommand("menu").setExecutor(new GuiCommand(serviceController));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }
}
