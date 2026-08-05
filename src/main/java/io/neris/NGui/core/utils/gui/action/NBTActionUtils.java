package io.neris.NGui.core.utils.gui.action;

import io.neris.NGui.core.gui.element.controller.actions.NbtActionKeys;
import io.neris.NGui.core.services.nbtTagger.NBTTagger;
import io.neris.NGui.core.utils.nbt.NBTUtils;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class NBTActionUtils {

    private static NBTTagger tagger;


    public static void init(NBTTagger nbtTagger){
        tagger = nbtTagger;
    }


    public static boolean hasActionTrue(ItemStack itemStack, String actionKey){
        return hasAction(itemStack, actionKey) && isActionTrue(itemStack, actionKey);
    }

    public static boolean hasAction(ItemStack itemStack, String actionKey){
        return NBTUtils.hasKey(itemStack, actionKey);
    }

    private static boolean isActionTrue(ItemStack itemStack, String actionKey){
        String value = tagger.getValueStringNBT(itemStack, actionKey);
        if(value.isEmpty()){
            return false;
        }

        String[] values = value.split(",");
        return List.of(values).contains("true");
    }
}
