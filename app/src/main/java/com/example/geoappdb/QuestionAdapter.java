
package com.example.geoappdb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class QuestionAdapter extends ArrayAdapter<Question> {
    Context context;
    public QuestionAdapter(@NonNull Context context, List<Question> theQuestion) {
        super(context, 0,theQuestion);
        this.context=context;
    }

    public View getView(int position, View theView, ViewGroup myListView){
        if (theView==null){
            theView= LayoutInflater.from(context).inflate(R.layout.infodb_activity,myListView,false);
        }
        TextView tvCapital = theView.findViewById(R.id.tvcapital);
        TextView tvCountry = theView.findViewById(R.id.tvcountry);
        TextView tvPopulation = theView.findViewById(R.id.tvpopulation);
        TextView tvCurrency = theView.findViewById(R.id.tvcurrency);

        Question questionToDisplay=(Question)getItem(position);
        tvCapital.setText(questionToDisplay.getCapital());
        tvCountry.setText(questionToDisplay.getCountry());
        tvPopulation.setText(questionToDisplay.getPopulation());
        tvCurrency.setText(questionToDisplay.getCurrency());
        return theView;
    }
}


