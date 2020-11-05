package com.example.voleyapp.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.example.voleyapp.R;
import com.example.voleyapp.control.Manager;
import com.example.voleyapp.model.Player;

import java.util.ArrayList;

public class AddPlayerActivity extends Activity implements View.OnClickListener{
    private static final String REMOVE_PLAYER = "Eliminar jugador";
    private static final String CONFIRM = "Confirmar";
    private static final String CANCEL = "Cancelar";


    private ListView lvPlayers;
    private EditText etNewPlayer;
    private ImageButton btAddPlayer;
    private Button btCreateTeam;


    private ArrayList<Player> players;
    private ArrayList<String> playersString;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);


        initComponentes();
        addListeners();
    }

    private void initComponentes() {
        lvPlayers = findViewById(R.id.players_list);
        etNewPlayer = findViewById(R.id.new_player);
        btAddPlayer = findViewById(R.id.button_add_player);
        btCreateTeam = findViewById(R.id.create_team);
        players = new ArrayList<Player>();
        playersString = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,  playersString);
        lvPlayers.setAdapter(adapter);
    }

    private void removePlayerDialogItem(final int position) {
        AlertDialog.Builder dialog =  new AlertDialog.Builder(AddPlayerActivity.this);
        dialog.setTitle(REMOVE_PLAYER);
        dialog.setMessage(REMOVE_PLAYER);

        //dialog.setCancelable(false);
        dialog.setPositiveButton(CONFIRM, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                removePlayer(position);
            }
        });

        dialog.setNegativeButton(CANCEL, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialog.show();
    }

    private void removePlayer(int position){
        players.remove(position);
        playersString.remove(position);
        adapter.notifyDataSetChanged();
    }

    private void addListeners(){
        btAddPlayer.setOnClickListener(this);
        btCreateTeam.setOnClickListener(this);
        lvPlayers.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                removePlayerDialogItem(position);
                return false;
            }
        });
    }

    private void addPlayer(){
        Player newPlayer = new Player(etNewPlayer.getText().toString());
        players.add(newPlayer);
        playersString.add(newPlayer.getName());
        adapter.notifyDataSetChanged();
        etNewPlayer.setText("");
    }


    private void createTeams() {
        Manager manager = new Manager(players);
        manager.createRandomTeams(2);
        showTeamsDialog();
    }

    private void showTeamsDialog() {
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_add_player:
                addPlayer();
                break;
            case R.id.create_team:
                createTeams();
                break;
        }
    }
}
