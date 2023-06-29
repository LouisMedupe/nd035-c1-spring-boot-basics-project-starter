package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

public class ResultController {

    @GetMapping("/result")
    public String resultView(HttpSession httpSession, Model model){
        model.addAttribute("Success", httpSession.getAttribute("Success"));
        model.addAttribute("error", httpSession.getAttribute("error"));
        //model.addAttribute("exception", httpSession.getAttribute("exception"));
        return "result";
      }
    }
