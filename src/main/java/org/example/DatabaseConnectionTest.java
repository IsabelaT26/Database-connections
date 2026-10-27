package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionTest {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/guestbook";
        String username = "root";
        String password = System.getenv("MYSQL_PASSWORD");

        try (Connection connection =
                     DriverManager.getConnection(url, username, password)) {

            System.out.println("Connected to: "
                    + connection.getCatalog());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
