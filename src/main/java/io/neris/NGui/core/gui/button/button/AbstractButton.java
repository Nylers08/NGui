package io.neris.NGui.core.gui.button.button;

import io.neris.NGui.core.gui.button.controller.renderers.ButtonRenderContext;
import io.neris.NGui.core.gui.button.controller.ButtonController;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public abstract class AbstractButton implements Button {

    protected final UUID uuid;
    protected ItemStack itemStack;

    protected final ButtonController controller;

    public AbstractButton(ButtonRenderContext renderContext, ButtonController controller){
        this.uuid = UUID.randomUUID();
        this.controller = controller;

        render(renderContext);
    }


    @Override
    public void render(ButtonRenderContext context) {
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

}
