package org.example.welcome;

import org.example.game.GameWindow;

import javax.swing.*;
import java.awt.*;

public class WelcomeWindow extends JFrame {

    public WelcomeWindow() {
        setTitle("Вітаємо");
        setSize(400, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new FlowLayout());

        JLabel label = new JLabel("Вітаємо вас у грі дитинства і всіх розумників!");
        JButton button = new JButton("ОК");

        button.addActionListener(e -> {
            new GameWindow().setVisible(true);
            dispose();
        });

        add(label);
        add(button);

        setVisible(true);
    }
}