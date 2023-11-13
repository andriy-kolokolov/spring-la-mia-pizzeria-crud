package com.example.experis.controller;

import com.example.experis.service.PizzaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.experis.model.Pizza;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/pizza")
public class PizzaController {

    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping()
    public String index(
            @RequestParam(value = "name", required = false) String name,
            Model model
    ) {
        List<Pizza> pizzas;

        if (name != null && !name.trim().isEmpty()) {
            pizzas = pizzaService.findByName(name);
        } else {
            pizzas = pizzaService.getAll();
        }

        model.addAttribute("pizzas", pizzas);
        model.addAttribute("route", "pizza");

        return "pizza/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model) {
        model.addAttribute("pizza", pizzaService.getPizzaById(id));
        model.addAttribute("route", "pizza");

        return "pizza/show";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("route", "pizza");

        return "pizza/create";
    }

    @PostMapping("/add")
    public String addPizza(
            @ModelAttribute @Valid Pizza pizza,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        model.addAttribute("route", "pizza");

        if (bindingResult.hasErrors()) {
            model.addAttribute("pizza", pizza); // Add the Pizza object to the model
            return "pizza/create"; // Return to the form page with validation errors
        }

        pizzaService.save(pizza);
        redirectAttributes.addFlashAttribute("message", "Pizza added successfully!");
        return "redirect:/pizza";
    }

}
