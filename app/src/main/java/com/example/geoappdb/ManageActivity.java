package com.example.geoappdb;

import android.content.ContentValues;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class ManageActivity extends AppCompatActivity {
    QuestionDBHELPER questionDBHELPER;
    SQLiteDatabase sqldb=null;
    TextView tvdbinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_activity);
        tvdbinfo=findViewById(R.id.tvdbinfo);
    }
    public void creation(View v){
    questionDBHELPER = new QuestionDBHELPER(this);
    sqldb=questionDBHELPER.getWritableDatabase();
        Toast.makeText(this,"DB name:" + questionDBHELPER.getDatabaseName(),
                Toast.LENGTH_LONG).show();
        System.out.println("=========== \n"+questionDBHELPER.getDatabaseName());
        tvdbinfo.setText("EXTRA EXTRA DATABASE WORK");
    }
    public void insertEntry(View view) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionDBHELPER.COUNTRY,"Italy");
        cv.put(QuestionDBHELPER.CAPITAL,"Rome");
        cv.put(QuestionDBHELPER.POPULATION,62);
        cv.put(QuestionDBHELPER.CURRENCY,"Euro");
        sqldb.insert(QuestionDBHELPER.TABLE_NAME,null,cv);
        //Italy:Rome:62:Euro
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
}