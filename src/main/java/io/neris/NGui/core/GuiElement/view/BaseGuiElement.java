package io.neris.NGui.core.GuiElement.view;

import io.neris.NGui.core.GuiElement.GuiElementClickContext;
import io.neris.NGui.core.GuiElement.controller.GuiElementRenderContext;
import io.neris.NGui.core.services.nbtTagger.GuiElementTagger;
import io.neris.NGui.core.GuiElement.controller.GuiElementController;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class BaseGuiElement implements GuiElement {

    private final UUID uuid;
    private ItemStack itemStack;

    private final GuiElementController controller;
    private final GuiElementTagger nbtTagger;

    public BaseGuiElement(GuiElementController controller, GuiElementTagger nbtTagger){
        this.uuid = UUID.randomUUID();
        this.nbtTagger = nbtTagger;

        this.controller = controller;
    }


    @Override
    public void click(InventoryClickEvent event) {
        GuiElementClickContext clickContext = buildClickContext(event);
        controller.execute(clickContext);
    }

    @Override
    public void render(GuiElementRenderContext context) {
        itemStack = controller.render(context);
        nbtTagger.addUUIDTag(itemStack, uuid);
    }


    @Override
    public ItemStack getItem() {
        return itemStack;
    }

    @Override
    public UUID uuid() {
        return uuid;
    }


    private GuiElementClickContext buildClickContext(InventoryClickEvent event){
        return new GuiElementClickContext(event);
    }
}
