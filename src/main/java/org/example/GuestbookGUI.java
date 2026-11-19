package org.example;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class GuestbookGUI {

    public static void main(String[] args){

        GuestbookDatabase guestbookDatabase = new GuestbookDatabase();

        JFrame frame = new JFrame("GuestBook");
        JPanel panel = new JPanel(new BorderLayout(0, 12));
        panel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JPanel fieldsPanel = new JPanel(new GridLayout(3, 2, 8, 8));

        JTextArea entriesArea = new JTextArea(10, 40);
        entriesArea.setEditable(false);
        entriesArea.setLineWrap(true);
        entriesArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(entriesArea);

        JFrame entriesFrame = new JFrame("Guestbook entries");
        entriesFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        entriesFrame.add(scrollPane);
        entriesFrame.setSize(600, 400);
        entriesFrame.setLocationRelativeTo(frame);

        JLabel name = new JLabel("Name");
        JLabel email = new JLabel("Email");
        JLabel website = new JLabel("Website");
        JLabel comment = new JLabel("Comment");

        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField websiteField = new JTextField();
        JTextArea commentField = new JTextArea();

        JButton enterButton = new JButton();
        JButton showButton = new JButton();

        enterButton.addActionListener(event -> {
            try {
                guestbookDatabase.saveEntry(new GuestbookEntry(nameField.getText(), emailField.getText(), websiteField.getText(), commentField.getText()));
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(frame, "Could not save the entry.");
                e.printStackTrace();
            }
        });

        showButton.addActionListener(event -> {
            try {
                var entries = guestbookDatabase.getEntries();
                entriesArea.setText("");

                for (GuestbookEntry entry : entries) {
                    entriesArea.append("Name: " + entry.getName() + "\n");
                    entriesArea.append("Email: " + entry.getEmail() + "\n");
                    entriesArea.append("Website: " + entry.getWebsite() + "\n");
                    entriesArea.append("Comment: " + entry.getComment() + "\n\n");
                }

                entriesArea.setCaretPosition(0);
                entriesFrame.setVisible(true);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(frame, "Could not load the entries.");
                e.printStackTrace();
            }
        });


        fieldsPanel.add(name);
        fieldsPanel.add(nameField);
        fieldsPanel.add(email);
        fieldsPanel.add(emailField);
        fieldsPanel.add(website);
        fieldsPanel.add(websiteField);
        panel.add(fieldsPanel, BorderLayout.NORTH);

        JPanel commentPanel = new JPanel(new BorderLayout(0, 4));
        commentField.setLineWrap(true);
        commentField.setWrapStyleWord(true);
        commentPanel.add(comment, BorderLayout.NORTH);
        commentPanel.add(new JScrollPane(commentField), BorderLayout.CENTER);
        panel.add(commentPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        enterButton.setText("Save entry");
        showButton.setText("Show entries");
        buttonPanel.add(showButton);
        buttonPanel.add(enterButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);



        frame.setSize(600,400);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);
    }

}
