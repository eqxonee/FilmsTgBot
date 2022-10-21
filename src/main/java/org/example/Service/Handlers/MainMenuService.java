package org.example.Service.Handlers;

import org.example.Statemachine.State;
import org.example.Statemachine.TransmittedData;
import org.example.Util.DialogStringsStorage;
import org.example.Util.InlineKeyboardsMarkupStorage;
import org.example.Util.SystemStringsStorage;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MainMenuService {

    public void processCommandStart(long chatId, TransmittedData transmittedData, Update update, TelegramLongPollingBot bot) throws TelegramApiException {

        String receivedText = update.getMessage().getText();
        String responseText = SystemStringsStorage.Empty;

        if (!receivedText.equals(SystemStringsStorage.CommandStart)) {
            responseText = DialogStringsStorage.CommandStartError;

            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText(responseText);
            bot.execute(message);

            return;
        }

        responseText = DialogStringsStorage.CommandStartError;
        InlineKeyboardMarkup responseInlineKeyboardMarkup = InlineKeyboardsMarkupStorage.GetInlineKeyboardMarkupMenuMain();

        //transmittedData.setState(State.WaitingClickOnInlineButtonInMenuMain);

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(responseText);
        message.setReplyMarkup(responseInlineKeyboardMarkup);
        bot.execute(message);

    }
}