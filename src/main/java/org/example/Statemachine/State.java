package org.example.Statemachine;

public enum State {
    WaitingCommandStart,
    WaitingClickOnInlineButtonInMenuMain,
    WaitingClickOnInlineButtonInMenuChooseFilm,
    WaitingInputStartFromMenu,
    WaitingClickOnInlineButtonInMenuDeleteFilm,
    WaitingClickOnInlineButtonInMenuChooseFromDeleteFilm,
    WaitingClickOnInlineButtonInMenuChooseFromDeleteFilmSuccess,
    WaitingClickOnInlineButtonInMenuFindFilm,

    NameFilm,
    ReleaseFilm,
    DurationFilm,
    LinkFilm,
    FilmStyle;



}
