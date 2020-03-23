package com.elchinaliyev.testmvvm.ViewModel;

import android.app.Application;
import android.util.Log;

import com.elchinaliyev.testmvvm.Model.Note;
import com.elchinaliyev.testmvvm.Model.Repository.NoteRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository noteRepository;
    private LiveData<List<Note>>allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
       noteRepository=new NoteRepository(application);
        allNotes=noteRepository.getAllNotes();
    }

    public LiveData<List<Note>>getGetAllNotes()
    {
        return allNotes;
    }
    public void Insert(Note note)
    {
        noteRepository.insert(note);
    }
    public void Update(Note note)
    {
        noteRepository.update(note);
    }
    public void delete(Note note)
    {
        noteRepository.delete(note);
    }
    public void deleteAll()
    {
        noteRepository.deleteAllNotes();
    }
}
