package com.example.geoappdb;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity{
    String a,b,c,d;
    Button button1,button2,button3,button4;
    TextView ansText;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);


        //BTN IDs
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        //TEXTVIEW
        ansText=findViewById(R.id.ansText);
    }
    public void submit(View v){

    }
}
