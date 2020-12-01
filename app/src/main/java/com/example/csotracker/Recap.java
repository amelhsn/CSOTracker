package com.example.csotracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Recap extends AppCompatActivity {
    int bonus2 = 0;
    int malus2 = 0;
    int total2 = 0;
    int bonus = 0;
    int malus = 0;
    int total = 0;
    String Cavalier1, Cavalier2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recap);

        //récup données
        Bundle bndl = getIntent().getExtras();
        if (bndl != null) {
            bonus = bndl.getInt("bonus");
            malus = bndl.getInt("malus");
            total = bndl.getInt("total");
            bonus2 = bndl.getInt("bonus2");
            malus2 = bndl.getInt("malus2");
            total2 = bndl.getInt("total2");

            Cavalier1 = bndl.getString("Cavalier1");
            Cavalier2 = bndl.getString("Cavalier2");

            TextView pointsC1 = (TextView)findViewById(R.id.activity_recap_TabRes_C1_points);
            pointsC1.setText(String.valueOf(bonus));
            TextView malusC1 = (TextView)findViewById(R.id.activity_recap_TabRes_C1_malus);
            malusC1.setText(String.valueOf(malus));
            TextView totalC1 = (TextView)findViewById(R.id.activity_recap_TabRes_C1_Total);
            totalC1.setText(String.valueOf(total));

            TextView pointsC2 = (TextView)findViewById(R.id.activity_recap_TabRes_C2_points);
            pointsC2.setText(String.valueOf(bonus2));
            TextView malusC2 = (TextView)findViewById(R.id.activity_recap_TabRes_C2_malus);
            malusC2.setText(String.valueOf(malus2));
            TextView totalC2 = (TextView)findViewById(R.id.activity_recap_TabRes_C2_total);
            totalC2.setText(String.valueOf(total2));


            TextView nomcavalier1 = (TextView)findViewById(R.id.activity_recap_TabRes_Cavalier1);
            nomcavalier1.setText(String.valueOf(Cavalier1));
            TextView nomcavalier2 = (TextView)findViewById(R.id.activity_recap_TabRes_Cavalier2);
            nomcavalier2.setText(String.valueOf(Cavalier2));
        }

        //activity_recap_menu_btn
        Button menuBtn = findViewById(R.id.activity_recap_menu_btn);
        menuBtn.setOnClickListener(v -> {
        Intent main = new Intent(Recap.this, MainActivity.class);
        startActivity(main);
        });
    }
}