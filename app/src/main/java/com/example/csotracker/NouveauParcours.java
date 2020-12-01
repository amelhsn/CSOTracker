package com.example.csotracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.csotracker.MyMapFragment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;


public class NouveauParcours extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    protected EditText cavalier1nom, cavalier2nom, cheval2nom, cheval1nom, nomConcours;
    protected TextView LocaView;
    protected Button valider, demarrer;
    public Connection connexion = null;


    ArrayList<String> nomsListe = new ArrayList<>();
    final String[] Cavalier1 = new String[1];
    final String[] Cavalier2 = new String[1];
    final String[] Cheval1 = new String[1];
    final String[] Cheval2 = new String[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouveau_parcours);

        //récup des données
        cavalier1nom = (EditText) findViewById(R.id.activity_parcours_cavalier1_txt);
        cavalier2nom = (EditText) findViewById(R.id.activity_parcours_cavalier2_txt);
        cheval1nom = (EditText) findViewById(R.id.activity_parcours_cheval1_txt);
        cheval2nom = (EditText) findViewById(R.id.activity_parcours_cheval2_txt);
        nomConcours = (EditText) findViewById(R.id.activity_parcours_concours_txt);

        valider = findViewById(R.id.activity_parcours_valider);
        valider.setOnClickListener(this);

        demarrer = findViewById(R.id.activity_parcours_demarrer);
        demarrer.setOnClickListener(this);



        //Maps
        LocaView = (TextView)findViewById(R.id.t_coord);
        LocaView.setText("Paris");
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        MyMapFragment myMapFragment = (MyMapFragment) fragmentManager.findFragmentById(R.id.fragment_map);

        //Liste terrains
        Spinner spinnerTerrain = findViewById(R.id.activity_parcours_terrain_spnr);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.terrains, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTerrain.setAdapter(adapter);
        spinnerTerrain.setOnItemSelectedListener (this);


    }

    //maps
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    //Connexion SQL
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
        private void Connection_mysql() {

            // fin du run
            Thread thread = new Thread(() -> {
                try {
                    Class.forName("com.mysql.jdbc.Driver"); //mysqldriver
                    Log.d("CONNEXION", "driver ok");
                } catch (
                        ClassNotFoundException e
                ) {
                    e.printStackTrace();
                }

                //connection a remotemysql --> phpmyadmin
                String jdbcURL = "jdbc:mysql://remotemysql.com:3306/VMODqmE3Vs?autoReconnect=true&useSSL=false";
               // String jdbcURL = "jdbc:mysql://192.168.99.1/VMODqmE3Vs?autoReconnect=true&useSSL=false";
                String user = "VMODqmE3Vs";
                String passwd = "HPDIW3tcUF";

                try {
                    //
                    connexion = DriverManager.getConnection(jdbcURL, user, passwd);
                    Log.d("CONNEXION","connexion ok");
                    //query
                    PreparedStatement prepStat =null;

                    //insertion de nos données dans la table
                    String req = "insert into `Courses` (`Nom`, `NomCa1`, `NomCa2`, `NomCh1`, `NomCh2`, `Localisation`) values (?,?,?,?,?,?) ";
                    prepStat =  connexion.prepareStatement(req);
                    //numéro des ?
                    prepStat.setString(1, nomConcours.getText().toString());
                    prepStat.setString(2, cavalier1nom.getText().toString());
                    prepStat.setString(3, cavalier2nom.getText().toString());
                    prepStat.setString(4, cheval1nom.getText().toString());
                    prepStat.setString(5, cheval2nom.getText().toString());
                    prepStat.setString(6, LocaView.getText().toString());

                    //prepStat.setString(7, date.getText().toString());
                    //prepStat.setString(8, date.getText().toString());

                    //execute la query
                    prepStat.executeUpdate();
                    Log.d("CONNEXION","requêtes ok");
                    //fin query
                    //avoir case vide une fois données transféré
                    //effacerView();
                    prepStat.close();
                } catch (
                        SQLException se
                ) {
                    se.printStackTrace();
                }
            }); // fin du thread
            thread.start();
    }



/*
    public void effacerView(){
        cavalier1nom.setText("");
        cavalier2nom.setText("");
        cheval1nom.setText("");
        cheval2nom.setText("");
    }*/

   @Override
    public void onClick(View v) {
        if (v == valider) {
            Connection_mysql();
        }

        if (v == demarrer){
            Intent enregistrementIntent = new Intent(NouveauParcours.this, Enregistrement.class);

            Cavalier1[0] = cavalier1nom.getText().toString();
            Cheval1[0] = cheval1nom.getText().toString();
            Cavalier2[0] = cavalier2nom.getText().toString();
            Cheval2[0] = cheval2nom.getText().toString();

            enregistrementIntent.putExtra("Cavalier1", Cavalier1[0]);
            enregistrementIntent.putExtra("Cavalier2", Cavalier2[0]);

            startActivity(enregistrementIntent);
        }
    }
}