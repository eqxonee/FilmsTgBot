package org.example.Model.Tables;

import org.example.Model.Entities.StyleFilm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TableStyleFilmsJdbcImpl {
   /* private Connection connection;

    public TableStyleFilmsJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    public List<StyleFilm> getAll() throws SQLException {
        List<StyleFilm> styleFilms = new ArrayList<>();

        Statement statement = connection.createStatement();

        String selectQuery = String.format("SELECT * FROM style_films ORDER BY id ASC");

        ResultSet resultSet = statement.executeQuery(selectQuery);

        while (resultSet.next()) {

            int id = resultSet.getInt("id");
            String styleFilm = resultSet.getString("style_film");

            styleFilms.add(new StyleFilm(id, styleFilm));
        }
        resultSet.close();

        statement.close();

        return styleFilms;
    }

    public StyleFilm getByName(String name) throws SQLException {
        StyleFilm styleFilm = null;

        Statement statement = connection.createStatement();

        String selectQuery = String.format("SELECT * FROM style_films WHERE style_film = '%s'",name);

        ResultSet resultSet = statement.executeQuery(selectQuery);

        if (resultSet.next()) {

            int id = resultSet.getInt("id");
            String style = resultSet.getString("style_film");

            styleFilm = new StyleFilm(id, style);
        }
        resultSet.close();

        statement.close();

        return styleFilm;
    }*/
}
