package com.example.geoappdb;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ManageActivity extends AppCompatActivity {
    QuestionDBHELPER questionDBHELPER;
    SQLiteDatabase sqldb=null;
    TextView tvdbinfo;
    Button querybtn,entrybtn,coutnbtn,displaybtn;
    EditText etcountry,etcapital,etpopulation,etcurrency;
    ListView lvforquestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_activity);
        getViewsReferences();
        enableButtons(false);
    }
    public void creation(View v){
    questionDBHELPER = new QuestionDBHELPER(this);
    sqldb=questionDBHELPER.getWritableDatabase();
        Toast.makeText(this,"DB name:" + questionDBHELPER.getDatabaseName(),
                Toast.LENGTH_LONG).show();
        System.out.println("=========== \n"+questionDBHELPER.getDatabaseName());
        tvdbinfo.setText("EXTRA EXTRA DATABASE WORK");
        enableButtons(true);
    }
    public void insertEntry(View view) {
        String country = etcountry.getText().toString();
        String capital = etcapital.getText().toString();
        int population;
        try {
            population = Integer.parseInt(etpopulation.getText().toString());
        } catch(NumberFormatException nfe) { population = 0; }
        String currency = etcurrency.getText().toString();
        if(!country.equals("")&&!capital.equals("")){
            ContentValues newRecord=new ContentValues();
            newRecord.put(QuestionDBHELPER.COUNTRY,country);
            newRecord.put(QuestionDBHELPER.CAPITAL,capital);
            newRecord.put(QuestionDBHELPER.POPULATION,population);
            newRecord.put(QuestionDBHELPER.CURRENCY,currency);
            sqldb.insert(QuestionDBHELPER.TABLE_NAME,null,newRecord);
        }
    }
    //QUERY OF ALL VALUE
    public void queryDatabase(View view) {
        Cursor entries = sqldb.query(QuestionDBHELPER.TABLE_NAME,
                                    QuestionDBHELPER.columns,
                null,
                null,
                null,
                null,null
                );
       // processCursor(entries);
        createQuestionFromCursor(entries);
    }
    public void querySpecial(View v) {
        // all National
        String selection = QuestionDBHELPER.COUNTRY+"=?";
        String[] selectionArguments = {"asd"};
        Cursor entries = sqldb.query(QuestionDBHELPER.TABLE_NAME,
                QuestionDBHELPER.columns,
                selection,
                selectionArguments,
                null,
                null,null);
        processCursor(entries);
    }
    private void  createQuestionFromCursor(Cursor c){
        ArrayList<Question> questionsFromQuery= new ArrayList<>();
        c.moveToFirst();
        String cols[] = c.getColumnNames();
        while (!c.isAfterLast()){
            String capital=null,country=null,currency=null;
            Integer population=0;
            for(int colnb=0;colnb<c.getColumnCount();colnb++){
                switch (c.getType(colnb)){
                    case Cursor.FIELD_TYPE_STRING:
                        String value = c.getString(colnb);
                        if(cols[colnb].equals(QuestionDBHELPER.COUNTRY)) country = value;
                        else if(cols[colnb].equals(QuestionDBHELPER.CAPITAL)) capital = value;
                        else if(cols[colnb].equals(QuestionDBHELPER.CURRENCY)) currency = value;
                        break;
                    case Cursor.FIELD_TYPE_INTEGER:
                        population = c.getInt(colnb);
                        break;
                }
            }
            Question theNewQuestion = new Question(country,capital,population,currency);
            questionsFromQuery.add(theNewQuestion);
            c.moveToNext();
        }
        c.close();
        QuestionAdapter ca = new QuestionAdapter(this,questionsFromQuery);
        lvforquestion.setAdapter(ca);
        //display layout
        //NEW ACTIVITY
    }


    private void processCursor(Cursor c) {
        ArrayList<String> allRecords = new ArrayList<>();
        String entry = new String();    //for displaing content intoTV
        c.moveToFirst();
        String cols[] = c.getColumnNames();
        while (!c.isAfterLast()){
            entry="";
            for(int colnb=0;colnb<c.getColumnCount();colnb++){
                switch (c.getType(colnb)){
                    case Cursor.FIELD_TYPE_STRING:
                        entry+=cols[colnb]+": "+c.getString(colnb)+ " ";
                        break;
                    case Cursor.FIELD_TYPE_INTEGER:
                        entry+="\n"+cols[colnb]+": "+c.getInt(colnb);
                }
            }
         allRecords.add(entry);
            c.moveToNext();
        }
        String allEntries = new String();
        for (String line: allRecords)
            allEntries+=line+"\n";
        tvdbinfo.setText(allEntries);
    }

    public void countRecords(View view) {
        long nbEntries = DatabaseUtils.queryNumEntries(sqldb,
                QuestionDBHELPER.TABLE_NAME);
        Toast.makeText(this,"nb records:"+nbEntries,
                Toast.LENGTH_LONG).show();
        System.out.println("nb records:"+nbEntries);
        tvdbinfo.setText("Number of entries:"+nbEntries);
    }


    public void back(View view) {
    }

    public void deleteDB(View view) {
        questionDBHELPER.deleteDB();
        tvdbinfo.setText("");
    }
    private void enableButtons(boolean enable){
        entrybtn.setEnabled(enable);
        querybtn.setEnabled(enable);
        coutnbtn.setEnabled(enable);
        displaybtn.setEnabled(enable);
    }
    private void getViewsReferences() {
        tvdbinfo = findViewById(R.id.tvdbinfo);
        entrybtn=findViewById(R.id.entrybtn);
        coutnbtn=findViewById(R.id.countbtn);
        querybtn=findViewById(R.id.querybtn);
        displaybtn.findViewById(R.id.displaybtn);
        etcurrency = findViewById(R.id.etcurrency);
        etcapital= findViewById(R.id.etcapital);
        etpopulation = findViewById(R.id.etpopulation);
        etcountry = findViewById(R.id.etcountry);
        lvforquestion = findViewById(R.id.lvforquestion);
    }











    // ====================EXAMPLE FROM LESSON=============//
    public void example(View view) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionDBHELPER.COUNTRY,"Italy");
        cv.put(QuestionDBHELPER.CAPITAL,"Rome");
        cv.put(QuestionDBHELPER.POPULATION,62);
        cv.put(QuestionDBHELPER.CURRENCY,"Euro");
        sqldb.insert(QuestionDBHELPER.TABLE_NAME,null,cv);
        //Italy:Rome:62:Euro
    }

}