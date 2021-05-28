package com.example.geoappdb;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity{
    QuestionDBHELPER questionDBHELPER;
    SQLiteDatabase sqldb=null;
    String a,b,c,d;
    Button button1,button2,button3,button4 ,startbtn;
    TextView ansText,programinfotv,question;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);
        getViewsReferences();
        enableButtons(false);
    }
    public void start(View v){
        questionDBHELPER = new QuestionDBHELPER(this);
        sqldb=questionDBHELPER.getWritableDatabase();
        Toast.makeText(this,"DB name:" + questionDBHELPER.getDatabaseName(),
                Toast.LENGTH_LONG).show();
        System.out.println("=========== \n"+questionDBHELPER.getDatabaseName());
        programinfotv.setText("EXTRA EXTRA DATABASE WORK");
        enableButtons(true);
        question.setText("Nice ! click no RandButton in aim to give a question");
        startbtn.setEnabled(false);
    }
    private void randQestion(){
        ArrayList<String> questionList = new ArrayList<>();
        questionList.add("What is the Capitalize in ");
        questionList.add("How many popopulation is in [k]");
        questionList.add("What is the currency in");
        Random random
    }
    public void submit(View v){
    }
    private void  getViewsReferences(){
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        startbtn=findViewById(R.id.startbtn);
        programinfotv=findViewById(R.id.programinfotv);
        ansText=findViewById(R.id.ansText);
        question=findViewById(R.id.question);
    }
    private void enableButtons(boolean enable) {
    button1.setEnabled(enable);
    button2.setEnabled(enable);
    button3.setEnabled(enable);
    button4.setEnabled(enable);
    }
}
