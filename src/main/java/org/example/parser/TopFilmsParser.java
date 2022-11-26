package org.example.parser;

import com.sun.jdi.event.ExceptionEvent;
import org.example.Statemachine.State;
import org.example.Statemachine.TransmittedData;
import org.example.Util.ButtonsStorage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class TopFilmsParser {
    public SendMessage processGetTopFilms(String callBackData, TransmittedData transmittedData) throws Exception {

        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());

        if (callBackData.equals(ButtonsStorage.ButtonTopFilmsFromMenuMain.getCallBackData())) {


            HttpClient httpClient = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .connectTimeout(Duration.ofSeconds(10))
                    .build();

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://www.kinonews.ru/best_top100/"))
                    .build();

            HttpResponse httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String bodyAsString = (String) httpResponse.body();

            Document document = Jsoup.parse(bodyAsString);

            Elements divqs = document.select("div.bigtext");
            for (Element divq : divqs) {


                String number = divq.childNodes().get(0).childNodes().get(0).toString().replace('.', ' ').trim();

                String name = divq.childNodes().get(2).childNodes().get(0).toString();
                String engName = divq.childNodes().get(4).childNodes().get(0).toString();
                String year = divq.childNodes().get(5).toString();


                System.out.println(number + " " + name + " " + engName + " " + year + "\n");

                Integer.parseInt(number);
                if (Integer.parseInt(number) == 10)
                    break;
            }



        }


        return message;
    }
}

