package com.example.geoappdb;

import android.content.ContentValues;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class ManageActivity extends AppCompatActivity {
    QuestionDBHELPER questionDBHELPER;
    SQLiteDatabase sqldb=null;
    TextView tvdbinfo;
    Button querybtn,entrybtn,coutnbtn;
    EditText etcountry,etcapital,etpopulation,etcurrency;
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
        int population = Integer.parseInt(etpopulation.getText().toString());
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

    public void queryDatabase(View view) {
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
    }
    private void enableButtons(boolean enable){
        entrybtn.setEnabled(enable);
        querybtn.setEnabled(enable);
        coutnbtn.setEnabled(enable);
    }
    private void getViewsReferences() {
        tvdbinfo = findViewById(R.id.tvdbinfo);
        entrybtn=findViewById(R.id.entrybtn);
        coutnbtn=findViewById(R.id.countbtn);
        querybtn=findViewById(R.id.querybtn);
        etcurrency = findViewById(R.id.etcurrency);
        etcapital= findViewById(R.id.etcapital);
        etpopulation = findViewById(R.id.etpopulation);
        etcountry = findViewById(R.id.etcountry);
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