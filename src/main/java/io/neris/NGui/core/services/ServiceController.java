package io.neris.NGui.core.services;

import io.neris.NGui.core.gui.button.controller.actions.CancelPutAction;
import io.neris.NGui.core.gui.button.controller.actions.MsgPlayerAction;
import io.neris.NGui.core.gui.button.services.installers.BaseButtonInstaller;
import io.neris.NGui.core.gui.button.services.nbtAction.NbtActionExecutor;
import io.neris.NGui.core.gui.button.services.nbtAction.NbtActionRegistry;
import io.neris.NGui.core.gui.menu.services.OpenedMenuRegistry;
import io.neris.NGui.core.gui.menu.services.PlayerMenuCloser;
import io.neris.NGui.core.gui.menu.services.PlayerMenuOpener;
import io.neris.NGui.core.services.nbtTagger.NBTTagger;
import io.neris.NGui.core.utils.gui.action.NBTActionUtils;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class ServiceController {

    @Getter private final JavaPlugin plugin;

    @Getter private final NBTTagger nbtTagger;

    @Getter private final BaseButtonInstaller baseElementInstaller;

    @Getter private final NbtActionRegistry nbtActionRegistry;
    @Getter private final NbtActionExecutor nbtActionExecutor;

    @Getter private final OpenedMenuRegistry openedMenuRegistry;
    @Getter private final PlayerMenuOpener playerMenuOpener;
    @Getter private final PlayerMenuCloser playerMenuCloser;

    public ServiceController(JavaPlugin plugin) {
        this.plugin = plugin;

        this.nbtTagger = new NBTTagger(plugin);

        this.baseElementInstaller = new BaseButtonInstaller();

        this.nbtActionRegistry = new NbtActionRegistry();
        this.nbtActionExecutor = new NbtActionExecutor(nbtActionRegistry);

        this.openedMenuRegistry = new OpenedMenuRegistry();
        this.playerMenuOpener = new PlayerMenuOpener(openedMenuRegistry);
        this.playerMenuCloser = new PlayerMenuCloser(openedMenuRegistry);

        initNbtAction();
        NBTActionUtils.init(nbtTagger);
    }


    private void initNbtAction(){
        nbtActionRegistry.register(new CancelPutAction(), new MsgPlayerAction(nbtTagger));
    }
}
