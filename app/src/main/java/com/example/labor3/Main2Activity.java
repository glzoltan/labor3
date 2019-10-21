package com.example.labor3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private Button btn2,btn3;
    private DatabaseHelper db;
    private EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        db = new DatabaseHelper(this);
        btn2 = (Button)findViewById(R.id.button3);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = (EditText)findViewById(R.id.editText4);
                String hobby=text.getText().toString();
                Intent intent = getIntent();
                String name = intent.getStringExtra("elso");
                //db.onUpgrade(db2,1,1);
                long id = db.insertHobby(hobby,name);
               /* int n= db.getHobbiesCount();
                EditText szoveg = findViewById(R.id.editText4);
                szoveg.setText(Integer.toString(n));*/
            }
        });
        btn3 = (Button)findViewById(R.id.button4);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });
    }
    public void openActivity3(){
        Intent intent = new Intent(this, Main3Activity.class);
        startActivity(intent);
    }
}
