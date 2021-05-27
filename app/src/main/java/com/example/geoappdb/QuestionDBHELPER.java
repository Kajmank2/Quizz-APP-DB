package com.example.geoappdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QuestionDBHELPER extends SQLiteOpenHelper {

    public final static String DB_NAME = "Question Db";
    public final static int DB_VERSION = 1;
    public final static String TABLE_NAME = "Questions";
    // column names
    public final static String _ID = "id";
    public final static String COUNTRY = "country";
    public final static String CAPITAL = "capital";
    public final static String POPULATION = "popultation";
    public final static String CURRENCY = "currency";
    public final static String[] columns = {_ID, COUNTRY, CAPITAL, POPULATION, CURRENCY};

    protected Context context;

    protected QuestionDBHELPER(Context context){
        super(context,DB_NAME,null,DB_VERSION);
        this.context=context;
}

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE "+TABLE_NAME+"("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COUNTRY + " TEXT NOT NULL, "
            + CAPITAL + " TEXT NOT NULL, "
            + POPULATION + " INTEGER, "
            + CURRENCY + " TEXT NOT NULL); ");
        System.out.println("creation of the DB table named: "+TABLE_NAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public void deleteDB(){
    context.deleteDatabase(DB_NAME);
    }
}
