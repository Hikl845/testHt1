package org.example.game;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    private JTextField inputField;
    private JLabel computerLabel;
    private JLabel scoreLabel;
    private JLabel bestScoreLabel;

    private final GameLogic gameLogic;

    public GameWindow() {
        setTitle("Міста");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        gameLogic = new GameLogic();

        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.add(new JLabel("Введіть назву міста:", SwingConstants.CENTER));

        inputField = new JTextField();
        topPanel.add(inputField);

        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(2, 1));

        computerLabel = new JLabel("Комп'ютер: ", SwingConstants.CENTER);
        scoreLabel = new JLabel("Рахунок: 0", SwingConstants.CENTER);

        centerPanel.add(computerLabel);
        centerPanel.add(scoreLabel);

        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout());

        JButton moveButton = new JButton("Хід");
        JButton giveUpButton = new JButton("Здаюсь");
        JButton resetButton = new JButton("Нова гра");

        bestScoreLabel = new JLabel("Рекорд: 0");

        bottomPanel.add(moveButton);
        bottomPanel.add(giveUpButton);
        bottomPanel.add(resetButton);
        bottomPanel.add(bestScoreLabel);

        add(bottomPanel, BorderLayout.SOUTH);


        inputField.addActionListener(e -> handleMove());

        moveButton.addActionListener(e -> handleMove());
        giveUpButton.addActionListener(e -> handleGiveUp());
        resetButton.addActionListener(e -> resetGame());
    }

    private void handleMove() {
        String input = inputField.getText();

        if (input.equalsIgnoreCase("здаюсь")) {
            handleGiveUp();
            inputField.setText("");
            return;
        }


        String result = gameLogic.processUserMove(input);

        switch (result) {
            case "INVALID" -> JOptionPane.showMessageDialog(this, "Неправильне місто!");

            case "USED" -> JOptionPane.showMessageDialog(this, "Місто вже було!");

            case "WIN" -> {
                gameLogic.updateBestScore();

                JOptionPane.showMessageDialog(this,
                        "Ти виграв 🎉\nРахунок: " + gameLogic.getScore());

                bestScoreLabel.setText("Рекорд: " + gameLogic.getBestScore());
            }

            default -> {
                computerLabel.setText("Комп'ютер: " + result);
                scoreLabel.setText("Рахунок: " + gameLogic.getScore());
            }
        }

        inputField.setText(""); // ✅ очищення завжди
    }

    private void handleGiveUp() {
        gameLogic.updateBestScore();

        JOptionPane.showMessageDialog(this,
                "Ти програв 😢\nРахунок: " + gameLogic.getScore());

        bestScoreLabel.setText("Рекорд: " + gameLogic.getBestScore());
    }

    private void resetGame() {
        gameLogic.resetGame();

        computerLabel.setText("Комп'ютер: ");
        scoreLabel.setText("Рахунок: 0");
        inputField.setText("");
    }
}