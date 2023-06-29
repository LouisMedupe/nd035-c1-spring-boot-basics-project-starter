package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final UserService users;
    private final NoteService notes;
    private final FileService files;
    private final CredentialService credentials;

    public HomeController(
            UserService users,
            NoteService notes,
            FileService files,
            CredentialService credentials
    ) {
        this.users = users;
        this.notes = notes;
        this.files = files;
        this.credentials = credentials;
    }

    @GetMapping()
    public String homeView(Authentication authentication, Model model) {

        try {
            User user = users.getUser(authentication.getName());
            //Integer userId = users.getUser(authentication.getName()).getUserId();
            model.addAttribute("notes", notes.getAllNotes(user.getUserId()));
            model.addAttribute("files", files.getUserFiles(user.getUserId()));
            model.addAttribute("credentials", credentials.getAllCredentials(user.getUserId()));

        } catch (Exception ignored) {
            model.addAttribute("logoutSuccess",false);
            return "redirect:/LoginError";
        }
        return "home";
    }

}
