package com.example.geoappdb;

import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity{
    QuestionDBHELPER questionDBHELPER;
    SQLiteDatabase sqldb=null;
  public   String CurrQuestion,FalseQuestion1,FalseQuestion2,d;
    Random alea;
    Button startbtn,randquestionbtn,button1,button2,button3,querybttn;
    TextView ansText,programinfotv,question;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);
        alea=new Random(System.currentTimeMillis());
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


    public void randQuestion(View v){
        ArrayList<String> questionList = new ArrayList<>();
        questionList.add("What is the Capitalize in ");
        questionList.add("How many popopulation is in [k]");
        questionList.add("What is the currency in");
        int val = 0+alea.nextInt(3);
        question.setText(questionList.get(val));
    }
    public void queryDatabaseBUTTNOS(View view) {
        // COUNTRY
        Cursor entries = sqldb.query(QuestionDBHELPER.TABLE_NAME,
                QuestionDBHELPER.columns,
                null,
                null,
                null,
                null, "RANDOM()","1"
        );
        processCursorCountry(entries);
        // COuntry with false
        /*
        Cursor entriesFalse = sqldb.query(QuestionDBHELPER.TABLE_NAME,
                QuestionDBHELPER.columns,
                null,
                null,
                null,
                null, "RANDOM()","2"
        );
        processCursorCountryFalse(entriesFalse);
        
         */
        String selection = QuestionDBHELPER.COUNTRY+"=?";
        // Add Arguments to array
        String[] selectionArguments = {CurrQuestion};
        Cursor entrCapital = sqldb.query(QuestionDBHELPER.TABLE_NAME,
                QuestionDBHELPER.columns,
                selection,
                selectionArguments,
                null,
                null,null,null);
        processCursorCapital(entrCapital);

    }
    public void querySpecial(View view) {
        // all National
        String selection = QuestionDBHELPER.COUNTRY+"=?";
        // Add Arguments to array
        String[] selectionArguments = {CurrQuestion};
        Cursor entrCapital = sqldb.query(QuestionDBHELPER.TABLE_NAME,
                QuestionDBHELPER.columns,
                selection,
                selectionArguments,
                null,
                null,null,null);
        processCursorCapital(entrCapital);
    }

    private void processCursorCapital(Cursor c) {

        ArrayList<String> allRecords = new ArrayList<>();
        String entry = new String();
        c.moveToFirst();
        String cols[] = c.getColumnNames();
        while (!c.isAfterLast()){
            entry="";
            for(int colnb=2;colnb<3;colnb++){
                switch (c.getType(colnb)){
                    case Cursor.FIELD_TYPE_STRING:
                        entry+=c.getString(colnb);
                        break;
                    case Cursor.FIELD_TYPE_INTEGER:
                        entry+="\n"+cols[colnb]+": "+c.getInt(colnb);
                        break;
                }
            }
            allRecords.add(entry);
            c.moveToNext();
        }
        String allEntries = new String();
        for (String line: allRecords)
            allEntries+=line;
        programinfotv.setText(allEntries);

        // TRUE FOR BUTTON
        int val = 1+alea.nextInt(4);
        if (val==1){
            button1.setText(allEntries);
        }else if(val==2){
            button2.setText(allEntries);
        }else
            button3.setText(allEntries);

    }


    // QUESTION METHOD RANDOM
    private void processCursorCountry(Cursor c) {
        ArrayList<String> allCountry = new ArrayList<>();
        String entry = new String();    //for displaing content intoTV
        c.moveToFirst();
        String cols[] = c.getColumnNames();
        while (!c.isAfterLast()){
            entry="";
            for(int colnb=1;colnb<2;colnb++){
                switch (c.getType(colnb)){
                    case Cursor.FIELD_TYPE_STRING:
                        entry+=c.getString(colnb);
                        break;
                    case Cursor.FIELD_TYPE_INTEGER:
                        entry+="\n"+cols[colnb]+": "+c.getInt(colnb);
                        break;
                }
            }
            allCountry.add(entry);
            c.moveToNext();
        }
        // RAND QUESTION
        ArrayList<String> questionList = new ArrayList<>();
        questionList.add("What is the Capitalize in ");
        questionList.add("How many popopulation is in [k]");
        questionList.add("What is the currency in");
        int val = 0+alea.nextInt(3);
        question.setText(questionList.get(val));

        String allEntries = new String();
        for (String line: allCountry)
            allEntries+=line;
        question.setText(questionList.get(val)+ " " +allEntries);
        CurrQuestion=allEntries;
    }


    private void processCursorCountryFalse(Cursor c) {
        ArrayList<String> allCountry = new ArrayList<>();
        String entry = new String();    //for displaing content intoTV
        c.moveToFirst();
        String cols[] = c.getColumnNames();
        while (!c.isAfterLast()){
            entry="";
            for(int colnb=1;colnb<2;colnb++){
                switch (c.getType(colnb)){
                    case Cursor.FIELD_TYPE_STRING:
                        entry+=c.getString(colnb);
                        break;
                    case Cursor.FIELD_TYPE_INTEGER:
                        entry+="\n"+cols[colnb]+": "+c.getInt(colnb);
                        break;
                }
            }
            allCountry.add(entry);
            c.moveToNext();
        }
        ArrayList<String> valueFalse = new ArrayList<String>();
        valueFalse.add(entry);

        //String allEntries = new String();
        //for(String line: allCountry)
          //  allEntries+=line+"\n";
        programinfotv.setText(valueFalse.get(0)+" - " + valueFalse.get(1));
    }
    public void queryDatabase(View view) {
        Cursor entries = sqldb.query(QuestionDBHELPER.TABLE_NAME,
                QuestionDBHELPER.columns,
                null,
                null,
                null,
                null, "RANDOM()","1"
        );
        processCursorCountry(entries);
    }
    //////////////////////////
    public void submit(View v){
    }
    private void  getViewsReferences(){
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        startbtn=findViewById(R.id.startbtn);
        querybttn=findViewById(R.id.querybttn);
        randquestionbtn=findViewById(R.id.randquestionbtn);

        // TEXT
        programinfotv=findViewById(R.id.programinfotv);
        ansText=findViewById(R.id.ansText);
        question=findViewById(R.id.question);
        randquestionbtn=findViewById(R.id.randquestionbtn);
    }
    private void enableButtons(boolean enable) {
    button1.setEnabled(enable);
        button2.setEnabled(enable);
        button3.setEnabled(enable);
        querybttn.setEnabled(enable);
        randquestionbtn.setEnabled(enable);
    }
}
