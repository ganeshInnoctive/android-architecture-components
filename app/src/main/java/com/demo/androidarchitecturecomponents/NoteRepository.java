package com.demo.androidarchitecturecomponents;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import java.util.List;

public class NoteRepository {

  private NoteDao noteDao;
  private LiveData<List<Note>> allNotes;

  public NoteRepository(Application application) {
    NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
    noteDao = noteDatabase.noteDao();
    allNotes = noteDao.getAllNotes();
  }

  public void insertNote(Note note) {
    new InsertNoteAsyncTask(noteDao).execute(note);
  }

  public void updateNote(Note note) {
    new UpdateNoteAsyncTask(noteDao).execute(note);
  }

  public void deleteNote(Note note) {
    new DeleteNoteAsyncTask(noteDao).execute(note);
  }

  public void deleteAllNote() {
    new DeleteAllNotesAsyncTask(noteDao).execute();
  }

  public LiveData<List<Note>> getAllNotes() {
    return allNotes;
  }

  private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
    private NoteDao noteDao;

    private InsertNoteAsyncTask(NoteDao noteDao) {
      this.noteDao = noteDao;
    }

    @Override protected Void doInBackground(Note... notes) {
      noteDao.insertNote(notes[0]);
      return null;
    }
  }

  private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
    private NoteDao noteDao;

    private UpdateNoteAsyncTask(NoteDao noteDao) {
      this.noteDao = noteDao;
    }

    @Override protected Void doInBackground(Note... notes) {
      noteDao.updateNote(notes[0]);
      return null;
    }
  }

  private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
    private NoteDao noteDao;

    private DeleteNoteAsyncTask(NoteDao noteDao) {
      this.noteDao = noteDao;
    }

    @Override protected Void doInBackground(Note... notes) {
      noteDao.deleteNote(notes[0]);
      return null;
    }
  }

  private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
    private NoteDao noteDao;

    private DeleteAllNotesAsyncTask(NoteDao noteDao) {
      this.noteDao = noteDao;
    }

    @Override protected Void doInBackground(Void... voids) {
      noteDao.deleteAllNotes();
      return null;
    }
  }
}
