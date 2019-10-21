package com.example.labor3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView nTv;
    Button nBtn;
    Calendar c;
    DatePickerDialog dpd;


    private EditText text1;
    private EditText text2;
    private EditText text3;
    private TextView text4;
    private CheckBox check1;
    private Button btn1;
    public static final String SHARED_PREFS="sharedprefs";
    public static final String Text11="text";
    public static final String Text22="text";
    public static final String Text33="text";
    public static final String Clndr1="text";
    public static final String Checkb="check1";
    private String etext1;
    private String etext2;
    private String etext3;
    private String etext4;
    private boolean echeck1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nTv =(TextView)findViewById(R.id.textView2);
        nBtn =(Button)findViewById(R.id.button);
        nBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            c= Calendar.getInstance();
            int day = c.get(Calendar.DAY_OF_MONTH);
            int month = c.get(Calendar.MONTH);
            int year = c.get(Calendar.YEAR);
            dpd = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int nYear, int nMonth, int nDay) {
                    nTv.setText(nDay + "/" + (nMonth+1)+"/"+ nYear);
                }
            },day,month,year);
            dpd.show();

            }
        });
        text1 = (EditText)findViewById(R.id.editText);
        text2 = (EditText)findViewById(R.id.editText2);
        text3 = (EditText)findViewById(R.id.editText3);
        text4 = (TextView)findViewById(R.id.textView2);
        check1 = (CheckBox)findViewById((R.id.checkBox));
        btn1= (Button)findViewById(R.id.button2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                openActivity2();
            }
        });
        loadData();
        updateViews();

    }
    public void openActivity2(){
        Intent intent = new Intent(this, Main2Activity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra("elso", message);
        startActivity(intent);
    }
    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name",text1.getText().toString());
        editor.putString("email",text2.getText().toString());
        editor.putString("password",text3.getText().toString());
        editor.putString("date",text4.getText().toString());
        editor.putBoolean("chkb",check1.isChecked());
        editor.apply();
    }
    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        etext1 = sharedPreferences.getString("name","");
        etext2 = sharedPreferences.getString("email","");
        etext3 = sharedPreferences.getString("password","");
        etext4 = sharedPreferences.getString("date","");
        echeck1 =sharedPreferences.getBoolean("chkb",false);

    }
    public void updateViews(){
        text1.setText(etext1);
        text2.setText(etext2);
        text3.setText(etext3);
        text4.setText(etext4);
        check1.setChecked(echeck1);
    }
}
