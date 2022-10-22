package org.example.Service.Handlers;

import org.example.Statemachine.State;
import org.example.Statemachine.TransmittedData;
import org.example.Util.DialogStringsStorage;
import org.example.Util.InlineKeyboardsMarkupStorage;
import org.example.Util.SystemStringsStorage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class StaticService {
    public SendMessage processCommandReset(TransmittedData transmittedData) {
        transmittedData.setState(State.WaitingClickOnInlineButtonInMenuChooseFilm);

        transmittedData.getDataStorage().clear();

        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());
        message.setText(DialogStringsStorage.CommandStartOK);
        message.setReplyMarkup(InlineKeyboardsMarkupStorage.GetInlineKeyboardMarkupMenuMain());

        return message;
    }
}
