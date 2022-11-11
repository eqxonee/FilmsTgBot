package org.example.Service;

import org.example.Service.Handlers.*;
import org.example.Statemachine.State;
import org.example.Statemachine.TransmittedData;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.HashMap;
import java.util.Map;

public class ServiceManager {

    private Map<State,Service> methods;
    private MainMenuService mainMenuService;
    private MainMenuListFilms mainMenuListFilms;
    private MainMenuAddFilm mainMenuAddFilms;
    private MainMenuDeleteFilm mainMenuDeleteFilms;
    private MainMenuSearchFilm mainMenuSearchFilm;

    private StaticService staticService;

    public ServiceManager() {
        methods = new HashMap<>();
        mainMenuService = new MainMenuService();
        mainMenuListFilms = new MainMenuListFilms();
        mainMenuAddFilms = new MainMenuAddFilm();
        mainMenuDeleteFilms = new MainMenuDeleteFilm();
        mainMenuSearchFilm = new MainMenuSearchFilm();
        staticService = new StaticService();

        methods.put(State.WaitingCommandStart,mainMenuService::processCommandStart);
        methods.put(State.WaitingClickOnInlineButtonInMenuMain,mainMenuService::processClickOnInlineButtonInMenuMain);
        methods.put(State.WaitingClickOnInlineButtonInMenuChooseFilm,mainMenuListFilms::processClickOnInlineButtonStylesFilms);
        methods.put(State.WaitingInputStartFromMenu,mainMenuService::processCommandStart);

        methods.put(State.NameFilm,mainMenuAddFilms::processNameFilm);
        methods.put(State.ReleaseFilm,mainMenuAddFilms::processReleaseFilm);
        methods.put(State.DurationFilm,mainMenuAddFilms::processDurationFilm);
        methods.put(State.FilmStyle,mainMenuAddFilms::processStyleFilm);
        methods.put(State.LinkFilm,mainMenuAddFilms::processLinkFilm);

        methods.put(State.WaitingClickOnInlineButtonInMenuDeleteFilm,mainMenuDeleteFilms::processClickOnInlineButtonInMenuDeleteFilm);
        methods.put(State.WaitingClickOnInlineButtonInMenuChooseFromDeleteFilm,mainMenuDeleteFilms::processClickOnInlineButtonInMenuChooseFromDeleteFilm);
        methods.put(State.WaitingClickOnInlineButtonInMenuChooseFromDeleteFilmSuccess,mainMenuDeleteFilms::processClickOnInlineButtonInMenuChooseFromDeleteFilmSuccess);

        methods.put(State.WaitingClickOnInlineButtonInMenuFindFilm,mainMenuSearchFilm::processClickOnInlineButtonInMenuFindFilm);

    }

    public SendMessage processUpdate(String textData, TransmittedData transmittedData) throws Exception {
       return methods.get(transmittedData.getState()).processUpdate(textData, transmittedData);
    }

    public StaticService getStaticService() {
        return staticService;
    }
}
