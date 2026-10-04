package io.neris.NGui.core.example;

import io.neris.NGui.core.gui.button.button.StaticNbtButton;
import io.neris.NGui.core.gui.button.controller.ButtonController;
import io.neris.NGui.core.gui.button.controller.actions.ButtonAction;
import io.neris.NGui.core.gui.button.controller.actions.CancelPutAction;
import io.neris.NGui.core.gui.button.controller.renderers.ButtonRenderContext;
import io.neris.NGui.core.gui.button.controller.renderers.ButtonRenderer;
import io.neris.NGui.core.gui.button.controller.renderers.PlayerNicknameRenderer;
import org.bukkit.Material;

import java.util.List;
import java.util.UUID;

public class TestButton extends StaticNbtButton {

    public TestButton(UUID uuid){
        List<ButtonAction> actions = List.of(
                new CancelPutAction()
        );
        ButtonRenderer renderer = new PlayerNicknameRenderer(Material.DIAMOND, 1);

        ButtonController buttonController = new ButtonController(renderer, actions);
        ButtonRenderContext context = new ButtonRenderContext(uuid);
        super(buttonController, context);
    }


}
