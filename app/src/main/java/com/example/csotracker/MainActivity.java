package com.example.csotracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {


    Locale myLocale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button parcoursBtn = findViewById(R.id.activity_main_parcours);

        parcoursBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent NParcoursIntent = new Intent(MainActivity.this, NouveauParcours.class);
                startActivity(NParcoursIntent);
             }
        });

        Button historiqueBtn = findViewById(R.id.mHistoriqueBtn);

        historiqueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent historiqueIntent = new Intent(MainActivity.this, Historique.class);
                startActivity(historiqueIntent);
            }
        });

        Button cinqBtn = findViewById(R.id.cinq);

        cinqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent CinqIntent = new Intent(MainActivity.this, Cinq.class);
                startActivity(CinqIntent);
            }
        });


         /*  Spinner languageSpinner;
        languageSpinner = (Spinner) findViewById(R.id.spinner);
        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                if (pos == 1) {
                    Toast.makeText(parent.getContext(),

                } else if (pos == 2) {

                }

            }


            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }

        })*/
    }

  /*  public void setLocale(String lang) {

        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);
    }*/

   /* private static class LanguageHelper {
        public static void changeLocale(Resources res, String locale) {

            Configuration config;
            config = new Configuration(res.getConfiguration());


            if ("fr".equals(locale)) {
                config.locale = Locale.FRENCH;
            } else {
                config.locale = Locale.ENGLISH;
            }
            res.updateConfiguration(config, res.getDisplayMetrics());
            // reload files from assets directory
        }
    }*/
}

        /*Thread t = new Thread(t.run()){
            Connection conn = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://10.0.2.2/baseCSOTracker" + "user=root&password=");
                Log.d("error", "working!");

            } catch (SQLException ex){
                Log.d("error","SQLException"+ ex.getMessage());
                Log.d("error","SQLState"+ ex.getSQLState());
                Log.d("error","VendorError"+ ex.getErrorCode());

            } catch (ClassNotFoundException ex){
                Log.d("error","SQLException"+ ex.getMessage());
            }
        };
                t.start();*/





