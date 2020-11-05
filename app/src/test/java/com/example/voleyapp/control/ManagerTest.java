package com.example.voleyapp.control;

import com.example.voleyapp.model.Player;
import com.example.voleyapp.model.Team;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ManagerTest {

    @Test
    public void createRandomTeams() {
        int numPlayers = 12;
        ArrayList<Team> teams = new ArrayList<>();
        ArrayList<Player> listPlayers = getPlayers(numPlayers);
        Manager manager = new Manager(listPlayers);

        assertTrue(manager.createRandomTeams(2));
        teams = manager.getTeams();

        assertTrue(allPlayersInTeams(teams, numPlayers));
        assertFalse(playerInTwoTeams(teams));
    }

    private boolean playerInTwoTeams(ArrayList<Team> teams) {
        for(Team team1: teams){
            for(Player player: team1.getPlayers()){
                for(Team team2: teams){
                    if(team1 != team2){
                        if(team2.playerInTeam(player))
                            return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean allPlayersInTeams(ArrayList<Team> teams, int numPlayers) {
        ArrayList<Player> players = new ArrayList<>();


        for(Team team: teams){
            System.out.println("TEAM--+++++++++++++ ");
            players.addAll(team.getPlayers());
            for(Player p: team.getPlayers()){
                System.out.println("JUGADOR--> " + p.getName());
            }
        }


        for(int i = 0; i < numPlayers; i++){
            if(!playerInTeam(String.valueOf(i), players)) {
                System.out.println("FALLOOOOOO EN -->" + i);
                return false;
            }
        }
        return true;
    }

    private boolean playerInTeam(String name, ArrayList<Player> playersInTeams){
        for(Player player: playersInTeams){
            if(player.getName().equals(name)){
                return true;
            }
        }
        return false;
    }


    //Create a list of players, with the length pass for parameter
    private ArrayList<Player> getPlayers(int numPlayers){
        ArrayList<Player> list = new ArrayList<>();
        for(int i = 0; i < numPlayers; i++){
            list.add(new Player(String.valueOf(i)));
        }
        return list;
    }
}