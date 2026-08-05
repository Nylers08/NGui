package io.neris.NGui.core.services;

import io.neris.NGui.core.gui.element.controller.actions.nbt.CancelPutNbtAction;
import io.neris.NGui.core.gui.element.controller.actions.nbt.MsgPlayerNbtAction;
import io.neris.NGui.core.gui.element.services.installers.BaseGuiElementInstaller;
import io.neris.NGui.core.gui.element.services.nbtAction.NbtActionExecutor;
import io.neris.NGui.core.gui.element.services.nbtAction.NbtActionRegistry;
import io.neris.NGui.core.gui.element.services.installers.SlotBindingGuiElementInstaller;
import io.neris.NGui.core.gui.element.services.slotBinding.GuiElementInventoryIndex;
import io.neris.NGui.core.gui.element.services.slotBinding.GuiElementBindingSystem;
import io.neris.NGui.core.gui.element.services.slotBinding.GuiElementRegistry;
import io.neris.NGui.core.services.nbtTagger.GuiElementTagger;
import io.neris.NGui.core.services.nbtTagger.NBTTagger;
import io.neris.NGui.core.utils.gui.action.NBTActionUtils;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class ServiceController {

    @Getter private final JavaPlugin plugin;

    @Getter private final NBTTagger nbtTagger;
    @Getter private final GuiElementTagger guiElementTagger;

    @Getter private final GuiElementInventoryIndex elementPosRegistry;
    @Getter private final GuiElementRegistry uuidElementRegistry;
    @Getter private final GuiElementBindingSystem elementRegistry;
    @Getter private final SlotBindingGuiElementInstaller slotElementInstaller;
    @Getter private final BaseGuiElementInstaller baseElementInstaller;

    @Getter private final NbtActionRegistry nbtActionRegistry;
    @Getter private final NbtActionExecutor nbtActionExecutor;

    public ServiceController(JavaPlugin plugin) {
        this.plugin = plugin;

        this.nbtTagger = new NBTTagger(plugin);
        this.guiElementTagger = new GuiElementTagger(nbtTagger);

        this.elementPosRegistry = new GuiElementInventoryIndex();
        this.uuidElementRegistry = new GuiElementRegistry();
        this.elementRegistry = new GuiElementBindingSystem(elementPosRegistry, uuidElementRegistry);
        this.slotElementInstaller = new SlotBindingGuiElementInstaller(elementRegistry);
        this.baseElementInstaller = new BaseGuiElementInstaller();

        this.nbtActionRegistry = new NbtActionRegistry();
        this.nbtActionExecutor = new NbtActionExecutor(nbtActionRegistry);

        initNbtAction();
        NBTActionUtils.init(nbtTagger);
    }


    private void initNbtAction(){
        nbtActionRegistry.register(new CancelPutNbtAction(), new MsgPlayerNbtAction(nbtTagger));
    }
}
