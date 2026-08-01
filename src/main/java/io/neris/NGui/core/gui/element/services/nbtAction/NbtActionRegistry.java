package io.neris.NGui.core.gui.element.services.nbtAction;

import io.neris.NGui.core.gui.element.controller.actions.GUIElementAction;
import io.neris.NGui.core.gui.element.controller.actions.NbtAction;
import io.neris.NGui.core.gui.element.view.GuiElement;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class NbtActionRegistry {

    private final Map<String, GUIElementAction> actionMap = new HashMap<>();


    public void register(@NotNull NbtAction... actions){
        for (NbtAction action : actions){
            register(action);
        }
    }

    public void register(@NotNull NbtAction action){
        register(action.key(), action);
    }

    public void register(@NotNull String key, @NotNull GUIElementAction action){
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


    private @Nullable GUIElementAction getAction(@NotNull String key){
        return actionMap.get(key);
    }
}
