package org.example;

import org.example.welcome.WelcomeWindow;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new WelcomeWindow().setVisible(true);
        });
    }
}