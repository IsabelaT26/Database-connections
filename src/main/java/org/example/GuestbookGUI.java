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
