package com.example.voleyapp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.voleyapp.R;

public class VictoryActivity extends Activity implements View.OnClickListener{
    private final static String WIN_BLUE = "BLUE";
    private final static String WIN_RED = "RED";


    private LinearLayout llBackground;
    private TextView tvVictoryText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victory);
        Bundle bundle = getIntent().getExtras();
        String winner = bundle.getString("winner");
        initComponents();
        addListeners();

        showVictory(winner);
    }

    private void showVictory(String winner) {
        if(winner.equals(WIN_BLUE)){
            llBackground.setBackgroundColor(llBackground.getContext().getResources().getColor(R.color.blue));
            tvVictoryText.setText(R.string.blue_victory);
        }else if(winner.equals(WIN_RED)){
            llBackground.setBackgroundColor(llBackground.getContext().getResources().getColor(R.color.red));
            tvVictoryText.setText(R.string.red_victory);
        }else{
            llBackground.setBackgroundColor(llBackground.getContext().getResources().getColor(R.color.red));
            tvVictoryText.setText(R.string.error);
        }
    }

    private void addListeners() {
        llBackground.setOnClickListener(this);
    }

    private void initComponents() {
        llBackground = findViewById(R.id.background);
        tvVictoryText = findViewById(R.id.victoryText);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.background:
                Intent intent = new Intent(this, MainActivity.class);
                startActivityForResult(intent, 0);
                break;
        }
    }
}
