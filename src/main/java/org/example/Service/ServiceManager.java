package org.example.Service;

import org.example.Service.Handlers.MainMenuService;
import org.example.Statemachine.State;
import org.example.Statemachine.TransmittedData;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.HashMap;
import java.util.Map;

public class ServiceManager {

    private Map<State,Service> methods;
    private MainMenuService mainMenuService;

    public ServiceManager() {
        methods = new HashMap<>();
        mainMenuService = new MainMenuService();

        methods.put(State.WaitingCommandStart,mainMenuService::processCommandStart);
    }

    public SendMessage processUpdate(String textData, TransmittedData transmittedData) {
       return methods.get(transmittedData.getState()).processUpdate(textData, transmittedData);
    }
}
