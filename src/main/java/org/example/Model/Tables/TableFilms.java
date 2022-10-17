package org.example.Model.Tables;

import org.example.Model.Entities.Films;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableFilms {
        private Connection connection;

        public TableFilms(Connection connection) {
            this.connection = connection;
        }

        public void addNew(Films films) throws SQLException {
            Statement statement = connection.createStatement();

            String insertQuery = String.format("INSERT INTO films (chat_id, name, time_length, link_film, release_year, style_film_id) VALUES (%d,%f,%d,%f,%d,%d)", films.getChatId(), films.getName(), films.getTimeLength(), films.getLinkFilm(), films.getReleaseFilm(), films.getStyleFilmToId());
            //Изменить Length в БД
            statement.executeUpdate(insertQuery);

            statement.close();
        }
}
