package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;
import org.apache.ibatis.annotations.*;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
@Mapper
public interface NoteMapper {

    @Delete("DELETE FROM NOTES WHERE noteId = #{noteId} AND userId = #{userId}")
    void deleteNote(Note note);

    @Select("SELECT * FROM NOTES WHERE userId = #{userId}")
    List<Note> findAllNotes(Integer userId);

    @Update("UPDATE NOTES SET noteTitle = #{noteTitle}, noteDescription = #{noteDescription} WHERE noteId = #{noteId}")
    void updateNote(Note note);

    @Insert("INSERT INTO NOTES (noteId, noteTitle, noteDescription, userId) VALUES (#{noteId}, #{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    void insertNote(Note note);

}

