package io.neris.NGui.core.gui.button.controller.renderers;

import io.neris.NGui.core.utils.itemStack.ItemStackUtils;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class SimpleRenderer implements ButtonRenderer{

    @Getter @Setter
    private Material material;

    @Getter @Setter
    private Component name;

    @Getter @Setter
    private int amount;

    public SimpleRenderer(Material material, int amount, Component name){
        this.material = material;
        this.name = name;
        this.amount = amount;
    }

    @Override
    public ItemStack render(ButtonRenderContext context) {
        amountCorrector();
        ItemStack itemStack = new ItemStack(material, amount);
        ItemStackUtils.rename(itemStack, name);
        return itemStack;
    }

    private void amountCorrector(){
        if(amount < 0){
            amount = 0;
        } else if(amount>64){
            amount = 64;
        }
    }
}
