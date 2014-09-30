package com.sputnik.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String listArticles(Model model) {
        model.addAttribute("message", "Greetings!");

        return "index";
    }
}
