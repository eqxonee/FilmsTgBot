package org.example.Statemachine;

public enum State {
    WaitingCommandStart,
    WaitingClickOnInlineButtonInMenuMain,
    WaitingClickOnInlineButtonInMenuChooseFilm,
    WaitingInputStartFromMenu,
    WaitingClickOnInlineButtonInMenuAddFilm,
    WaitingClickOnInlineButtonInMenuDeleteFilm,
    WaitingClickOnInlineButtonInMenuChooseFromDeleteFilm,
    WaitingClickOnInlineButtonInMenuFindFilm,

    NameFilm,
    ReleaseFilm,
    DurationFilm,
    LinkFilm,
    FilmStyle;



}
