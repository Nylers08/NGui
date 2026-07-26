package io.neris.NGui;

import io.neris.NGui.core.command.MyCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class NGui extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        this.getCommand("menu").setExecutor(new MyCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }
}
