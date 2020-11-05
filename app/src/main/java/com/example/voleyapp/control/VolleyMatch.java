package com.example.voleyapp.control;

import com.example.voleyapp.model.Player;
import com.example.voleyapp.model.ReactionScorePoint;
import com.example.voleyapp.model.Team;
import com.example.voleyapp.model.TypeVolleyMatch;

import java.util.ArrayList;
import java.util.Random;

public class VolleyMatch extends Match{
    private Team rightTeam;
    private Team leftTeam;

    private ArrayList<Team> teams;
    private ArrayList<Player> players;
    private TypeVolleyMatch typeVolleyMatch;

    private int setToPlay;//Numero maximo de sets que se pueden jugar en el partido
    private int pointFinalSetRight;//Indica el resultado del equipo derecho en el ultimo set jugado
    private int pointFinalSetLeft;//Indica el resultado del equipo izquierdo en el ultimo set jugado
    private boolean rightWonLastSet; //Indica si el equipo de la derecha gano el ultimo set
    private boolean deuce; //Indica si se ha producido un deuce en el set que se este jugando

    private boolean rightService; //Indica si el saque lo tiene el equipo de la derecha
    private boolean previousRightService; //Indica si el ultimo saque lo ha tenido el equipo de la derecha
    /*Indica si el saque inicial del set anterior lo ha realizado el equipo de la derecha*/
    private boolean previousRightServiceSet;

    // Constructor para inicializar con jugadores
    public VolleyMatch(ArrayList<Player> players) {
        this.players = players;

        teams = new ArrayList<Team>();
        rightTeam = new Team();
        leftTeam = new Team();

        pointFinalSetLeft = 0;
        pointFinalSetRight = 0;
        deuce = false;

        Random random = new Random();
        rightService = random.nextBoolean();
        previousRightService = rightService;
    }

    public VolleyMatch(TypeVolleyMatch typeVolleyMatch) {
        this.typeVolleyMatch = typeVolleyMatch;
        setToPlay = typeVolleyMatch.getSets();

        rightTeam = new Team();
        leftTeam = new Team();
        pointFinalSetLeft = 0;
        pointFinalSetRight = 0;
        deuce = false;

        Random random = new Random();
        rightService = random.nextBoolean();
        previousRightService = rightService;
    }

    /*
    * Marca un punto en los equipos en partido
    * y devuelve el resultado de marcar el punto
     */
    public ReactionScorePoint scorePoint(boolean right) {
        previousRightService = rightService;
        if (right) {
            rightTeam.scorePoint();
            rightService = true;
        } else {
            leftTeam.scorePoint();
            rightService = false;
        }

        if (isScoreSet()) {
            scoreSet(right);
            return matchWin();
        }
        return ReactionScorePoint.ONLY_SCORE_POINT;
    }

    //Quita un punto a un equipo. Devuelve cierto si se ha restado un set a uno de los equipos
    public boolean subtractionPoint(boolean right){
        rightService = previousRightService;
        if(right){
            rightTeam.subtractPoint();
        } else {
            leftTeam.subtractPoint();
        }

        return subtractionSet(right);
    }

    //Devuelve cierto si se ha quitado un set
    private boolean subtractionSet(boolean right) {
        if(rightTeam.getMatchPoints() == 0 && leftTeam.getMatchPoints() == 0
                && (rightTeam.getMatchSets() != 0 || leftTeam.getMatchSets() != 0)){
            changeField();
            rightTeam.setMatchPoints(pointFinalSetRight);
            leftTeam.setMatchPoints(pointFinalSetLeft);
            if(rightWonLastSet){
                rightTeam.subtractSet();
            }else{
                leftTeam.subtractSet();
            }
            previousRightServiceSet = !previousRightServiceSet;
            setToPlay++;

            return true;
        }
        return false;
    }

    /*
    * Comprueba si algun equipo ha ganado el partido y en caso de
    * hacerlo devuelve el equipo que ha ganado.
     */
    protected ReactionScorePoint matchWin() {
        if (rightTeam.getMatchSets() >= setToPlay + leftTeam.getMatchSets()
                || leftTeam.getMatchSets() >= setToPlay + rightTeam.getMatchSets()
                || setToPlay == 0) {
            if (rightTeam.getMatchSets() > leftTeam.getMatchSets()) {
                return ReactionScorePoint.RIGHT_WIN;
            } else {
                return ReactionScorePoint.LEFT_WIN;
            }
        }
        return ReactionScorePoint.SET_WIN;
    }

    private boolean isLastSetWon(){
        return (rightTeam.getMatchPoints() >= typeVolleyMatch.getPointsLastSet()
                || leftTeam.getMatchPoints() >= typeVolleyMatch.getPointsLastSet());
    }

    private void scoreSet(boolean right){
        pointFinalSetRight = rightTeam.getMatchPoints();
        pointFinalSetLeft = leftTeam.getMatchPoints();

        if(right){
            pointFinalSetRight--;
            rightTeam.scoreSet();
            leftTeam.lostSet();
            rightWonLastSet = true;
        }else{
            pointFinalSetLeft--;
            leftTeam.scoreSet();
            rightTeam.lostSet();
            rightWonLastSet = false;
        }

        //Cambio de saque para el siguiente set
        rightService = !previousRightServiceSet;
        previousRightServiceSet = !previousRightServiceSet;
        setToPlay--;

        changeField();
    }

    private boolean isScoreSet(){
        if(rightTeam.getMatchPoints() >= typeVolleyMatch.getPoints()
                || leftTeam.getMatchPoints() >= typeVolleyMatch.getPoints()
                || (setToPlay == 1 && isLastSetWon())) {
            if(rightTeam.getMatchPoints() == leftTeam.getMatchPoints()){
                deuce = true;
                return false;
            } else if(rightTeam.getMatchPoints() > (leftTeam.getMatchPoints() + 1)
                    || leftTeam.getMatchPoints() > (rightTeam.getMatchPoints() + 1)){
                deuce = false;
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }



    public int getRightPoints() {
        System.out.println("DERECHA----->" + rightTeam.getMatchPoints());
        return rightTeam.getMatchPoints();
    }

    public int getLeftPoints() {
        System.out.println("IZQ----->" + leftTeam.getMatchPoints());
        return leftTeam.getMatchPoints();
    }

    //Cambia de campo los equipos
    protected void changeField() {
        Team auxiliar = rightTeam;
        rightTeam = leftTeam;
        leftTeam = auxiliar;
    }

    public int getLeftSets() {
        return leftTeam.getMatchSets();
    }

    public int getRightSets() {
        return rightTeam.getMatchSets();
    }

    public boolean isRightService() {
        return rightService;
    }
}
