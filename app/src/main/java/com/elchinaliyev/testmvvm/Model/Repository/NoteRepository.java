package com.elchinaliyev.testmvvm.Model.Repository;
import android.app.Application;
import android.os.AsyncTask;

import com.elchinaliyev.testmvvm.Model.Note;
import com.elchinaliyev.testmvvm.Model.NoteDatabase;
import com.elchinaliyev.testmvvm.Model.noteDao;
import java.util.List;
import androidx.lifecycle.LiveData;

public class NoteRepository {
    private noteDao noteDao;
    private LiveData<List<Note>>allNotes;

    public NoteRepository(Application app)
    {
        NoteDatabase db=NoteDatabase.getInstance(app);
        noteDao=db.noteDao();
        allNotes=noteDao.getAllNotes();
    }
    public LiveData<List<Note>>getAllNotes()
    {
        return allNotes;
    }
    public void insert( Note note) {
        new InsertAsyncTask(noteDao).execute(note);
    }
    public void update(Note note)
    {
       new UpdateAsyncTask(noteDao).execute(note);
    }
    public void delete(Note note)
    {
        new DeleteAsyncTask(noteDao).execute(note);
    }
    public void deleteAllNotes()
    {
        new DeleteAllAsyncTask(noteDao).execute();
    }

    private static class InsertAsyncTask extends AsyncTask<Note,Void,Void>{
        private noteDao noteDao;
        private InsertAsyncTask(noteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Note,Void,Void>{
        private noteDao noteDao;
        private UpdateAsyncTask(noteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Note,Void,Void>{
        private noteDao noteDao;
        private DeleteAsyncTask(noteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void>{
        private noteDao noteDao;
        private DeleteAllAsyncTask(noteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }

}
