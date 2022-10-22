package org.example.Service.Handlers;

import org.example.Statemachine.State;
import org.example.Statemachine.TransmittedData;
import org.example.Util.DialogStringsStorage;
import org.example.Util.InlineKeyboardsMarkupStorage;
import org.example.Util.SystemStringsStorage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
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
}
