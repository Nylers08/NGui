package io.neris.NGui.core.gui.button.services.nbtAction;

import io.neris.NGui.core.gui.button.controller.actions.ButtonAction;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class NbtActionRegistry {

    private final Map<String, ButtonAction> actionMap = new HashMap<>();


    public void register(@NotNull ButtonAction... actions){
        for (ButtonAction action : actions){
            register(action);
        }
    }

    public void register(@NotNull ButtonAction action){
        register(action.key(), action);
    }

    public void register(@NotNull String key, @NotNull ButtonAction action){
        actionMap.put(key, action);
    }


    public void unregister(@NotNull String... keys){
        for (String key : keys){
            unregister(key);
        }
    }

    public void unregister(@NotNull String key){
        actionMap.remove(key);
    }


    public @Nullable ButtonAction getAction(@NotNull String key){
        return actionMap.get(key);
    }
}
