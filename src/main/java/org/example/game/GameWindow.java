package org.example.game;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    private JTextField inputField;
    private JLabel computerLabel;
    private JLabel scoreLabel;
    private JLabel bestScoreLabel;

    private GameLogic gameLogic;

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

        JPanel top = new JPanel(new GridLayout(2, 1));
        top.add(new JLabel("Введіть назву міста:", SwingConstants.CENTER));

        inputField = new JTextField();
        top.add(inputField);
        add(top, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(2, 1));
        computerLabel = new JLabel("Комп'ютер: ", SwingConstants.CENTER);
        scoreLabel = new JLabel("Рахунок: 0", SwingConstants.CENTER);

        center.add(computerLabel);
        center.add(scoreLabel);
        add(center, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout());

        JButton move = new JButton("Хід");
        JButton giveUp = new JButton("Здаюсь");
        JButton reset = new JButton("Нова гра");

        bestScoreLabel = new JLabel("Рекорд: 0");

        bottom.add(move);
        bottom.add(giveUp);
        bottom.add(reset);
        bottom.add(bestScoreLabel);

        add(bottom, BorderLayout.SOUTH);

        inputField.addActionListener(e -> handleMove());
        move.addActionListener(e -> handleMove());
        giveUp.addActionListener(e -> handleGiveUp());
        reset.addActionListener(e -> resetGame());
    }

    private void handleMove() {
        if (gameLogic.isGameOver()) {
            return;
        }

        String input = inputField.getText();

        if (input.equalsIgnoreCase("здаюсь")) {
            handleGiveUp();
            return;
        }

        MoveResult result = gameLogic.processUserMove(input);

        switch (result.getType()) {
            case INVALID -> show("Неправильне місто");

            case USED -> show("Місто вже було");

            case INVALID_LETTER ->
                    show("Потрібно місто на літеру: " + result.getExpectedLetter());

            case WIN -> {
                gameLogic.updateBestScore();
                show("Ти виграв 🎉\nРахунок: " + gameLogic.getScore());
                bestScoreLabel.setText("Рекорд: " + gameLogic.getBestScore());
            }

            case SUCCESS -> {
                computerLabel.setText("Комп'ютер: " + result.getCity());
                scoreLabel.setText("Рахунок: " + gameLogic.getScore());
            }
        }

        inputField.setText("");
    }

    private void handleGiveUp() {
        if (gameLogic.isGameOver()) {
            return;
        }

        gameLogic.updateBestScore();
        show("Ти програв 😢\nРахунок: " + gameLogic.getScore());
        bestScoreLabel.setText("Рекорд: " + gameLogic.getBestScore());
    }

    private void resetGame() {
        gameLogic.resetGame();

        computerLabel.setText("Комп'ютер: ");
        scoreLabel.setText("Рахунок: 0");
        inputField.setText("");
    }

    private void show(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}