package org.example.Service.Handlers;

import org.example.Model.DbManager;
import org.example.Model.Entities.Film;
import org.example.Statemachine.TransmittedData;
import org.example.Util.DialogStringsStorage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import static org.example.Statemachine.State.WaitingInputStartFromMenu;

public class MainMenuSearchFilm {

    public SendMessage processClickOnInlineButtonInMenuFindFilm(String receivedText, TransmittedData transmittedData) throws Exception{

        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());


        Film film = DbManager.getInstance().getTableFilms().getFilmByName(receivedText);
        transmittedData.getDataStorage().add("film",film);

        if(film == null){
            message.setText(DialogStringsStorage.CommandNotFindFilm);
            //transmittedData.setState(WaitingInputStartFromMenu);
            return message;
        }else {
            message.setText(DialogStringsStorage.CommandYesFindFilm + "\n" + film.getName() + "\n" + film.getLinkFilm());
            transmittedData.setState(WaitingInputStartFromMenu);
            return message;
        }

    }
}
