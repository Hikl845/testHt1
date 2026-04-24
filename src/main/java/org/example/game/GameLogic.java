package org.example.game;

import java.util.HashSet;
import java.util.Set;

public class GameLogic {

    private CityStorage cityStorage = new CityStorage();
    private Set<String> usedCities = new HashSet<>();

    private int score = 0;
    private int bestScore = 0;

    private Character expectedLetter = null;
    private boolean gameOver = false;

    public MoveResult processUserMove(String userCity) {
        if (gameOver) {
            return MoveResult.win();
        }

        userCity = normalize(userCity);

        if (userCity.isEmpty() || !cityStorage.exists(userCity)) {
            return MoveResult.invalid();
        }

        if (usedCities.contains(userCity)) {
            return MoveResult.used();
        }

        if (expectedLetter != null &&
                !userCity.startsWith(String.valueOf(expectedLetter))) {
            return MoveResult.invalidLetter(expectedLetter);
        }

        usedCities.add(userCity);
        score++;

        char lastChar = getLastChar(userCity);

        String response = findCity(lastChar);

        if (response == null) {
            gameOver = true;
            return MoveResult.win();
        }

        usedCities.add(response);
        expectedLetter = getLastChar(response);

        return MoveResult.success(response);
    }

    private String findCity(char letter) {
        for (String city : cityStorage.getCitiesByLetter(letter)) {
            if (!usedCities.contains(city)) {
                return city;
            }
        }
        return null;
    }

    public int getScore() {
        return score;
    }

    public int getBestScore() {
        return bestScore;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void updateBestScore() {
        if (score > bestScore) {
            bestScore = score;
        }
    }

    public void resetGame() {
        usedCities.clear();
        score = 0;
        expectedLetter = null;
        gameOver = false;
    }

    private String normalize(String input) {
        return input.trim().toLowerCase();
    }

    private char getLastChar(String word) {
        for (int i = word.length() - 1; i >= 0; i--) {
            char c = word.charAt(i);
            if ("ьйи".indexOf(c) == -1) {
                return c;
            }
        }
        return word.charAt(word.length() - 1);
    }
}