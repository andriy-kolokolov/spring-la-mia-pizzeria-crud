package com.example.experis.database;


import com.example.experis.model.Pizza;
import com.example.experis.repository.PizzaRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
public class PizzaSeeder {

    private static final String[] INGREDIENTS = {
            "Mozzarella", "Tomato Sauce", "Pepperoni", "Onions",
            "Green Peppers", "Mushrooms", "Sausage", "Bacon",
            "Black Olives", "Pineapple", "Ham", "Spinach"
    };

    private static final String[] PIZZA_NAMES = {
            "Margherita", "Pepperoni", "Hawaiian", "Veggie",
            "Meat Lovers", "Supreme", "BBQ Chicken", "Buffalo Chicken",
            "Mushroom and Spinach", "Four Cheese"
    };

    private static final Random random = new Random();
    private static final int MAX_PIZZA_COUNT = 15;

    @Bean
    CommandLineRunner commandLineRunner(PizzaRepository pizzaRepository) {
        return args -> {
            long count = pizzaRepository.count();
            if (count >= MAX_PIZZA_COUNT) {
                return; // Skip data generation if the threshold is reached
            }

            Faker faker = new Faker(new Locale("en-US"));

            for (int i = 0; i < MAX_PIZZA_COUNT; i++) {
                String name = PIZZA_NAMES[random.nextInt(PIZZA_NAMES.length)];

                String description = IntStream.range(0, 3 + random.nextInt(3)) // 3 to 5 ingredients
                        .mapToObj(n -> INGREDIENTS[random.nextInt(INGREDIENTS.length)])
                        .collect(Collectors.joining(", "));

                String url = faker.internet().url();
                BigDecimal price = BigDecimal.valueOf(faker.number().randomDouble(2, 5, 30));

                Pizza pizza = new Pizza(null, name, description, url, price);
                pizzaRepository.save(pizza);
            }
        };
    }
}
