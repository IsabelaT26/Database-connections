package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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
            statement.setString(1, removeHTML(entry.getName()));
            statement.setString(2, removeHTML(entry.getEmail()));
            statement.setString(3, removeHTML(entry.getWebsite()));
            statement.setString(4, removeHTML(entry.getComment()));
            statement.executeUpdate();
        }

    }

    public List<GuestbookEntry> getEntries() throws SQLException {
        String query = "SELECT name, email, website, comment FROM entries";
        List<GuestbookEntry> entries = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rows = statement.executeQuery()) {

            while (rows.next()) {
                GuestbookEntry entry = new GuestbookEntry(
                        rows.getString("name"),
                        rows.getString("email"),
                        rows.getString("website"),
                        rows.getString("comment")
                );
                entries.add(entry);
            }
        }

        return entries;
    }

    public void showEntries() throws SQLException {
        List<GuestbookEntry> entries = getEntries();

        String format = "%-10s | %-20s | %-30s | %-10s%n";
        System.out.printf(format, "Name", "Email", "Website", "Comment");

        for (GuestbookEntry entry : entries) {
            System.out.printf(format,
                    entry.getName(),
                    entry.getEmail(),
                    entry.getWebsite(),
                    entry.getComment());
        }
    }

    private String removeHTML(String text){
        Pattern htmlTag = Pattern.compile("</?[a-zA-Z][^>]*>");
        if(htmlTag.matcher(text).find()){
            return "censur";
        }
        return text;
    }
}
