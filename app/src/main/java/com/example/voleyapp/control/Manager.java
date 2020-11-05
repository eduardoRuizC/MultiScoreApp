package com.example.voleyapp.control;

import com.example.voleyapp.model.Player;
import com.example.voleyapp.model.SportMacth;
import com.example.voleyapp.model.Team;

import java.util.ArrayList;
import java.util.Random;

public class Manager {
    private ArrayList<Team> teams;
    private ArrayList<Player> players;

    public Manager(ArrayList<Player> players) {
        this.teams = new ArrayList<>();
        this.players = players;
    }

    public Manager(){
        teams = new ArrayList<>();
        players = new ArrayList<>();
    }

    public void createTeam(ArrayList<Player> teamPlayers){
        if(teamPlayers == null)
            teams.add(new Team());
        else
            teams.add(new Team(teamPlayers));
    }

    public void registerNewPlayer(Player player){
        players.add(player);
    }


    //Crea un equipo de forma aleatoria
    public boolean createRandomTeams(int numTeams){
        int numPlayersTeam = players.size() / numTeams;
        System.out.println("ENTROOOO-->" + numPlayersTeam);
        for(int i = 0; i < numTeams; i++)
            createTeam(null);

        for(int i = 0; i < numTeams; i++){
            Team team = new Team();
            for(int n = 0; n < numPlayersTeam; n++) {
                putPlayerInTeam(team);
            }
            teams.add(team);
        }

        putRestPlayersInTeam(numPlayersTeam, numTeams);

        return true;
    }

    //Busca un jugador libre para añadir al equipo
    private boolean putPlayerInTeam(Team team){
        System.out.println("PONGO JUGADOR EN EQUIPO");
        Player player = null;
        Random random = new Random();
        do{
            player = players.get(random.nextInt(players.size()));
        }while(!playerWithoutTeam(player));
        team.addPlayer(player);
        return true;
    }

    //Comprueba que un jugador no tenga equipo
    private boolean playerWithoutTeam(Player player){
        for(Team team: teams){
            if(team.playerInTeam(player)){
                return false;
            }
        }
        return true;
    }

    //Agrega a los jugadores restantes en los equipos
    private void putRestPlayersInTeam(int numPlayersTeam, int numTeams) {
        int numPlayersWithoutTeam = players.size() % numTeams;
        Team team;
        Random random = new Random();

        if(numPlayersWithoutTeam != 0){
            for(int i = 0; i < numPlayersWithoutTeam; i++){
                do{
                    team = teams.get(random.nextInt(teams.size()));
                }while(team.getNumPlayers() > numPlayersTeam);

                putPlayerInTeam(team);
            }
        }
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }
}
