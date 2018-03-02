package com.example.crud_notes.controller;

import com.example.crud_notes.bean.Note;
import com.example.crud_notes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//create REST API
@RestController
@RequestMapping("/api")
public class NoteController {
    @Autowired
    NoteRepository noteRepository;
    // get all notes
    @GetMapping("/notes")
    //@RequestMapping(value="/notes", method=RequestMethod.GET)
    public List<Note> getAllNotes(){
        return noteRepository.findAll();
    }

    // create a new note
    @PostMapping("/notes")
    public Note createNote(@Valid @RequestBody Note note){
        //@Valid ---> note not empty or null ,otherwise return 400 bBadRequest
        return noteRepository.save(note);
    }

    // get a single note
    @GetMapping("/notes/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable(value = "id")Long noteId){
        Note note = noteRepository.getOne(noteId);
        if(note == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(note);
    }

    // update a note
    @PutMapping("/notes/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable(value = "id") Long noteId,
                                           @Valid @RequestBody Note noteDetails) {
        Note note = noteRepository.getOne(noteId);
        if(note == null) {
            return ResponseEntity.notFound().build();
        }
        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());

        Note updatedNote = noteRepository.save(note);
        return ResponseEntity.ok(updatedNote);
    }
    // delete a note
    @DeleteMapping("/notes/{id}")
    public ResponseEntity<Note> deleteNote(@PathVariable(value = "id") Long noteId) {
        Note note = noteRepository.getOne(noteId);
        if(note == null) {
            return ResponseEntity.notFound().build();
        }

        noteRepository.delete(note);
        return ResponseEntity.ok().build();
    }
}
