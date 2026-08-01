package io.neris.NGui.core.gui.element.view;

import io.neris.NGui.core.gui.element.GuiElementClickContext;
import io.neris.NGui.core.gui.element.controller.renderers.GuiElementRenderContext;
import io.neris.NGui.core.gui.element.controller.GuiElementController;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class BaseGuiElement implements GuiElement {

    private final UUID uuid;
    private ItemStack itemStack;

    private final GuiElementController controller;

    public BaseGuiElement(GuiElementRenderContext renderContext, GuiElementController controller){
        this.uuid = UUID.randomUUID();

        this.controller = controller;

        render(renderContext);
    }


    @Override
    public void click(InventoryClickEvent event) {
        GuiElementClickContext clickContext = buildClickContext(event);
        controller.execute(clickContext);
    }

    @Override
    public void render(GuiElementRenderContext context) {
        itemStack = controller.render(context);
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
