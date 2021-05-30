package com.example.geoappdb;

import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
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
    String CurrQuestion,CurrCuntry,FalseQuestion1,FalseQuestion2,FalseQuestion3,trueAnswer;
    Random alea;
    Button startbtn,button1,button2,button3,querybttn;
    TextView ansText,question;
    Integer count=0;
    ArrayList<String> questionList = new ArrayList<>();

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
        enableButtons(true);
        question.setText("Nice ! click no RandButton in aim to give a question");
        startbtn.setEnabled(false);
        enableButton(false);
    }
    public void Validate1(View v){
    querybttn.setEnabled(true);
    enableButton(false);
    colorButtons();
    if (button1.getText().toString()==trueAnswer){
        button1.setBackgroundColor(Color.GREEN);
        count++;
        ansText.setText(
                "COUNT ===>  " + count.toString());
    }
    button2.setBackgroundColor(Color.RED);
    button3.setBackgroundColor(Color.RED);
    }
    public void Validate2(View v){
        querybttn.setEnabled(true);
        enableButton(false);
        colorButtons();
        if (button2.getText().toString()==trueAnswer) {
            button2.setBackgroundColor(Color.GREEN);
            count++;
            ansText.setText(
                    "COUNT ===>  " + count.toString() );
        }
        button1.setBackgroundColor(Color.RED);
        button3.setBackgroundColor(Color.RED);
    }
    public void Validate3(View v){
        querybttn.setEnabled(true);
        enableButton(false);
        colorButtons();
        if (button3.getText().toString()==trueAnswer) {
            button3.setBackgroundColor(Color.GREEN);
            count++;
            ansText.setText(
                    "COUNT ===>  " + count.toString());
        }
        button1.setBackgroundColor(Color.RED);
        button2.setBackgroundColor(Color.RED);
    }

    public void queryDatabaseBUTTNOS(View view) {
        querybttn.setEnabled(false);
        enableButton(true);
        colorButtons();
        Cursor entries = sqldb.query(QuestionDBHELPER.TABLE_NAME,
                QuestionDBHELPER.columns,
                null,
                null,
                null,
                null, "RANDOM()","1"
        );
        processCursorCountry(entries);
        // COuntry with false
        Cursor entriesFalse = sqldb.query(QuestionDBHELPER.TABLE_NAME,
                QuestionDBHELPER.columns,
                null,
                null,
                null,
                null, "RANDOM()","3"
        );
        processCursorCountryFalse(entriesFalse);



        String selection = QuestionDBHELPER.COUNTRY+"=?";
        // Add Arguments to Button  CAPITAL
        String[] selectionArguments = {CurrCuntry};
        Cursor entrCapital = sqldb.query(QuestionDBHELPER.TABLE_NAME,
                QuestionDBHELPER.columns,
                selection,
                selectionArguments,
                null,
                null,null,null);
        processCursorCapital(entrCapital);

    }
    public void querySpecial(View view) {
        Cursor entriesFalse = sqldb.query(QuestionDBHELPER.TABLE_NAME,
                QuestionDBHELPER.columns,
                null,
                null,
                null,
                null, "RANDOM()","3"
        );
        processCursorCountryFalse(entriesFalse);
    }

    private void processCursorCapital(Cursor c) {
        if(CurrQuestion==questionList.get(0)){
             ArrayList<String> allRecords = new ArrayList<>();
            String entry = new String();
            c.moveToFirst();
            while (!c.isAfterLast()){
                entry="";
                for(int colnb=2;colnb<3;colnb++){
                    switch (c.getType(colnb)){
                        case Cursor.FIELD_TYPE_STRING:
                            entry+=c.getString(colnb);
                            break;
                        case Cursor.FIELD_TYPE_INTEGER:
                            entry+=c.getInt(colnb);
                            break;
                    }
                }
                allRecords.add(entry);
                c.moveToNext();
            }
            String allEntries = new String();
            for (String line: allRecords)
                allEntries+=line;

            // TRUE FOR BUTTON
            int val = 1+alea.nextInt(4);
            if (val==1){
                button1.setText(allEntries);
            }else if(val==2){
                button2.setText(allEntries);
            }else
                button3.setText(allEntries);
            trueAnswer=allEntries;
        }else if(CurrQuestion==questionList.get(1)){
            ArrayList<String> allPopulationFalse = new ArrayList<>();
            c.moveToFirst();
            String entry = new String();
            while (!c.isAfterLast()){
                entry="";
                for(int colnb=3;colnb<4;colnb++){
                    switch (c.getType(colnb)){
                        case Cursor.FIELD_TYPE_INTEGER:
                            entry+=c.getInt(colnb);
                            break;
                    }
                }
                allPopulationFalse.add(entry);
                c.moveToNext();
            }

            String allEntries = new String();
            for (String line: allPopulationFalse)
                allEntries+=line;

            // TRUE FOR BUTTON
            int val = 1+alea.nextInt(4);
            if (val==1){
                button1.setText(allEntries);
            }else if(val==2){
                button2.setText(allEntries);
            }else
                button3.setText(allEntries);
            trueAnswer=allEntries;
        }else{
            ArrayList<String> allCurrencyFalse = new ArrayList<>();
            c.moveToFirst();
            String entry = new String();
            while (!c.isAfterLast()){
                entry="";
                for(int colnb=4;colnb<5;colnb++){
                    switch (c.getType(colnb)){
                        case Cursor.FIELD_TYPE_STRING:
                            entry+=c.getString(colnb);
                            break;
                    }
                }
                allCurrencyFalse.add(entry);
                c.moveToNext();
            }
            String allEntries = new String();
            for (String line: allCurrencyFalse)
                allEntries+=line;

            // TRUE FOR BUTTON
            int val = 1+alea.nextInt(4);
            if (val==1){
                button1.setText(allEntries);
            }else if(val==2){
                button2.setText(allEntries);
            }else
                button3.setText(allEntries);
            trueAnswer=allEntries;
        }

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
        questionList.add("What is the Capitalize in ");
        questionList.add("How many popopulation is in [k]");
        questionList.add("What is the currency in");
        int val = 0+alea.nextInt(3);
        question.setText(questionList.get(val));

        String allEntries = new String();
        for (String line: allCountry)
            allEntries+=line;
        question.setText(questionList.get(val)+ " " +allEntries);
        CurrCuntry=allEntries;
        // QUESTION ----------------------------> VALUE
        CurrQuestion=questionList.get(val);
    }
//NAMES FOR BUTTON METHOD
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
                }
            }
            allCountry.add(entry);
            c.moveToNext();
        }

        //String allEntries = new String();
        //for(String line: allCountry)
          //  allEntries+=line+"\n";
        // IF FOR Questions ==== CAPITAL
        if(CurrQuestion==questionList.get(0)){
            ArrayList<String> allCapitalizeFalse = new ArrayList<>();
            c.moveToFirst();

            while (!c.isAfterLast()){
                entry="";
                for(int colnb=2;colnb<3;colnb++){
                    switch (c.getType(colnb)){
                        case Cursor.FIELD_TYPE_STRING:
                            entry+=c.getString(colnb);
                            break;
                    }
                }
                allCapitalizeFalse.add(entry);
                c.moveToNext();
            }

            button1.setText(allCapitalizeFalse.get(0));
            button2.setText(allCapitalizeFalse.get(1));
            button3.setText(allCapitalizeFalse.get(2));
        }else if(CurrQuestion==questionList.get(1)){
            ArrayList<String> allPopulationFalse = new ArrayList<>();
            c.moveToFirst();

            while (!c.isAfterLast()){
                entry="";
                for(int colnb=3;colnb<4;colnb++){
                    switch (c.getType(colnb)){
                        case Cursor.FIELD_TYPE_INTEGER:
                            entry+=c.getInt(colnb);
                            break;
                    }
                }
                allPopulationFalse.add(entry);
                c.moveToNext();
            }

            button1.setText(allPopulationFalse.get(0));
            button2.setText(allPopulationFalse.get(1));
            button3.setText(allPopulationFalse.get(2));
        }else if(CurrQuestion==questionList.get(2)){
            ArrayList<String> allCurrencyFalse = new ArrayList<>();
            c.moveToFirst();

            while (!c.isAfterLast()){
                entry="";
                for(int colnb=4;colnb<5;colnb++){
                    switch (c.getType(colnb)){
                        case Cursor.FIELD_TYPE_STRING:
                            entry+=c.getString(colnb);
                            break;
                    }
                }
                allCurrencyFalse.add(entry);
                c.moveToNext();
            }
            button1.setText(allCurrencyFalse.get(0));
            button2.setText(allCurrencyFalse.get(1));
            button3.setText(allCurrencyFalse.get(2));
        }

        FalseQuestion1= allCountry.get(0);
        FalseQuestion2= allCountry.get(1);
        FalseQuestion3= allCountry.get(2);
    }

    private void  getViewsReferences(){
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        startbtn=findViewById(R.id.startbtn);
        querybttn=findViewById(R.id.querybttn);


        // TEXT
        ansText=findViewById(R.id.ansText);
        question=findViewById(R.id.question);
    }
    private void enableButtons(boolean enable) {
    button1.setEnabled(enable);
    button2.setEnabled(enable);
    button3.setEnabled(enable);
    querybttn.setEnabled(enable);
    }
    private void colorButtons() {
        button1.setBackgroundColor(Color.GRAY);
        button2.setBackgroundColor(Color.GRAY);
        button3.setBackgroundColor(Color.GRAY);
    }
    private void enableButton(boolean enable) {
        button1.setEnabled(enable);
        button2.setEnabled(enable);
        button3.setEnabled(enable);
    }
}
