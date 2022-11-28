package org.example.parser;

import org.example.Statemachine.TransmittedData;
import org.example.Util.ButtonsStorage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

public class TopFilmsParser {
    public String processGetTopFilms() throws Exception {


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
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 10; i++) {

            Element divq = divqs.get(i);


            String number = divq.childNodes().get(0).childNodes().get(0).toString().replace(".", " ");
            String name = divq.childNodes().get(2).childNodes().get(0).toString();
            String engName = divq.childNodes().get(4).childNodes().get(0).toString();
            String year = divq.childNodes().get(5).toString();

            stringBuilder.append(number + " " + name + "  " + engName + " " + year + "\n");


        }


        return stringBuilder.toString();
    }
}
















