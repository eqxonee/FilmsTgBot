package org.example.Statemachine;

import org.example.Bot.BotInitializer;
import org.example.Service.ServiceManager;
import org.example.Util.SystemStringsStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;

public class ChatRouter {

    private Map<Long,TransmittedData> chats;
    private ServiceManager serviceManager;
    private static final Logger logger = LoggerFactory.getLogger(ChatRouter.class);

    public ChatRouter() {
        chats = new HashMap<>();
        serviceManager = new ServiceManager();
    }

    public SendMessage route(long chatId, String textData) throws Exception {

        if(!chats.containsKey(chatId)){
            chats.put(chatId,new TransmittedData(chatId));
        }

        TransmittedData transmittedData = chats.get(chatId);
        logger.info(String.format("ROUTE: %d %s", chatId, transmittedData.getState()));

        if(textData.equals(SystemStringsStorage.CommandReset) && transmittedData.getState() != State.WaitingCommandStart){
            return serviceManager.getStaticService().processCommandReset(transmittedData);
        }

       return serviceManager.processUpdate(textData, transmittedData);
    }
}
