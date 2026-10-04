package io.neris.NGui.core.utils.gui.action;

import io.neris.NGui.core.services.nbtTagger.NBTTagger;
import io.neris.NGui.core.utils.nbt.NBTUtils;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class NBTActionUtils {

    @Getter private static NBTTagger tagger;


    public static void init(NBTTagger nbtTagger){
        tagger = nbtTagger;
    }


    public static boolean hasActionTrue(ItemStack itemStack, String actionKey){
        return hasAction(itemStack, actionKey) && isActionTrue(itemStack, actionKey);
    }

    public static boolean hasAction(ItemStack itemStack, String actionKey){
        return NBTUtils.hasKey(itemStack, actionKey);
    }

    public static boolean isActionTrue(ItemStack itemStack, String actionKey){
        String value = tagger.getValueStringNBT(itemStack, actionKey);
        if(value.isEmpty()){
            return false;
        }

        String[] values = value.split(",");
        return List.of(values).contains("true");
    }


    public static void setBool(ItemStack itemStack, String actionKey, boolean bool){
        String value = tagger.getValueStringNBT(itemStack, actionKey);
        String newValue = changeStringBool(value, bool);
        tagger.addStringNBT(itemStack, actionKey, newValue);
    }

    private static String changeStringBool(String st, boolean bool){
        if(bool){
            return st.replace(",false", ",true");
        } else {
            return st.replace(",true", ",false");
        }
    }
}
