package org.example;

import java.sql.*;

public class GuestbookDatabase {
    private String url = "jdbc:mysql://localhost:3306/guestbook";
    private String username = "root";
    private String password = System.getenv("MYSQL_PASSWORD");



    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public void saveEntry(GuestbookEntry entry) throws SQLException {
        String query = "INSERT INTO entries (name, email, website, comment)\n" +
                "VALUES (?,?,?,?);";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);){
            statement.setString(1, entry.getName());
            statement.setString(2, entry.getEmail());
            statement.setString(3, entry.getWebsite());
            statement.setString(4, entry.getComment());
            statement.executeUpdate();
        }

    }

    public void showEntries() throws  SQLException{
        String query = "SELECT * FROM entries";
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rows = statement.executeQuery();
            System.out.printf("Name | Email | Website | Comment");
            while (rows.next()){
                System.out.printf("%s | %s | %s | %s%n",
                        rows.getString("name"),
                        rows.getString("email"),
                        rows.getString("website"),
                        rows.getString("comment"));
            }
        }
    }
}
