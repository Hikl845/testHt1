package org.example.game;

import java.io.*;
import java.util.*;

public class CityStorage {

    private Map<Character, Set<String>> citiesByLetter = new HashMap<>();
    private Set<String> allCities = new HashSet<>();

    public CityStorage() {
        loadCities();
    }

    private void loadCities() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        getClass().getClassLoader().getResourceAsStream("cities.txt")
                )
        )) {
            reader.lines()
                    .filter(line -> !line.isBlank()) // 🔥 першим
                    .map(String::trim)
                    .map(String::toLowerCase)
                    .forEach(city -> {
                        allCities.add(city);

                        char first = city.charAt(0);
                        citiesByLetter
                                .computeIfAbsent(first, k -> new HashSet<>())
                                .add(city);
                    });

        } catch (IOException e) {
            throw new RuntimeException("Помилка читання cities.txt", e);
        }
    }

    public boolean exists(String city) {
        return allCities.contains(city.toLowerCase());
    }

    public Set<String> getCitiesByLetter(char letter) {
        return citiesByLetter.getOrDefault(letter, Set.of());
    }
}