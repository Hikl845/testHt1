package org.example.game;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class CityStorage {

    private Set<String> cities = new HashSet<>();

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
                    .map(String::trim)
                    .map(String::toLowerCase)
                    .filter(s -> !s.isEmpty())
                    .forEach(cities::add);

        } catch (IOException e) {
            throw new RuntimeException("Помилка читання cities.txt", e);
        }
    }

    public boolean exists(String city) {
        return cities.contains(city.toLowerCase());
    }

    public String findByLetter(char letter, Set<String> used) {
        for (String city : cities) {
            if (!used.contains(city) && city.startsWith(String.valueOf(letter))) {
                return city;
            }
        }
        return null;
    }
}