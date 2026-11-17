package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionTest {

    public static void main(String[] args) throws SQLException {
        GuestbookDatabase guestbookDatabase = new GuestbookDatabase();
        GuestbookEntry entry = new GuestbookEntry("Isabela", "isat@gmail.com", "www.IsabelaBela.com", "nice");
        GuestbookEntry entry1 = new GuestbookEntry("Daniel", "DanielL@gmail.com", "www.DanielLeigh.com", "professional" );
        GuestbookEntry entry2 = new GuestbookEntry("Bela", "isat@gmail.com", "www.IsabelaBela.com", "Hello <b>world</b>");
        guestbookDatabase.saveEntry(entry2);
        //guestbookDatabase.saveEntry(entry);
        //guestbookDatabase.saveEntry(entry1);
        guestbookDatabase.showEntries();
    }
}
