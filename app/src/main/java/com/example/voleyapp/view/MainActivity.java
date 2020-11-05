package com.example.voleyapp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.voleyapp.R;
import com.example.voleyapp.model.TypeVolleyMatch;

public class MainActivity extends Activity implements View.OnClickListener{
    private Button btLargeMatch;
    private Button btMediumMatch;
    private Button btShortMatch;
    private Button btHeatingMatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initComponentes();
        addListeners();
    }

    private void initComponentes() {
        btLargeMatch = findViewById(R.id.button_large_match);
        btMediumMatch = findViewById(R.id.button_medium_match);
        btShortMatch = findViewById(R.id.button_short_match);
        btHeatingMatch = findViewById(R.id.button_heating_match);
    }

    private void addListeners(){
        btLargeMatch.setOnClickListener(this);
        btMediumMatch.setOnClickListener(this);
        btShortMatch.setOnClickListener(this);
        btHeatingMatch.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_large_match:
                starMatch(TypeVolleyMatch.LARGO);
                break;
            case  R.id.button_medium_match:
                starMatch(TypeVolleyMatch.MEDIO);
                break;
            case R.id.button_short_match:
                starMatch(TypeVolleyMatch.CORTO);
                break;
            case R.id.button_heating_match:
                starMatch(TypeVolleyMatch.CALENTAMIENTO);
                break;
        }

    }

    private void starMatch(TypeVolleyMatch typeVolleyMatch) {
        Intent intent = new Intent(this, MatchActivity.class);
        intent.putExtra("typeMatchPoints", typeVolleyMatch.getPoints());
        intent.putExtra("typeMatchSets", typeVolleyMatch.getSets());
        startActivityForResult(intent, 0);
    }
}
