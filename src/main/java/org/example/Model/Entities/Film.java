package org.example.Model.Entities;

public class Film {

    private int id;
    private String name;
    private long chatId;
    private int timeLength;
    private String linkFilm;
    private int releaseFilm;
    private int styleFilmToId;

    public Film(int id, long chatId, String name, int timeLength, String linkFilm, int releaseFilm, int styleFilmToId) {
        this.id = id;
        this.chatId = chatId;
        this.name = name;
        this.timeLength = timeLength;
        this.linkFilm = linkFilm;
        this.releaseFilm = releaseFilm;
        this.styleFilmToId = styleFilmToId;
    }

    public Film(long chatId, String name){
        this.chatId = chatId;
        this.name = name;
    }

    public Film(String name, int timeLength, String linkFilm, int releaseFilm) {
        this.name = name;
        this.timeLength = timeLength;
        this.linkFilm = linkFilm;
        this.releaseFilm = releaseFilm;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTimeLength() {
        return timeLength;
    }

    public String getLinkFilm() {
        return linkFilm;
    }

    public int getReleaseFilm() {
        return releaseFilm;
    }

    public int getStyleFilmToId() {
        return styleFilmToId;
    }

    public long getChatId() {
        return chatId;
    }

    @Override
    public String toString() {
        return "" +
                ", Название='" + name + '\'' +
                ", Длительность=" + timeLength +
                ", Актуальная ссылка='" + linkFilm + '\'' +
                ", Дата выхода фильма=" + releaseFilm + '\n'
                ;
    }
}


