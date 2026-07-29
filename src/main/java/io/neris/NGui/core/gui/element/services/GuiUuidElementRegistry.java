package io.neris.NGui.core.gui.element.services;

import io.neris.NGui.core.gui.element.view.GuiElement;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GuiUuidElementRegistry {

    @Getter private final Map<UUID, GuiElement> registry = new HashMap<>();


    public void register(@NotNull GuiElement guiElement){
        registry.put(guiElement.uuid(), guiElement);
    }

    public void unregister(@NotNull UUID uuid) {
        registry.remove(uuid);
    }

    public void unregister(@NotNull GuiElement element){
        registry.remove(element.uuid());
    }


    public GuiElement get(UUID uuid){
        return registry.get(uuid);
    }


    public boolean containsUUID(UUID uuid){
        return registry.containsKey(uuid);
    }

}
