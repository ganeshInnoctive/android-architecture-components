package com.demo.androidarchitecturecomponents;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;

@Dao
public interface NoteDao {

  @Insert
  void insertNote(Note note);

  @Update
  void updateNote(Note note);

  @Delete
  void deleteNote(Note note);

  @Query("DELETE FROM table_note")
  void deleteAllNotes();

  @Query("SELECT * FROM table_note ORDER BY priority DESC")
  LiveData<List<Note>> getAllNotes();
}
