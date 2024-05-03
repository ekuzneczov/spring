package org.example.spring.http.controller;

import org.example.spring.dto.LoginDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "/user/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("login") LoginDto login,
                        Model model) {

        return "redirect:https://www.google.com/";
    }
}
