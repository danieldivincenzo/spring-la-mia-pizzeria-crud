package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Pizza;
import com.example.demo.repository.PizzaRepository;




@Controller
@RequestMapping("/pizze")
public class MainController {

    @Autowired
    private PizzaRepository repository;

    @GetMapping
    public String index(Model model) {
        List <Pizza> risultato = repository.findAll();
        model.addAttribute("lista", risultato);
        return "/pizze/index";
    }
    
    @GetMapping("/show/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        Optional <Pizza> optionalPizza = repository.findById(id);
        if(optionalPizza.isPresent()){
            model.addAttribute("pizza", optionalPizza.get());
            model.addAttribute("empty", false);
        } else {
            model.addAttribute("empty", true);
        }
        return "/pizze/show";
    }
    
}
