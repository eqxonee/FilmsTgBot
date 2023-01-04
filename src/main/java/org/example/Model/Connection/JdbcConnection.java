package org.example.Model.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    public class JdbcConnection {
        private Connection connection;

        public JdbcConnection(String url, String user, String password) throws SQLException {
            connection = DriverManager.getConnection(url, user, password);
        }

        public Connection getConnection() {
            return connection;
        }

    }
