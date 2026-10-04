package io.neris.NGui.core.gui.button.controller.actions;

import io.neris.NGui.core.gui.button.ClickEventContext;
import io.neris.NGui.core.gui.button.MainItemEventContext;
import io.neris.NGui.core.services.nbtTagger.NBTTagger;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.neris.NGui.core.gui.button.ButtonUtils.extractMainItemStack;
import static io.neris.NGui.core.utils.itemStack.ItemStackUtils.parseToOptional;

public class MsgPlayerAction implements ButtonAction {

    @Getter @Setter private String message;

    private final NBTTagger nbtTagger;


    public MsgPlayerAction(NBTTagger nbtTagger){
        this.nbtTagger = nbtTagger;
    }

    public MsgPlayerAction(NBTTagger nbtTagger, String message){
        this.message = message;
        this.nbtTagger = nbtTagger;
    }


    @Override
    public String key() {
        return NbtActionKeys.PLAYER_MESSAGE;
    }

    @Override
    public String value() {
        return "msg:" + message + ",true";
    }


    @Override
    public void execute(ClickEventContext context) {
        HumanEntity entity = context.whoClicked;

        Optional<ItemStack> optItemStack = extractMainItemStack(context);
        if(entity == null || optItemStack.isEmpty()){
            return;
        }

        ItemStack itemStack = optItemStack.get();
        String value = nbtTagger.getValueStringNBT(itemStack ,key());
        if(value == null || value.contains(",false")){
            return;
        }

        String message = extractMessage(value);
        Component componentMessage = Component.text(message);

        entity.sendMessage(componentMessage);

    }

    private String extractMessage(@NotNull String value){
        Pattern pattern = Pattern.compile("msg:(.*?),");
        Matcher matcher = pattern.matcher(value);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return "";
    }
}
