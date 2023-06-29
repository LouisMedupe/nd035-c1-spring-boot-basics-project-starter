package com.udacity.jwdnd.course1.cloudstorage.controller;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
    @Controller
    public class LoginController {
        @GetMapping("/login")
        public String login(Model model) {
         // model.addAttribute("",true);
            return "login";
        }

        @GetMapping("/LoginError")
        public String loginError(Model model) {
            model.addAttribute("AuthError", true);
            return "login";
        }

    }
