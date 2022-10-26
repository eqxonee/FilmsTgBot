package org.example.Model;

import org.example.Model.Connection.DbConnection;
import org.example.Model.Tables.TableFilms;
import org.example.Model.Tables.TableStyleFilms;

import java.sql.Connection;
import java.sql.SQLException;

public class DbManager {
    private TableStyleFilms tableStyleFilms;
    private TableFilms tableFilms;

    private DbManager() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        tableFilms = new TableFilms(connection);
        tableStyleFilms = new TableStyleFilms(connection);
    }

    public TableStyleFilms getTableStyleFilms() {
        return tableStyleFilms;
    }

    public TableFilms getTableFilms() {
        return tableFilms;
    }

    private static DbManager instance;

    public static DbManager getInstance() throws SQLException {
        if (instance == null) {
            instance = new DbManager();
        }
        return instance;
    }
}
