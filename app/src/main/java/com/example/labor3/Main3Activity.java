package com.example.labor3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class Main3Activity extends AppCompatActivity {
    private DatabaseHelper db;
    private static final String Tag = "Main3Activity";
    private ListView nListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        db = new DatabaseHelper(this);
        int n= db.getHobbiesCount();
        nListView =(ListView)findViewById(R.id.listview);
        List<String> hObbies=db.getAllHObbies();
        ListAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,hObbies);
        nListView.setAdapter(adapter);
    }
}
