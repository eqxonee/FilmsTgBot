package org.example.Service;

import org.example.Service.Handlers.MainMenuService;
import org.example.Service.Handlers.StaticService;
import org.example.Statemachine.State;
import org.example.Statemachine.TransmittedData;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.HashMap;
import java.util.Map;

public class ServiceManager {

    private Map<State,Service> methods;
    private MainMenuService mainMenuService;
    private StaticService staticService;

    public ServiceManager() {
        methods = new HashMap<>();
        mainMenuService = new MainMenuService();
        staticService = new StaticService();

        methods.put(State.WaitingCommandStart,mainMenuService::processCommandStart);
        methods.put(State.WaitingClickOnInlineButtonInMenuMain,mainMenuService::processClickOnInlineButtonInMenuMain);
        methods.put(State.WaitingClickOnInlineButtonInMenuChooseFilm,mainMenuService::processClickOnInlineButtonStylesFilms);
        methods.put(State.WaitingInputStartFromMenu,mainMenuService::processCommandStart);

    }

    public SendMessage processUpdate(String textData, TransmittedData transmittedData) throws Exception {
       return methods.get(transmittedData.getState()).processUpdate(textData, transmittedData);
    }

    public StaticService getStaticService() {
        return staticService;
    }
}
