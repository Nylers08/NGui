package io.neris.NGui.core.gui.button.controller.actions;

import io.neris.NGui.core.gui.button.ClickEventContext;
import io.neris.NGui.core.utils.gui.action.NBTActionUtils;
import org.bukkit.event.Cancellable;

public class CancelPutAction implements ButtonAction {

    @Override
    public String key() {
        return NbtActionKeys.CANCEL_PUT;
    }

    @Override
    public String value() {
        return ",true";
    }

    @Override
    public void execute(ClickEventContext clickContext) {
        if(!isActionTrue(clickContext)){
            return;
        }

        if(clickContext.event instanceof Cancellable cancellable){
            cancellable.setCancelled(true);
        }
    }

    private boolean isActionTrue(ClickEventContext clickContext){
        return NBTActionUtils.isActionTrue(clickContext.items.getFirst(), key());
    }
}
