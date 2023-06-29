package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import static org.junit.jupiter.api.Assertions.assertEquals;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class NoteTests {
    @LocalServerPort
    private WebDriver webdriver;
    private WebDriverWait waitdriver;
    public String BASE_URL;

    @Test
    public void NoteTests() {

        // Note creation details
        String noteTitle = "New Note";
        String noteDescription = "A new Note Test ";

        // IsHomepage Working
        waitdriver.until(ExpectedConditions.titleContains("Home"));
        assertEquals("Home", webdriver.getTitle());

        // Create a new note
        Note note = new Note(webdriver.hashCode(), waitdriver.hashCode());
        note.setNoteDescription(noteDescription);
        note.setNoteTitle(noteTitle);

        // Checking NoteTitle and Description
        assertEquals(noteTitle,note.getNoteTitle());
        assertEquals(noteDescription, note.getNoteDescription());

        // Update note
        String updateNoteTitle = "Updated Note";
        String updateNoteDescription = "This note has been edited";

        // Check how updated Details show
        note.setNoteDescription(updateNoteDescription);
        note.setNoteTitle(noteTitle);
        assertEquals(updateNoteTitle, note.getNoteTitle());
        assertEquals(updateNoteDescription,note.getNoteDescription());


}
}
