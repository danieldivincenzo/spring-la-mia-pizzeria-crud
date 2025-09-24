package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Pizza;
import com.example.demo.repository.PizzaRepository;

import jakarta.validation.Valid;





@Controller
@RequestMapping("/pizze")
public class MainController {

    @Autowired
    private PizzaRepository repository;

    @GetMapping
    public String index(Model model, @RequestParam(name="keyword", required=false) String keyword) {
        List <Pizza> risultato = null;
        if(keyword == null || keyword.isBlank()){
            risultato = repository.findAll();
        } else {
            risultato = repository.findByNomeContainingIgnoreCase(keyword);
        }
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

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "/pizze/create";
    }

    @PostMapping("/create")
    public String save(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "/pizze/create";
        }

        repository.save(formPizza);
        redirectAttributes.addFlashAttribute("successMessage", "Pizza creata con successo");
        return "redirect:/pizze";
    }
}
