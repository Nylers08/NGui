package io.neris.NGui.core.gui.button.factory;

import io.neris.NGui.core.gui.button.controller.renderers.ButtonRenderContext;
import io.neris.NGui.core.gui.button.controller.renderers.ButtonRenderer;
import io.neris.NGui.core.gui.menu.Menu;

public record ButtonToMenuContext(
        ButtonRenderer renderer,
        ButtonRenderContext renderContext,
        Menu menu
) {
}
