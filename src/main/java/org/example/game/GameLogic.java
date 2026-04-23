package org.example.game;

import java.util.HashSet;
import java.util.Set;

public class GameLogic {

    private CityStorage cityStorage = new CityStorage();
    private Set<String> usedCities = new HashSet<>();

    private int score = 0;
    private int bestScore = 0;

    public String processUserMove(String userCity) {
        userCity = userCity.trim();

        if (userCity.isEmpty() || !cityStorage.exists(userCity)) {
            return "INVALID";
        }

        if (usedCities.contains(userCity)) {
            return "USED";
        }

        usedCities.add(userCity);
        score++;

        char lastChar = getLastChar(userCity);

        String response = cityStorage.findByLetter(lastChar, usedCities);

        if (response == null) {
            return "WIN";
        }

        usedCities.add(response);
        return response;
    }

    public int getScore() {
        return score;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void updateBestScore() {
        if (score > bestScore) {
            bestScore = score;
        }
    }

    public void resetGame() {
        usedCities.clear();
        score = 0;
    }

    private char getLastChar(String word) {
        String lower = word.toLowerCase();

        for (int i = lower.length() - 1; i >= 0; i--) {
            char c = lower.charAt(i);
            if ("ьйы".indexOf(c) == -1) {
                return c;
            }
        }
        return lower.charAt(lower.length() - 1);
    }
}