package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // htmlとpdfの両方のマッピングに対応
    @GetMapping({"/home.html", "/home.pdf"})
    public String home(final Model model) {
        model.addAttribute("message", "Hello, Spring Boot!");
        return "home";
        
    }
}