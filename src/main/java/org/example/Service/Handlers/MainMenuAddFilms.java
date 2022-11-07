package org.example.Service.Handlers;

import org.example.Model.DbManager;
import org.example.Model.Entities.Film;
import org.example.Model.Entities.StyleFilm;
import org.example.Statemachine.TransmittedData;
import org.example.Util.DialogStringsStorage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

import static org.example.Statemachine.State.*;

public class MainMenuAddFilms {

    public SendMessage processNameFilm(String receivedText, TransmittedData transmittedData) throws Exception{
        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());
        message.setText(DialogStringsStorage.CommandReleaseFilm);
        transmittedData.getDataStorage().add(String.valueOf(transmittedData.getChatId()),new Film(receivedText,0,"",0));

        transmittedData.setState(ReleaseFilm);

        return message;
    }

    public SendMessage processReleaseFilm(String receivedText, TransmittedData transmittedData) throws Exception {
        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());
        message.setText(DialogStringsStorage.CommandDurationFilm);
        Film film = (Film) transmittedData.getDataStorage().get(String.valueOf(transmittedData.getChatId()));

        film.setReleaseFilm(Integer.parseInt(receivedText));
        transmittedData.getDataStorage().add(String.valueOf(transmittedData.getChatId()),film);
        transmittedData.setState(DurationFilm);

        return message;
    }

    public SendMessage processDurationFilm(String receivedText, TransmittedData transmittedData) throws Exception {
        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());

        StringBuilder stringBuilder = new StringBuilder();
        List<StyleFilm> filmList = DbManager.getInstance().getTableStyleFilms().getAll();
        for (int i = 0; i < filmList.size(); i++) {
            int id = filmList.get(i).getId();
            String style = filmList.get(i).getStyleFilm();
            stringBuilder.append(id).append(". ").append(style).append("\n");
        }


        message.setText(stringBuilder + DialogStringsStorage.CommandStyleFilm);
        Film film = (Film) transmittedData.getDataStorage().get(String.valueOf(transmittedData.getChatId()));

        film.setTimeLength(Integer.parseInt(receivedText));
        transmittedData.getDataStorage().add(String.valueOf(transmittedData.getChatId()), film);
        transmittedData.setState(FilmStyle);

        return message;
    }
    public SendMessage processStyleFilm(String receivedText, TransmittedData transmittedData) throws Exception {
        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());
        message.setText(DialogStringsStorage.CommandLinkFilm);
        Film film = (Film) transmittedData.getDataStorage().get(String.valueOf(transmittedData.getChatId()));

        film.setStyleFilmToId(Integer.parseInt(receivedText));
        transmittedData.getDataStorage().add(String.valueOf(transmittedData.getChatId()), film);
        transmittedData.setState(LinkFilm);

        return message;
    }

    public SendMessage processLinkFilm(String receivedText, TransmittedData transmittedData) throws Exception {
        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());
        message.setText(DialogStringsStorage.CommandEndFilm);
        Film film = (Film) transmittedData.getDataStorage().get(String.valueOf(transmittedData.getChatId()));

        film.setLinkFilm(receivedText);
        transmittedData.getDataStorage().add(String.valueOf(transmittedData.getChatId()), film);
        Film filmEnd = (Film) transmittedData.getDataStorage().get(String.valueOf(transmittedData.getChatId()));
        DbManager.getInstance().getTableFilms().addNew(filmEnd);
        transmittedData.setState(WaitingInputStartFromMenu);

        return message;
    }
}
