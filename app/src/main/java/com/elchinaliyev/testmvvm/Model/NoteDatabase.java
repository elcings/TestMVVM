package com.elchinaliyev.testmvvm.Model;

import android.content.Context;
import android.os.AsyncTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Note.class},version = 1,exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {
    public static NoteDatabase instance;

    public static synchronized NoteDatabase getInstance(final  Context contex) {
        if(instance==null)
        {
            instance=  Room.databaseBuilder(contex.getApplicationContext(),NoteDatabase.class,
                    "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }

    public abstract noteDao noteDao();


    private static RoomDatabase.Callback callback=new RoomDatabase.Callback()
    {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateCallback(instance).execute();
        }
    };

    private  static  class PopulateCallback extends AsyncTask<Void,Void,Void>
    {
        private noteDao dao;

        public PopulateCallback(NoteDatabase db) {
            this.dao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.insert(new Note("Test1","Description1","2"));
            dao.insert(new Note("Test2","Description2","3"));
            dao.insert(new Note("Test4","Description4","5"));
            return null;
        }
    }
}
