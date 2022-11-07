package org.example.Service.Handlers;

import org.example.Model.DbManager;
import org.example.Model.Entities.Film;
import org.example.Statemachine.TransmittedData;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import static org.example.Statemachine.State.WaitingInputStartFromMenu;

public class MainMenuSearchFilm {

    public SendMessage processClickOnInlineButtonInMenuFindFilm(String receivedText, TransmittedData transmittedData) throws Exception{

        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());


        Film film = DbManager.getInstance().getTableFilms().getByFilmByName(receivedText);
        transmittedData.getDataStorage().add("film",film);
        //String linkFilm = DbManager.getInstance().getTableFilms().getLinkFilm(film.getLinkFilm());
        if(film == null){
            message.setText("Такого фильма нет в базе данных");
            transmittedData.setState(WaitingInputStartFromMenu);
            return message;
        }else {
            //transmittedData.getDataStorage().add("filmSearch",film);
            //transmittedData.getDataStorage().get("filmSearch");
            //transmittedData.getDataStorage().add("linkFilm",linkFilm);
            //Film linkFilm = (Film) transmittedData.getDataStorage().get("linkFilm");
            message.setText("Ваш фильм" + "\n" + film.getName() + "\n" + film.getLinkFilm());
            transmittedData.setState(WaitingInputStartFromMenu);
            return message;
        }

    }
}
