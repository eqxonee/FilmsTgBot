package org.example.Service.Handlers;

import org.example.Statemachine.State;
import org.example.Statemachine.TransmittedData;
import org.example.Util.ButtonsStorage;
import org.example.Util.DialogStringsStorage;
import org.example.Util.InlineKeyboardsMarkupStorage;
import org.example.Util.SystemStringsStorage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;


public class MainMenuService {

    public SendMessage processCommandStart(String command, TransmittedData transmittedData) {

        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());

        if (!command.equals(SystemStringsStorage.CommandStart)) {
            message.setText(DialogStringsStorage.CommandStartError);
            return message;
        }

        transmittedData.setState(State.WaitingClickOnInlineButtonInMenuMain);

        message.setText(DialogStringsStorage.CommandStartOK);
        message.setReplyMarkup(InlineKeyboardsMarkupStorage.GetInlineKeyboardMarkupMenuMain());

        return message;

    }

    public SendMessage processClickOnInlineButtonInMenuMain(String callBackData, TransmittedData transmittedData) throws Exception {

        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());

        if (callBackData.equals(ButtonsStorage.ButtonAllFilmsInMenuMain.getCallBackData())) {
            message.setText("Вы нажали на список фильмов");
            return message;
        }else if(callBackData.equals(ButtonsStorage.ButtonAddFilmsInMenuMain.getCallBackData())){
            message.setText("Вы нажали добавить фильм");
            return message;
        }else if(callBackData.equals(ButtonsStorage.ButtonDeleteFilmsInMenuMain.getCallBackData())){
            message.setText("Вы нажали удалить фильм");
            return message;
        }else if(callBackData.equals(ButtonsStorage.ButtonFindFilmsInMenuMain.getCallBackData())){
            message.setText("Вы нажали поиск фильма");
            return message;
        }

        throw new Exception("Неправильный ввод");
    }
}
