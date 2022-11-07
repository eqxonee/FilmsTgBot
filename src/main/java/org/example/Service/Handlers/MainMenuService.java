package org.example.Service.Handlers;


import org.example.Model.DbManager;
import org.example.Model.Entities.Film;
import org.example.Model.Entities.StyleFilm;
import org.example.Model.Tables.TableFilms;
import org.example.Statemachine.DataStorage;
import org.example.Statemachine.TransmittedData;
import org.example.Util.ButtonsStorage;
import org.example.Util.DialogStringsStorage;
import org.example.Util.InlineKeyboardsMarkupStorage;
import org.example.Util.SystemStringsStorage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;


import java.util.List;

import static org.example.Statemachine.State.*;


public class MainMenuService {

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
            message.setText(DialogStringsStorage.CommandAddNameFilm);

            transmittedData.setState(NameFilm);

            return message;
        } else if (callBackData.equals(ButtonsStorage.ButtonDeleteFilmsInMenuMain.getCallBackData())) {
            StringBuilder stringBuilder = new StringBuilder();
            List<StyleFilm> filmList = DbManager.getInstance().getTableStyleFilms().getAll();
            for (int i = 0; i < filmList.size(); i++) {
                int id = filmList.get(i).getId();
                String style = filmList.get(i).getStyleFilm();
                stringBuilder.append(id).append(". ").append(style).append("\n");
            }
            message.setText(DialogStringsStorage.CommandDeleteFilm +"\n" + stringBuilder);

            transmittedData.setState(WaitingClickOnInlineButtonInMenuDeleteFilm);

            return message;
        } else if (callBackData.equals(ButtonsStorage.ButtonFindFilmsInMenuMain.getCallBackData())) {
            message.setText("Вы нажали поиск фильма");
            return message;
        }

        throw new Exception("Неправильный ввод");
    }

}



