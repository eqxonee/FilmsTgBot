package org.example.Service.menupoints;

import org.example.Model.DbManager;
import org.example.Model.Entities.Film;
import org.example.Model.Entities.StyleFilm;
import org.example.Statemachine.TransmittedData;
import org.example.Util.ButtonsStorage;
import org.example.Util.DialogStringsStorage;
import org.example.Util.InlineKeyboardsMarkupStorage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import static org.example.Statemachine.State.*;
import static org.example.Statemachine.State.WaitingInputStartFromMenu;

public class MainMenuDeleteFilm {

    private DbManager dbManager;

    public MainMenuDeleteFilm() {
        this.dbManager = dbManager;
    }

    public SendMessage processClickOnInlineButtonInMenuDeleteFilm(String receivedText, TransmittedData transmittedData) throws Exception{

        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());

        StyleFilm styleFilm = dbManager.getTableStyleFilms().getByName(receivedText);
        if(styleFilm == null){
            message.setText(DialogStringsStorage.CommandGenreFilm);
            return message;
        }else {
            transmittedData.getDataStorage().add("styleFilm",styleFilm);
            message.setText(DialogStringsStorage.CommandGenreFilmYes);
            transmittedData.setState(WaitingClickOnInlineButtonInMenuChooseFromDeleteFilm);
            return message;
        }

    }

    public SendMessage processClickOnInlineButtonInMenuChooseFromDeleteFilm(String receivedText, TransmittedData transmittedData) throws Exception{
        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());
        //сделать поиск фильма по названию
        //если фильм не найден = нулл
        //если найден - перевести на след стейт , сохранить фильм в дата сторадж и вывести клаву да нет
        Film film = dbManager.getTableFilms().getFilmByName(receivedText);
        if(film == null){
            message.setText(DialogStringsStorage.CommandFindFilm);
            transmittedData.setState(WaitingInputStartFromMenu);
            return message;
        }else {
            transmittedData.getDataStorage().add("film",film);
            message.setText(DialogStringsStorage.CommandDeleteFilmAnswer);
            message.setReplyMarkup(InlineKeyboardsMarkupStorage.GetInlineKeyboardMarkupMenuMainDeleteFilm());
            transmittedData.setState(WaitingClickOnInlineButtonInMenuChooseFromDeleteFilmSuccess);
            return message;
        }

    }
    public SendMessage processClickOnInlineButtonInMenuChooseFromDeleteFilmSuccess(String callBackData, TransmittedData transmittedData) throws Exception{
        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());

        Film film = (Film) transmittedData.getDataStorage().get("film");

        if (callBackData.equals(ButtonsStorage.ButtonDeleteFilmFromMenuMainYes.getCallBackData())){
            dbManager.getTableFilms().deleteByFilmName(film.getName());
            message.setText(DialogStringsStorage.CommandDeleteFilmSuccess);
            transmittedData.setState(WaitingInputStartFromMenu);
            return message;
        }else {
            message.setText(DialogStringsStorage.CommandFilmCancel);
            transmittedData.setState(WaitingInputStartFromMenu);
            return message;
        }


    }

}
