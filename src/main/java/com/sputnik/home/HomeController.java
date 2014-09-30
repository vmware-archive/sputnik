package com.sputnik.home;

import com.sputnik.persistence.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    MessageRepository repository;

    @RequestMapping("/")
    public String listArticles(Model model) {
        model.addAttribute("message", "Greetings!");

        return "index";
    }
}
