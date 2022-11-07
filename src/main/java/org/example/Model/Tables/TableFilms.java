package org.example.Model.Tables;

import org.example.Model.Entities.Film;
import org.example.Model.Entities.StyleFilm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TableFilms {
        private Connection connection;

        public TableFilms(Connection connection) {
            this.connection = connection;
        }

        public void addNew(Film film) throws SQLException {
            Statement statement = connection.createStatement();

            String insertQuery = String.format("INSERT INTO films (name, time_length, link_film, release_year, style_film_id) VALUES ('%s',%d,'%s',%d,%d)", film.getName(), film.getTimeLength(), film.getLinkFilm(), film.getReleaseFilm(), film.getStyleFilmToId());
            //Изменить Length в БД
            statement.executeUpdate(insertQuery);

            statement.close();
        }

    public Film getByFilmByName(String name) throws SQLException {
        Film film = null;

        Statement statement = connection.createStatement();

        String selectQuery = String.format("SELECT * FROM films WHERE name = '%s'", name);

        ResultSet resultSet = statement.executeQuery(selectQuery);

        if(resultSet.next()) {

            long chatId = resultSet.getLong("id");
            String nameFilm = resultSet.getString("name");

            film = new Film(chatId, nameFilm);
        }

        resultSet.close();

        statement.close();

        return film;
    }

    public void deleteByFilmName(String name) throws SQLException {
        Statement statement = connection.createStatement();
        String deleteQuery = String.format("DELETE FROM films WHERE name = '%s'", name);

        statement.executeUpdate(deleteQuery);
        statement.close();
    }

    public List<StyleFilm> getFilmByName(long chatId) throws SQLException {

        List<StyleFilm> films = new ArrayList<>();

        Statement statement = connection.createStatement();

        String selectQuery = String.format("SELECT * FROM style_films WHERE id = %d ORDER BY id ASC", chatId);

        ResultSet resultSet = statement.executeQuery(selectQuery);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String styleFilms = resultSet.getString("style_film");


            films.add(new StyleFilm(id, styleFilms));
        }

        resultSet.close();

        statement.close();

        return films;
    }

    public List<Film> getAllHorror() throws SQLException {
        List<Film> films = new ArrayList<>();

        Statement statement = connection.createStatement();

        String selectQuery = String.format("SELECT * FROM films WHERE style_film_id = 1");
        ResultSet resultSet = statement.executeQuery(selectQuery);


        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int timeLength = resultSet.getInt("time_length");
            String linkFilm = resultSet.getString("link_film");
            int releaseFilm = resultSet.getInt("release_year");

            films.add(new Film(name,timeLength,linkFilm,releaseFilm));
        }

        resultSet.close();
        statement.close();
        return films;
    }

    public List<Film> getAllMystic() throws SQLException {
        List<Film> films = new ArrayList<>();

        Statement statement = connection.createStatement();

        String selectQuery = String.format("SELECT * FROM films WHERE style_film_id = 2");
        ResultSet resultSet = statement.executeQuery(selectQuery);


        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int timeLength = resultSet.getInt("time_length");
            String linkFilm = resultSet.getString("link_film");
            int releaseFilm = resultSet.getInt("release_year");

            films.add(new Film(name,timeLength,linkFilm,releaseFilm));
        }

        resultSet.close();
        statement.close();
        return films;
    }

    public List<Film> getAllHistory() throws SQLException {
        List<Film> films = new ArrayList<>();

        Statement statement = connection.createStatement();

        String selectQuery = String.format("SELECT * FROM films WHERE style_film_id = 3");
        ResultSet resultSet = statement.executeQuery(selectQuery);


        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int timeLength = resultSet.getInt("time_length");
            String linkFilm = resultSet.getString("link_film");
            int releaseFilm = resultSet.getInt("release_year");

            films.add(new Film(name,timeLength,linkFilm,releaseFilm));
        }

        resultSet.close();
        statement.close();
        return films;
    }

    public List<Film> getAllComedy() throws SQLException {
        List<Film> films = new ArrayList<>();

        Statement statement = connection.createStatement();

        String selectQuery = String.format("SELECT * FROM films WHERE style_film_id = 4");
        ResultSet resultSet = statement.executeQuery(selectQuery);


        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int timeLength = resultSet.getInt("time_length");
            String linkFilm = resultSet.getString("link_film");
            int releaseFilm = resultSet.getInt("release_year");

            films.add(new Film(name,timeLength,linkFilm,releaseFilm));
        }

        resultSet.close();
        statement.close();
        return films;
    }

    public List<Film> getAllMilitary() throws SQLException {
        List<Film> films = new ArrayList<>();

        Statement statement = connection.createStatement();

        String selectQuery = String.format("SELECT * FROM films WHERE style_film_id = 5");
        ResultSet resultSet = statement.executeQuery(selectQuery);


        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int timeLength = resultSet.getInt("time_length");
            String linkFilm = resultSet.getString("link_film");
            int releaseFilm = resultSet.getInt("release_year");

            films.add(new Film(name,timeLength,linkFilm,releaseFilm));
        }

        resultSet.close();
        statement.close();
        return films;
    }

    public Film getLinkFilm(String link) throws SQLException {
        Film film = null;

        Statement statement = connection.createStatement();

        String selectQuery = String.format("SELECT * FROM films WHERE link_film = '%s'",link);

        ResultSet resultSet = statement.executeQuery(selectQuery);

        if(resultSet.next()) {

            long chatId = resultSet.getLong("id");
            String linkFilm = resultSet.getString("link_film");

            film = new Film(chatId, linkFilm);
        }

        resultSet.close();

        statement.close();

        return film;
    }
}
