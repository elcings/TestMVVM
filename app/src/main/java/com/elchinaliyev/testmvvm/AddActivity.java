package com.elchinaliyev.testmvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;

public class AddActivity extends AppCompatActivity {

    EditText title;
    EditText description;
    NumberPicker numPic;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        title=findViewById(R.id.tit);
        description=findViewById(R.id.desc);
        numPic=findViewById(R.id.pri);
        numPic.setMinValue(1);
        numPic.setMaxValue(10);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Note");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.note_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        switch (id)
        {
            case R.id.save:
                saveData();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    private void saveData() {
       String tit=title.getText().toString();
       String desc=description.getText().toString();
       int pri=numPic.getValue();

       if(tit.trim().isEmpty()||desc.trim().isEmpty())
           return;
        Intent data=new Intent();
        data.putExtra("Title",tit);
        data.putExtra("Description",desc);
        data.putExtra("Priority",pri);
        setResult(RESULT_OK,data);
        finish();
    }
}
