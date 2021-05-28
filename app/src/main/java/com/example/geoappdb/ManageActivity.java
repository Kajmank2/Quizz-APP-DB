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
<<<<<<< Updated upstream
=======
    Button querybtn,entrybtn,coutnbtn,initbtn;
    EditText etcountry,etcapital,etpopulation,etcurrency;
    ListView lvforquestion;
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
}
=======

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
>>>>>>> Stashed changes
