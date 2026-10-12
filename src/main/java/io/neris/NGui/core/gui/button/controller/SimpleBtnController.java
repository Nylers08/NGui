package io.neris.NGui.core.gui.button.controller;

import io.neris.NGui.core.gui.button.controller.actions.ButtonAction;
import io.neris.NGui.core.gui.button.controller.renderers.ButtonRenderer;
import io.neris.NGui.core.gui.button.controller.renderers.SimpleRenderer;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class SimpleBtnController extends ButtonController{

    public SimpleBtnController(Material material, int amount, Component name, ButtonAction... actions) {
        ButtonRenderer renderer = new SimpleRenderer(material, amount, name);
        List<ButtonAction> listActions = new ArrayList<>(List.of(actions));
        super(renderer, listActions);
    }
}
