package com.udacity.jwdnd.course1.cloudstorage.services;

import java.util.List;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;

@Service
public class NoteService {

    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public List<Note> getAllNotes(Integer userId) {
        return noteMapper.findAllNotes(userId);
    }

    public void deleteNote(Note note) {
        noteMapper.deleteNote(note);
    }

    public void CreateOrEditNote(Note note) {
        if (note.getNoteId() == null) {
            noteMapper.insertNote(note);
        } else {
            noteMapper.updateNote(note);
        }
    }

}
