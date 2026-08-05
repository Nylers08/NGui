package io.neris.NGui.core.gui.element.element;

import io.neris.NGui.core.gui.element.EventContext;
import io.neris.NGui.core.gui.element.controller.renderers.GuiElementRenderContext;
import io.neris.NGui.core.gui.element.controller.GuiElementController;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class BaseGuiElement implements GuiElement {

    protected final UUID uuid;
    protected ItemStack itemStack;

    protected final GuiElementController controller;

    public BaseGuiElement(GuiElementRenderContext renderContext, GuiElementController controller){
        this.uuid = UUID.randomUUID();

        this.controller = controller;

        render(renderContext);
    }


    @Override
    public void click(InventoryClickEvent event) {
        EventContext clickContext = buildClickContext(event);
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


    private EventContext buildClickContext(InventoryClickEvent event){
        return new EventContext(event);
    }
}
