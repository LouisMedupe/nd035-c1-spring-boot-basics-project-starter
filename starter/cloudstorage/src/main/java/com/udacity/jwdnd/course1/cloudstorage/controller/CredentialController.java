package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/credentials")
public class CredentialController {

    private final CredentialService credentialService;
    private final AuthenticationService authenticationService;

    public CredentialController(CredentialService credentialService, AuthenticationService authenticationService) {
        this.credentialService = credentialService;
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public String addOrUpdateNote(@ModelAttribute("credential") Credential credential, Model model) {

        Integer userId = authenticationService.getUserId();
        credential.setUserId(userId);
        if (credential.getCredentialId() == null) {
            credentialService.CreateOrUpdateCredential(credential);
        } else {
            credentialService.CreateOrUpdateCredential(credential);
        }
        return "redirect:/home";
    }

    /**@GetMapping("/delete/{credentialId}")
    public String deleteCredential(@PathVariable("credentialId") Integer credentialId, Model model) {
        Credential credential = new Credential();
        credentialService.deleteCredential(credential);
        return "home";
    }**/
    @GetMapping("/DeleteCredential/{credentialId}")
    public String deleteNote(@PathVariable Integer credentialId, Model model, Credential credential, Authentication authentication) {

        //credential.setCredentialId(credentialId);
        credentialService.deleteCredential(credential);
        return "redirect:/home";
    }
}
