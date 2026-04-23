package org.example.game;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class CityStorage {

    private final Set<String> cities = new HashSet<>();

    public CityStorage() {
        loadCitiesFromResources();
    }

    private void loadCitiesFromResources() {
        try {
            InputStream is = getClass()
                    .getClassLoader()
                    .getResourceAsStream("cities.txt");

            if (is == null) {
                throw new RuntimeException("cities.txt не знайдено в resources");
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            reader.lines()
                    .map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .forEach(cities::add);

        } catch (Exception e) {
            throw new RuntimeException("Помилка читання cities.txt", e);
        }
    }

    public boolean exists(String city) {
        return cities.contains(city);
    }

    public String findByLetter(char letter, Set<String> used) {
        for (String city : cities) {
            if (!used.contains(city) &&
                    city.toLowerCase().startsWith(String.valueOf(letter))) {
                return city;
            }
        }
        return null;
    }
}
