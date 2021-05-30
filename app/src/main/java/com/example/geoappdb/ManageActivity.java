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
    Button querybtn,entrybtn,coutnbtn,initbtn;
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
        processCursor(entries);
    }
    public void queryDatabaseAdapter(View view) {
        Cursor entries = sqldb.query(QuestionDBHELPER.TABLE_NAME,
                QuestionDBHELPER.columns,
                null,
                null,
                null,
                null,null
        );
        createQuestionFromCursor(entries);
    }
    public void querySpecial(View v) {
        // all National
        String selection = QuestionDBHELPER.COUNTRY+"=?";
        String[] selectionArguments = {"Poland"};
        Cursor entries = sqldb.query(QuestionDBHELPER.TABLE_NAME,
                QuestionDBHELPER.columns,
                selection,
                selectionArguments,
                null,
                null,null,null);
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
                        break;
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
        initbtn.setEnabled(enable);
    }
    private void getViewsReferences() {
        tvdbinfo = findViewById(R.id.tvdbinfo);
        entrybtn=findViewById(R.id.entrybtn);
        coutnbtn=findViewById(R.id.countbtn);
        querybtn=findViewById(R.id.querybtn);
        initbtn=findViewById(R.id.initbtn);
        // displaybtn.findViewById(R.id.displaybtn);
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
    //==============INIT=======================//
    public void Initialize(View view) {
        ContentValues cv = new ContentValues();

        cv.put(QuestionDBHELPER.COUNTRY,"Italy");
        cv.put(QuestionDBHELPER.CAPITAL,"Rome");
        cv.put(QuestionDBHELPER.POPULATION,62);
        cv.put(QuestionDBHELPER.CURRENCY,"Euro");

        sqldb.insert(QuestionDBHELPER.TABLE_NAME,null,cv);

        cv.put(QuestionDBHELPER.COUNTRY,"German");
        cv.put(QuestionDBHELPER.CAPITAL,"Berlin");
        cv.put(QuestionDBHELPER.POPULATION,82);
        cv.put(QuestionDBHELPER.CURRENCY,"Euro");

        sqldb.insert(QuestionDBHELPER.TABLE_NAME,null,cv);

        cv.put(QuestionDBHELPER.COUNTRY,"France");
        cv.put(QuestionDBHELPER.CAPITAL,"Paris");
        cv.put(QuestionDBHELPER.POPULATION,66);
        cv.put(QuestionDBHELPER.CURRENCY,"Euro");

        sqldb.insert(QuestionDBHELPER.TABLE_NAME,null,cv);

        cv.put(QuestionDBHELPER.COUNTRY,"China");
        cv.put(QuestionDBHELPER.CAPITAL,"Beijing");
        cv.put(QuestionDBHELPER.POPULATION,1382);
        cv.put(QuestionDBHELPER.CURRENCY,"Yuan");

        sqldb.insert(QuestionDBHELPER.TABLE_NAME,null,cv);


        cv.put(QuestionDBHELPER.COUNTRY,"USA");
        cv.put(QuestionDBHELPER.CAPITAL,"Washington");
        cv.put(QuestionDBHELPER.POPULATION,325);
        cv.put(QuestionDBHELPER.CURRENCY,"Dollar");

        sqldb.insert(QuestionDBHELPER.TABLE_NAME,null,cv);

        cv.put(QuestionDBHELPER.COUNTRY,"Russia");
        cv.put(QuestionDBHELPER.CAPITAL,"Moscow");
        cv.put(QuestionDBHELPER.POPULATION,147);
        cv.put(QuestionDBHELPER.CURRENCY,"Ruble");

        sqldb.insert(QuestionDBHELPER.TABLE_NAME,null,cv);

        cv.put(QuestionDBHELPER.COUNTRY,"Japan");
        cv.put(QuestionDBHELPER.CAPITAL,"Tokyo");
        cv.put(QuestionDBHELPER.POPULATION,127);
        cv.put(QuestionDBHELPER.CURRENCY,"Yen");

        sqldb.insert(QuestionDBHELPER.TABLE_NAME,null,cv);

        cv.put(QuestionDBHELPER.COUNTRY,"Belgium");
        cv.put(QuestionDBHELPER.CAPITAL,"Brussels");
        cv.put(QuestionDBHELPER.POPULATION,10);
        cv.put(QuestionDBHELPER.CURRENCY,"Euro");

        sqldb.insert(QuestionDBHELPER.TABLE_NAME,null,cv);

        cv.put(QuestionDBHELPER.COUNTRY,"Brasil");
        cv.put(QuestionDBHELPER.CAPITAL,"Brasilia");
        cv.put(QuestionDBHELPER.POPULATION,200);
        cv.put(QuestionDBHELPER.CURRENCY,"Real");

        sqldb.insert(QuestionDBHELPER.TABLE_NAME,null,cv);

        cv.put(QuestionDBHELPER.COUNTRY,"Indonesia");
        cv.put(QuestionDBHELPER.CAPITAL,"Jakarta");
        cv.put(QuestionDBHELPER.POPULATION,250);
        cv.put(QuestionDBHELPER.CURRENCY,"Rupiah");

        sqldb.insert(QuestionDBHELPER.TABLE_NAME,null,cv);

        cv.put(QuestionDBHELPER.COUNTRY,"Argentina");
        cv.put(QuestionDBHELPER.CAPITAL,"Buenos Aires");
        cv.put(QuestionDBHELPER.POPULATION,40);
        cv.put(QuestionDBHELPER.CURRENCY,"Peso");

        sqldb.insert(QuestionDBHELPER.TABLE_NAME,null,cv);

        cv.put(QuestionDBHELPER.COUNTRY,"Poland");
        cv.put(QuestionDBHELPER.CAPITAL,"Warsaw");
        cv.put(QuestionDBHELPER.POPULATION,38);
        cv.put(QuestionDBHELPER.CURRENCY,"Zloty");

        sqldb.insert(QuestionDBHELPER.TABLE_NAME,null,cv);
        cv.put(QuestionDBHELPER.COUNTRY,"Australia");
        cv.put(QuestionDBHELPER.CAPITAL,"Canberra");
        cv.put(QuestionDBHELPER.POPULATION,24);
        cv.put(QuestionDBHELPER.CURRENCY,"Dollar");

        sqldb.insert(QuestionDBHELPER.TABLE_NAME,null,cv);

        cv.put(QuestionDBHELPER.COUNTRY,"Chile");
        cv.put(QuestionDBHELPER.CAPITAL,"Santiago");
        cv.put(QuestionDBHELPER.POPULATION,17);
        cv.put(QuestionDBHELPER.CURRENCY,"Peso");

        sqldb.insert(QuestionDBHELPER.TABLE_NAME,null,cv);

        cv.put(QuestionDBHELPER.COUNTRY,"South Korea");
        cv.put(QuestionDBHELPER.CAPITAL,"Seoul");
        cv.put(QuestionDBHELPER.POPULATION,48);
        cv.put(QuestionDBHELPER.CURRENCY,"Won");

        sqldb.insert(QuestionDBHELPER.TABLE_NAME,null,cv);

        cv.put(QuestionDBHELPER.COUNTRY,"Canada");
        cv.put(QuestionDBHELPER.CAPITAL,"Ottawa");
        cv.put(QuestionDBHELPER.POPULATION,36);
        cv.put(QuestionDBHELPER.CURRENCY,"Dollar");

        sqldb.insert(QuestionDBHELPER.TABLE_NAME,null,cv);

        cv.put(QuestionDBHELPER.COUNTRY,"Turkey");
        cv.put(QuestionDBHELPER.CAPITAL,"Ankara");
        cv.put(QuestionDBHELPER.POPULATION,77);
        cv.put(QuestionDBHELPER.CURRENCY,"Lira");

        sqldb.insert(QuestionDBHELPER.TABLE_NAME,null,cv);

        cv.put(QuestionDBHELPER.COUNTRY,"Greece");
        cv.put(QuestionDBHELPER.CAPITAL,"Athens");
        cv.put(QuestionDBHELPER.POPULATION,10);
        cv.put(QuestionDBHELPER.CURRENCY,"Euro");

        sqldb.insert(QuestionDBHELPER.TABLE_NAME,null,cv);

        cv.put(QuestionDBHELPER.COUNTRY,"Netherland");
        cv.put(QuestionDBHELPER.CAPITAL,"Amsterdam");
        cv.put(QuestionDBHELPER.POPULATION,17);
        cv.put(QuestionDBHELPER.CURRENCY,"Euro");

        sqldb.insert(QuestionDBHELPER.TABLE_NAME,null,cv);

        cv.put(QuestionDBHELPER.COUNTRY,"Egypt");
        cv.put(QuestionDBHELPER.CAPITAL,"Cairo");
        cv.put(QuestionDBHELPER.POPULATION,89);
        cv.put(QuestionDBHELPER.CURRENCY,"Pound");

        sqldb.insert(QuestionDBHELPER.TABLE_NAME,null,cv);

        cv.put(QuestionDBHELPER.COUNTRY,"Ireland");
        cv.put(QuestionDBHELPER.CAPITAL,"Dublin");
        cv.put(QuestionDBHELPER.POPULATION,4);
        cv.put(QuestionDBHELPER.CURRENCY,"Euro");

        sqldb.insert(QuestionDBHELPER.TABLE_NAME,null,cv);

        cv.put(QuestionDBHELPER.COUNTRY,"Ukraine");
        cv.put(QuestionDBHELPER.CAPITAL,"Kiev");
        cv.put(QuestionDBHELPER.POPULATION,45);
        cv.put(QuestionDBHELPER.CURRENCY,"Hryvnia");

        sqldb.insert(QuestionDBHELPER.TABLE_NAME,null,cv);

        cv.put(QuestionDBHELPER.COUNTRY,"Malaysia");
        cv.put(QuestionDBHELPER.CAPITAL,"Kuala Lumpur");
        cv.put(QuestionDBHELPER.POPULATION,28);
        cv.put(QuestionDBHELPER.CURRENCY,"Ringgit");

        sqldb.insert(QuestionDBHELPER.TABLE_NAME,null,cv);

        cv.put(QuestionDBHELPER.COUNTRY,"United Kingdom");
        cv.put(QuestionDBHELPER.CAPITAL,"London");
        cv.put(QuestionDBHELPER.POPULATION,65);
        cv.put(QuestionDBHELPER.CURRENCY,"Pound");

        sqldb.insert(QuestionDBHELPER.TABLE_NAME,null,cv);
        cv.put(QuestionDBHELPER.COUNTRY,"India");
        cv.put(QuestionDBHELPER.CAPITAL,"New Delhi");
        cv.put(QuestionDBHELPER.POPULATION,1210);
        cv.put(QuestionDBHELPER.CURRENCY,"Rupee");

        sqldb.insert(QuestionDBHELPER.TABLE_NAME,null,cv);

        cv.put(QuestionDBHELPER.COUNTRY,"Spain");
        cv.put(QuestionDBHELPER.CAPITAL,"Madrid");
        cv.put(QuestionDBHELPER.POPULATION,46);
        cv.put(QuestionDBHELPER.CURRENCY,"Euro");

        sqldb.insert(QuestionDBHELPER.TABLE_NAME,null,cv);

        //Italy:Rome:62:Euro
    }

}
/*
    Italy:Rome:62:Euro
     Germany:Berlin:82:Euro
    Poland:Warsaw:38:Zloty
    France:Paris:66:Euro
    China:Beijing:1382:Yuan
   USA:Washington:325:Dollar
   Russia:Moscow:147:Ruble
   Japan:Tokyo:127:Yen
    Belgium:Brussels:10:Euro
     Brasil:Brasilia:200:Real
      Indonesia:Jakarta:250:Rupiah
      Argentina:Buenos Aires:40:Peso
       Australia:Canberra:24:Dollar
      Canada:Ottawa:36:Dollar
      Chile:Santiago:17:Peso
     South Korea:Seoul:48:Won
        Turkey:Ankara:77:Lira
        Greece:Athens:10:Euro
        Netherland:Amsterdam:17:Euro
 Egypt:Cairo:89:Pound
        Ireland:Dublin:4:Euro
        Ukraine:Kiev:45:Hryvnia
        Malaysia:Kuala Lumpur:28:Ringgit
    United Kingdom:London:65:Pound
        India:New Delhi:1210:Rupee
        Spain:Madrid:46:Euro

 */