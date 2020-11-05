package com.example.voleyapp.control;

import com.example.voleyapp.model.Player;
import com.example.voleyapp.model.ReactionScorePoint;
import com.example.voleyapp.model.SportMacth;
import com.example.voleyapp.model.Team;

import java.util.ArrayList;
import java.util.Random;

public class Match {
    private Team rightTeam;
    private Team leftTeam;

    private ArrayList<Team> teams;
    private ArrayList<Player> players;
    private SportMacth sportMacth;



    // Constructor para inicializar con jugadores
    public Match(ArrayList<Player> players) {
        this.players = players;
        teams = new ArrayList<Team>();
        rightTeam = new Team();
        leftTeam = new Team();
    }

    public Match() {
        players = new ArrayList<>();
        teams = new ArrayList<>();
        rightTeam = new Team();
        leftTeam = new Team();
    }

    /*
     * Marca un punto en los equipos en partido
     * y devuelve el resultado de marcar el punto
     */
    public ReactionScorePoint scorePoint(boolean right) {
        if (right) {
            rightTeam.scorePoint();
        } else {
            leftTeam.scorePoint();
        }
        return ReactionScorePoint.ONLY_SCORE_POINT;
    }

    public boolean subtractionPoint(boolean right){
        if(right){
            rightTeam.subtractPoint();
        } else {
            leftTeam.subtractPoint();
        }
        return true;
    }

    /*
     * Comprueba si algun equipo ha ganado el partido y en caso de
     * hacerlo devuelve el equipo que ha ganado.
     */
    protected ReactionScorePoint matchWin() {
        if(rightTeam.getMatchPoints() > leftTeam.getMatchPoints()){
            return ReactionScorePoint.RIGHT_WIN;
        }else if(leftTeam.getMatchPoints() > rightTeam.getMatchPoints()){
            return ReactionScorePoint.LEFT_WIN;
        }else{
            return ReactionScorePoint.DRAW;
        }
    }

    //Cambia de campo los equipos
    protected void changeField() {
        Team auxiliar = rightTeam;
        rightTeam = leftTeam;
        leftTeam = auxiliar;
    }

    public ArrayList<Player> getRightTeamPlayers() {
        return rightTeam.getPlayers();
    }

    public ArrayList<Player> getLeftTeamPlayers(){
        return leftTeam.getPlayers();
    }

    public int getRightPoints() {
        System.out.println("DERECHA----->" + rightTeam.getMatchPoints());
        return rightTeam.getMatchPoints();
    }

    public int getLeftPoints() {
        System.out.println("IZQ----->" + leftTeam.getMatchPoints());
        return leftTeam.getMatchPoints();
    }
}
