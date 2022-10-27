package org.example.Service.Handlers;


import org.example.Model.DbManager;
import org.example.Model.Entities.Film;
import org.example.Model.Tables.TableFilms;
import org.example.Statemachine.TransmittedData;
import org.example.Util.ButtonsStorage;
import org.example.Util.DialogStringsStorage;
import org.example.Util.InlineKeyboardsMarkupStorage;
import org.example.Util.SystemStringsStorage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;


import java.util.List;

import static org.example.Statemachine.State.*;


public class MainMenuService {
    private TableFilms tableFilms;

    public SendMessage processCommandStart(String command, TransmittedData transmittedData) {

        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());

        if (!command.equals(SystemStringsStorage.CommandStart)) {
            message.setText(DialogStringsStorage.CommandStartError);
            return message;
        }

        transmittedData.setState(WaitingClickOnInlineButtonInMenuMain);

        message.setText(DialogStringsStorage.CommandStartOK);
        message.setReplyMarkup(InlineKeyboardsMarkupStorage.GetInlineKeyboardMarkupMenuMain());

        return message;

    }

    public SendMessage processClickOnInlineButtonInMenuMain(String callBackData, TransmittedData transmittedData) throws Exception {

        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());

        if (callBackData.equals(ButtonsStorage.ButtonAllFilmsInMenuMain.getCallBackData())) {
            message.setText(DialogStringsStorage.CommandMenuStyleFilms);
            message.setReplyMarkup(InlineKeyboardsMarkupStorage.GetInlineKeyboardMarkupMenuMainStylesFilm());

            transmittedData.setState(WaitingClickOnInlineButtonInMenuChooseFilm);

            return message;

        } else if (callBackData.equals(ButtonsStorage.ButtonAddFilmsInMenuMain.getCallBackData())) {
            message.setText("Вы нажали добавить фильм");
            return message;
        } else if (callBackData.equals(ButtonsStorage.ButtonDeleteFilmsInMenuMain.getCallBackData())) {
            message.setText("Вы нажали удалить фильм");
            return message;
        } else if (callBackData.equals(ButtonsStorage.ButtonFindFilmsInMenuMain.getCallBackData())) {
            message.setText("Вы нажали поиск фильма");
            return message;
        }

        throw new Exception("Неправильный ввод");
    }

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



