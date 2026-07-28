package io.neris.NGui.core.services.nbtTagger;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class GuiElementTagger {

    private final NBTTagger nbtTagger;

    public GuiElementTagger(NBTTagger nbtTagger) {
        this.nbtTagger = nbtTagger;
    }

    public void addUUIDTag(ItemStack itemStack, UUID uuid){
        String value = uuid.toString();
        nbtTagger.addStringNBT(itemStack, NBTKeys.GUI_ELEMENT, value);
    }
}
