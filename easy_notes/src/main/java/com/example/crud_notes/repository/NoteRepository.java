package com.example.crud_notes.repository;

import com.example.crud_notes.bean.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note,Long>{
}
