package io.neris.NGui.core.gui.element.controller.actions.nbt;

import io.neris.NGui.core.gui.element.EventContext;
import io.neris.NGui.core.gui.element.MainItemEventContext;
import io.neris.NGui.core.gui.element.controller.actions.NbtAction;
import io.neris.NGui.core.gui.element.controller.actions.NbtActionKeys;
import io.neris.NGui.core.services.nbtTagger.NBTTagger;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.neris.NGui.core.utils.itemStack.ItemStackUtils.parseToOptional;

public class MsgPlayerNbtAction implements NbtAction {

    @Getter @Setter private String message;

    private final NBTTagger nbtTagger;


    public MsgPlayerNbtAction(NBTTagger nbtTagger){
        this.nbtTagger = nbtTagger;
    }

    public MsgPlayerNbtAction(NBTTagger nbtTagger, String message){
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
    public void execute(EventContext context) {
        if(!isCorrectEvent(context.event)){
            return;
        }

        HumanEntity entity = context.whoClicked;

        Optional<ItemStack> optItemStack = extractItemStack(context);
        if(entity == null || optItemStack.isEmpty()){
            return;
        }

        ItemStack itemStack = optItemStack.get();
        String value = nbtTagger.getValueStringNBT(itemStack ,key());
        if(value == null){
            return;
        }

        String message = extractMessage(value);
        Component componentMessage = Component.text(message);

        entity.sendMessage(componentMessage);

    }

    private boolean isCorrectEvent(Event event){
        return event instanceof PlayerInteractEvent;
    }

    private Optional<ItemStack> extractItemStack(EventContext context){
        if(context instanceof MainItemEventContext mainItemEventContext){
            ItemStack itemStack = mainItemEventContext.getMainItem();
            return parseToOptional(itemStack);
        }

        ItemStack itemStack = context.items.getFirst();
        return Optional.ofNullable(itemStack);
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
