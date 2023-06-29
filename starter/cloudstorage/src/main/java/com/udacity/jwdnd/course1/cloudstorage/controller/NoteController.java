package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;
    private final AuthenticationService authenticationService;

    public NoteController(NoteService noteService, AuthenticationService authenticationService) {
        this.noteService = noteService;
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public String CreateOrEditNote(@ModelAttribute("note") Note note, Model model) {
        try {
            Integer userId = authenticationService.getUserId();
            note.setUserId(userId);

            //Creating file or Updating File
            if (note.getNoteId() == null) {
            noteService.CreateOrEditNote(note);
            model.addAttribute("success",true);
            }
            else {
            noteService.CreateOrEditNote(note);
            model.addAttribute("success",true);

            }
        }
        catch (Exception First) {
            model.addAttribute("error",true);
        }
        return "result";
    }


    @GetMapping("/DeleteNote/{noteId}")
    public String deleteNote(@PathVariable Integer noteId, Model model,Note note) {
        Integer userId = authenticationService.getUserId();
        note.setUserId(userId);
        noteService.deleteNote(note);
        model.addAttribute("success",true);
        return "result";
    }

}
