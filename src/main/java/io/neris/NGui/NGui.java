package io.neris.NGui;

import io.neris.NGui.core.command.MyCommand;
import io.neris.NGui.core.listeners.guiElement.GuiElementClickListener;
import io.neris.NGui.core.services.ServiceController;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class NGui extends JavaPlugin {

    @Getter private ServiceController serviceController;

    @Override
    public void onEnable() {
        // Plugin startup logic

        serviceController = new ServiceController(this);

        this.getServer().getPluginManager().registerEvents(new GuiElementClickListener(
                serviceController.getElementRegistry()),
                this);

        this.getCommand("menu").setExecutor(new MyCommand(serviceController));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }
}
