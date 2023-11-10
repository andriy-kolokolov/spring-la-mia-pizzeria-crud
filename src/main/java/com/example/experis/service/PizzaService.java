package com.example.experis.service;

import com.example.experis.model.Pizza;
import com.example.experis.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }

    public Pizza getPizzaById(Long id) {
        return pizzaRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Pizza with id " + id + " does not exist")
        );
    }

}