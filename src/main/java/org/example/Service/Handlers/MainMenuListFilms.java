package org.example.Service.Handlers;

import org.example.Model.DbManager;
import org.example.Model.Entities.Film;
import org.example.Statemachine.TransmittedData;
import org.example.Util.ButtonsStorage;
import org.example.Util.DialogStringsStorage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

import static org.example.Statemachine.State.WaitingInputStartFromMenu;

public class MainMenuListFilms {

    public SendMessage processClickOnInlineButtonStylesFilms(String callBackData, TransmittedData transmittedData) throws Exception {
        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());
        if (callBackData.equals(ButtonsStorage.ButtonStylesFilmsFromMenuMainHorrors.getCallBackData())) {
            List<Film> filmList = DbManager.getInstance().getTableFilms().getAllHorror();
            StringBuilder stringBuilder = new StringBuilder();

            for (Film film : filmList) {
                stringBuilder.append(film.toString() + "\n");
            }

            String filmsAsText = stringBuilder.toString();

            message.setText(DialogStringsStorage.CommandStyleFilmsHorror + "\n" + filmsAsText);
            transmittedData.setState(WaitingInputStartFromMenu);

            return message;

        } else if (callBackData.equals(ButtonsStorage.ButtonStylesFilmsFromMenuMainMystic.getCallBackData())) {
            if (callBackData.equals(ButtonsStorage.ButtonStylesFilmsFromMenuMainMystic.getCallBackData())) {
                List<Film> filmList = DbManager.getInstance().getTableFilms().getAllMystic();
                StringBuilder stringBuilder = new StringBuilder();

                for (Film film : filmList) {
                    stringBuilder.append(film.toString() + "\n");
                }

                String filmsAsText = stringBuilder.toString();

                message.setText(DialogStringsStorage.CommandStyleFilmsMystic + "\n" + filmsAsText);
                transmittedData.setState(WaitingInputStartFromMenu);
                return message;
            }

        } else if (callBackData.equals(ButtonsStorage.ButtonStylesFilmsFromMenuMainHistory.getCallBackData())) {
            if (callBackData.equals(ButtonsStorage.ButtonStylesFilmsFromMenuMainHistory.getCallBackData())) {
                List<Film> filmList = DbManager.getInstance().getTableFilms().getAllHistory();
                StringBuilder stringBuilder = new StringBuilder();

                for (Film film : filmList) {
                    stringBuilder.append(film.toString() + "\n");
                }

                String filmsAsText = stringBuilder.toString();

                message.setText(DialogStringsStorage.CommandStyleFilmsHistory + "\n" + filmsAsText);
                transmittedData.setState(WaitingInputStartFromMenu);
                return message;
            }
        } else if (callBackData.equals(ButtonsStorage.ButtonStylesFilmsFromMenuMainComedy.getCallBackData())) {
            if (callBackData.equals(ButtonsStorage.ButtonStylesFilmsFromMenuMainComedy.getCallBackData())) {
                List<Film> filmList = DbManager.getInstance().getTableFilms().getAllComedy();
                StringBuilder stringBuilder = new StringBuilder();

                for (Film film : filmList) {
                    stringBuilder.append(film.toString() + "\n");
                }

                String filmsAsText = stringBuilder.toString();

                message.setText(DialogStringsStorage.CommandStyleFilmsComedy + "\n" + filmsAsText);
                transmittedData.setState(WaitingInputStartFromMenu);
                return message;
            }
        } else if (callBackData.equals(ButtonsStorage.ButtonStylesFilmsFromMenuMainMilitary.getCallBackData())) {
            if (callBackData.equals(ButtonsStorage.ButtonStylesFilmsFromMenuMainMilitary.getCallBackData())) {
                List<Film> filmList = DbManager.getInstance().getTableFilms().getAllMilitary();
                StringBuilder stringBuilder = new StringBuilder();

                for (Film film : filmList) {
                    stringBuilder.append(film.toString() + "\n");
                }

                String filmsAsText = stringBuilder.toString();

                message.setText(DialogStringsStorage.CommandStyleFilmsMilitary + "\n" + filmsAsText);
                transmittedData.setState(WaitingInputStartFromMenu);
                return message;
            }
        }

        throw new Exception("Ввод говно");
    }
}
