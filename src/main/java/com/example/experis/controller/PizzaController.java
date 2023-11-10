package com.example.experis.controller;

import com.example.experis.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pizza")
public class PizzaController {

    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("pizzas", pizzaService.getAllPizzas());
        model.addAttribute("route", "pizza");

        return "pizza/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model) {
        model.addAttribute("pizza", pizzaService.getPizzaById(id));
        model.addAttribute("route", "pizza");

        return "pizza/show";
    }

}
