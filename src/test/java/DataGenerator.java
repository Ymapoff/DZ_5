package ru.netology.patterns.carddelivery.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Locale;
import java.util.Random;

import com.github.javafaker.Faker;

public class DataGenerator {
    private DataGenerator() {
    }
    static Faker faker = new Faker(new Locale("ru"));

    public static String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String generateName() {
        return faker.name().name();
    }

    public static String generatePhoneNumber() {
        return faker.numerify("+###########");
    }

    public static String generateCity() {
        var cities = new String[]{
                "Санкт-Петербург", "Омск", "Ульяновск", "Псков", "Йошкар-Ола", "Великий Новгород"
        };
        return cities[new Random().nextInt(cities.length)];
    }
}