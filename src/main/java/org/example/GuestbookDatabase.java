package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GuestbookDatabase {
    private String url = "jdbc:mysql://localhost:3306/guestbook";
    private String username = "root";
    private String password = System.getenv("MYSQL_PASSWORD");



    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
