package com.elchinaliyev.testmvvm.Model;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface noteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Note note);
    @Update
    void update(Note note);
    @Delete
    void delete(Note note);
    @Query("DELETE FROM note_table")
    void deleteAllNotes();
    @Query("SELECT* FROM note_table ORDER BY priority DESC")
    LiveData<List<Note>>getAllNotes();
}
