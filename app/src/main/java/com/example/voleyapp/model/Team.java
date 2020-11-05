package com.example.voleyapp.model;

import java.util.ArrayList;

public class Team {
    private ArrayList<Player> players;

    private int matchPoints;
    private int matchSets;

    private int wonMatches;
    private int lostMatches;

    public Team() {
        players = new ArrayList<Player>();
        matchPoints = 0;
        matchSets = 0;
        wonMatches = 0;
        lostMatches = 0;
    }

    public Team(ArrayList<Player> players) {
        this.players = players;
        matchPoints = 0;
        matchSets = 0;
        wonMatches = 0;
        lostMatches = 0;
    }

    public void scorePoint(){
        matchPoints++;
    }

    public  void subtractPoint(){
        if(matchPoints > 0){
            matchPoints--;
        }
    }

    public void scoreSet(){
        matchSets ++;
        matchPoints = 0;
    }

    public void lostSet(){
        matchPoints = 0;
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getMatchPoints() {
        return matchPoints;
    }

    public int getMatchSets() {
        return matchSets;
    }

    public boolean playerInTeam(Player player) {
        return players.contains(player);
    }

    public int getNumPlayers() {
        return players.size();
    }

    public void setMatchPoints(int matchPoints) {
        this.matchPoints = matchPoints;
    }

    public void subtractSet() {
        if(matchSets > 0){
            matchSets--;
        }
    }
}
