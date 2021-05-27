package com.example.geoappdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void gameActivity(View v){
        Intent intentgameActivity = new Intent(this,GameActivity.class);
        startActivity(intentgameActivity);
    }
    public void manageActivity(View v){
        Intent intentmanageActivity = new Intent(this,ManageActivity.class);
        startActivity(intentmanageActivity);
    }
}