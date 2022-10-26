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
            message.setText(DialogStringsStorage.CommandStyleFilmsHorror);
            List<Film> filmList = DbManager.getInstance().getTableFilms().getAllHorror();
            transmittedData.setState(WaitingInputStartFromMenu);
            return message;

        } else if (callBackData.equals(ButtonsStorage.ButtonStylesFilmsFromMenuMainMystic.getCallBackData())) {
            message.setText("Вы нажали на мистика");
            return message;
        } else if (callBackData.equals(ButtonsStorage.ButtonStylesFilmsFromMenuMainHistory.getCallBackData())) {
            message.setText("Вы нажали на исторические");
            return message;
        } else if (callBackData.equals(ButtonsStorage.ButtonStylesFilmsFromMenuMainComedy.getCallBackData())) {
            message.setText("Вы нажали на комедии");
            return message;
        } else if (callBackData.equals(ButtonsStorage.ButtonStylesFilmsFromMenuMainMilitary.getCallBackData())) {
            message.setText("Вы нажали на боевики");
            return message;
        }
        throw new Exception("Ввод говно");
    }
}


