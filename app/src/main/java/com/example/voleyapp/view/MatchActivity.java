package com.example.voleyapp.view;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Bundle;
import com.example.voleyapp.R;
import com.example.voleyapp.control.VolleyMatch;
import com.example.voleyapp.model.ReactionScorePoint;
import com.example.voleyapp.model.TypeVolleyMatch;


public class MatchActivity extends Activity implements View.OnClickListener{
    private final static String WIN_BLUE = "BLUE";
    private final static String WIN_RED = "RED";

    private ImageButton btPlusPointRight;
    private ImageButton btPlusPointLeft;
    private ImageButton btSubtractPointRight;
    private ImageButton btSubtractPointLeft;

    private TextView tvPointsRight;
    private TextView tvPointsLeft;
    private TextView tvSetsRight;
    private TextView tvSetsLeft;

    private ImageView ivBallRight;
    private ImageView ivBallLeft;

    private boolean changeField; //Indica si en la vista se han cambiado de campo los equipos

    private VolleyMatch volleyMatch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        Bundle bundle = getIntent().getExtras();
        int typeMatchPoints = bundle.getInt("typeMatchPoints");
        int typeMatchSets = bundle.getInt("typeMatchSets");
        TypeVolleyMatch typeVolleyMatch = selectTypeMatch(typeMatchPoints, typeMatchSets);


        changeField = false;

        volleyMatch = new VolleyMatch(typeVolleyMatch);

        initComponentes();
        addListeners();
        updateValuesView();
    }

    private TypeVolleyMatch selectTypeMatch(int typeMatchPoints, int typeMatchSets) {
        if(typeMatchPoints == TypeVolleyMatch.LARGO.getPoints()
                && typeMatchSets == TypeVolleyMatch.LARGO.getSets()){
            return TypeVolleyMatch.LARGO;
        }else if(typeMatchPoints == TypeVolleyMatch.MEDIO.getPoints()
                && typeMatchSets == TypeVolleyMatch.MEDIO.getSets()){
            return TypeVolleyMatch.MEDIO;
        }else if(typeMatchPoints == TypeVolleyMatch.CORTO.getPoints()
                && typeMatchSets == TypeVolleyMatch.CORTO.getSets()){
            return TypeVolleyMatch.CORTO;
        }else if(typeMatchPoints == TypeVolleyMatch.CALENTAMIENTO.getPoints()
                && typeMatchSets == TypeVolleyMatch.CALENTAMIENTO.getSets()){
            return TypeVolleyMatch.CALENTAMIENTO;
        }
        return  null;
    }

    private void initComponentes() {
        btPlusPointRight = findViewById(R.id.plus_point_right);
        btSubtractPointRight = findViewById(R.id.subtraction_point_right);
        btPlusPointLeft = findViewById(R.id.plus_point_left);
        btSubtractPointLeft = findViewById(R.id.subtraction_point_left);

        tvPointsRight = findViewById(R.id.points_team_right);
        tvPointsRight.setText(String.valueOf(volleyMatch.getRightPoints()));
        tvPointsLeft = findViewById(R.id.points_team_left);
        tvPointsLeft.setText(String.valueOf(volleyMatch.getLeftPoints()));

        tvSetsRight = findViewById(R.id.sets_team_right);
        tvSetsRight.setText(String.valueOf(volleyMatch.getRightSets()));
        tvSetsLeft = findViewById(R.id.sets_team_left);
        tvSetsLeft.setText(String.valueOf(volleyMatch.getLeftSets()));

        tvPointsRight.setTextColor(tvPointsRight.getContext().getResources().getColor(R.color.red));
        tvPointsLeft.setTextColor(tvPointsLeft.getContext().getResources().getColor(R.color.blue));
        tvSetsRight.setTextColor(tvSetsRight.getContext().getResources().getColor(R.color.red));
        tvSetsLeft.setTextColor(tvSetsLeft.getContext().getResources().getColor(R.color.blue));

        ivBallRight = findViewById(R.id.ball_right);
        ivBallLeft = findViewById(R.id.ball_left);
    }

    //Cambio de campo
    private void changeOfField(){
        if(changeField){
            tvPointsRight.setTextColor(tvPointsRight.getContext().getResources().getColor(R.color.red));
            tvPointsLeft.setTextColor(tvPointsLeft.getContext().getResources().getColor(R.color.blue));
            tvSetsRight.setTextColor(tvSetsRight.getContext().getResources().getColor(R.color.red));
            tvSetsLeft.setTextColor(tvSetsLeft.getContext().getResources().getColor(R.color.blue));
            changeField = false;
        }else{
            tvPointsRight.setTextColor(tvPointsRight.getContext().getResources().getColor(R.color.blue));
            tvPointsLeft.setTextColor(tvPointsLeft.getContext().getResources().getColor(R.color.red));
            tvSetsRight.setTextColor(tvSetsRight.getContext().getResources().getColor(R.color.blue));
            tvSetsLeft.setTextColor(tvSetsLeft.getContext().getResources().getColor(R.color.red));
            changeField = true;
        }
    }

    private void addListeners(){
        btPlusPointRight.setOnClickListener(this);
        btSubtractPointRight.setOnClickListener(this);
        btPlusPointLeft.setOnClickListener(this);
        btSubtractPointLeft.setOnClickListener(this);
    }

    //Actualiza los valores de la pantalla de partido
    private void updateValuesView(){
        tvPointsRight.setText(String.valueOf(volleyMatch.getRightPoints()));
        tvPointsLeft.setText(String.valueOf(volleyMatch.getLeftPoints()));
        tvSetsRight.setText(String.valueOf(volleyMatch.getRightSets()));
        tvSetsLeft.setText(String.valueOf(volleyMatch.getLeftSets()));
        if(volleyMatch.isRightService()){
            ivBallRight.setVisibility(View.VISIBLE);
            ivBallLeft.setVisibility(View.INVISIBLE);
        }else{
            ivBallLeft.setVisibility(View.VISIBLE);
            ivBallRight.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        ReactionScorePoint event;
        switch (v.getId()){
            case R.id.plus_point_right:
                event = volleyMatch.scorePoint(true);
                reactionScorePoint(event);
                updateValuesView();
                break;

            case R.id.subtraction_point_right:
                if(volleyMatch.subtractionPoint(true)){
                    changeOfField();
                }
                updateValuesView();
                break;

            case R.id.plus_point_left:
                event = volleyMatch.scorePoint(false);
                reactionScorePoint(event);
                updateValuesView();
                break;

            case R.id.subtraction_point_left:
                if(volleyMatch.subtractionPoint(false)){
                    changeOfField();
                }
                updateValuesView();
                break;
        }

    }

    private void matchWin(String winner){
        Intent intent = new Intent(this, VictoryActivity.class);
        intent.putExtra("winner", winner);
        startActivityForResult(intent, 0);
    }

    private void reactionScorePoint(ReactionScorePoint event) {
        switch (event){
            case SET_WIN:
                changeOfField();
                break;

            case LEFT_WIN:
                if(changeField){
                    matchWin(WIN_BLUE);
                }else{
                    matchWin(WIN_RED);
                }
                break;
            case RIGHT_WIN:
                if(!changeField){
                    matchWin(WIN_BLUE);
                }else{
                    matchWin(WIN_RED);
                }
                break;
            case ONLY_SCORE_POINT:
                break;
        }
    }
}