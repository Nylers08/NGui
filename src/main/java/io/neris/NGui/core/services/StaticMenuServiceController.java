package io.neris.NGui.core.services;

import lombok.Getter;

public class StaticMenuServiceController {

    @Getter
    private static MenuServiceController serviceController;


    public static void init(MenuServiceController _serviceController){
        serviceController = _serviceController;
    }

}
