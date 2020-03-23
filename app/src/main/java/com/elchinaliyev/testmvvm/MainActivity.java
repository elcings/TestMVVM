package com.elchinaliyev.testmvvm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.elchinaliyev.testmvvm.Adapter.NoteAdapter;
import com.elchinaliyev.testmvvm.Model.Note;
import com.elchinaliyev.testmvvm.ViewModel.NoteViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int ADD_NOTE_REQUEST=1;
    NoteViewModel noteViewModel;
    RecyclerView recView;
    NoteAdapter adapter;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recView = findViewById(R.id.recView);
        floatingActionButton=findViewById(R.id.fltBtn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add=new Intent(MainActivity.this,AddActivity.class);
                startActivityForResult(add,ADD_NOTE_REQUEST);
            }
        });
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.setHasFixedSize(false);
        noteViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(NoteViewModel.class);
        adapter = new NoteAdapter();
        recView.setAdapter(adapter);

        noteViewModel.getGetAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.submitList(notes);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                noteViewModel.delete(adapter.getNoteAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recView);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==ADD_NOTE_REQUEST&&resultCode==RESULT_OK)
        {
            String title=data.getStringExtra("Title");
            String description=data.getStringExtra("Description");
            String priority=String.valueOf(data.getIntExtra("Priority",1));
            Note not=new Note(title,description,priority);
            noteViewModel.Insert(not);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
