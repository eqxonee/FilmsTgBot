package org.example.Service;

import org.example.Service.Handlers.MainMenuService;
import org.example.Statemachine.State;
import org.example.Statemachine.TransmittedData;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;

public class ServiceManager {

    private Map<State,Service> methods;
    private MainMenuService mainMenuService;

    public ServiceManager() {
        methods = new HashMap<>();
        mainMenuService = new MainMenuService();

        methods.put(State.WaitingCommandStart,mainMenuService::processCommandStart);
    }

    public void processUpdate(long chatId, TransmittedData transmittedData, Update update, TelegramLongPollingBot bot) throws TelegramApiException {
        methods.get(transmittedData.getState()).processUpdate(chatId, transmittedData, update, bot);
    }
}
