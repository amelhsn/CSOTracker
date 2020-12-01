package com.example.csotracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;

public class CavalierSuivant extends AppCompatActivity {
    Chronometer chrono2;
    Button suivantBtn2, obstacleBtn2, chuteBtn2, refusBtn2, barreBtn2, finBtn;
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
        setContentView(R.layout.activity_cavalier_suivant);

        //récup données
        Bundle bndl = getIntent().getExtras();
        if (bndl != null) {
            bonus = bndl.getInt("bonus");
            malus = bndl.getInt("malus");
            total = bndl.getInt("total");

            Cavalier1 = bndl.getString("Cavalier1");
            Cavalier2 = bndl.getString("Cavalier2");

            TextView pointsC1 = (TextView)findViewById(R.id.activity_recap_TabRes_C1_points);
            pointsC1.setText(String.valueOf(bonus));
            TextView malusC1 = (TextView)findViewById(R.id.activity_enregistrement2_TabRes_C1_malus);
            malusC1.setText(String.valueOf(malus));
            TextView totalC1 = (TextView)findViewById(R.id.activity_enregistrement2_TabRes_C1_Total);
            totalC1.setText(String.valueOf(total));

            TextView nomcavalier1 = (TextView)findViewById(R.id.activity_enregistrement2_TabRes_Cavalier1);
            nomcavalier1.setText(String.valueOf(Cavalier1));
            TextView nomcavalier2 = (TextView)findViewById(R.id.activity_enregistrement2_TabRes_Cavalier2);
            nomcavalier2.setText(String.valueOf(Cavalier2));
        }

        //chrono
        ImageButton btn_start2 = findViewById(R.id.activity_enregistrement2_start_chrono_btn);
        btn_start2.setOnClickListener(v -> chrono2.start());

        ImageButton btn_stop2 = findViewById(R.id.activity_enregistrement2_stop_chrono_btn);
        btn_stop2.setOnClickListener(v -> chrono2.stop());
        chrono2 = (Chronometer)findViewById(R.id.activity_enregistrement2_chrono);
       // TextView chronoTxt2 = (TextView)findViewById(R.id.activity_enregistrement2_TabRes_C1_chrono);
       // chronoTxt2.setText(String.valueOf(chrono2));

        //actualisation des scores
        obstacleBtn2 = findViewById(R.id.activity_enregistrement2_obstacle_btn);
        obstacleBtn2.setOnClickListener((View.OnClickListener) v -> {

            //ajoute 30 points au score
            bonus2 += 30;
            total2 = malus2 + bonus2;
            TextView obstacleVw = (TextView)findViewById(R.id.activity_enregistrement2_TabRes_C2_points);
            obstacleVw.setText(String.valueOf(bonus2));
            TextView totalVw = (TextView)findViewById(R.id.activity_enregistrement2_TabRes_C2_total);
            totalVw.setText(String.valueOf(total2));
        });

        refusBtn2 = findViewById(R.id.activity_enregistrement2_refus_btn);
        refusBtn2.setOnClickListener((View.OnClickListener) v -> {
            //enlève 10 points
            malus2 -= 20;
            total2 = malus2 + bonus2;
            TextView obstacleVw = (TextView)findViewById(R.id.activity_enregistrement2_TabRes_C2_malus);
            obstacleVw.setText(String.valueOf(malus2));
            TextView totalVw = (TextView)findViewById(R.id.activity_enregistrement2_TabRes_C2_total);
            totalVw.setText(String.valueOf(total2));
        });

        chuteBtn2 = findViewById(R.id.activity_enregistrement2_chute_btn);
        chuteBtn2.setOnClickListener((View.OnClickListener) v -> {
            //enlève 10 points
            malus2 -= 25;
            total2 = malus2 + bonus2;
            TextView obstacleVw = (TextView)findViewById(R.id.activity_enregistrement2_TabRes_C2_malus);
            obstacleVw.setText(String.valueOf(malus2));
            TextView totalVw = (TextView)findViewById(R.id.activity_enregistrement2_TabRes_C2_total);
            totalVw.setText(String.valueOf(total2));
        });

        barreBtn2 = findViewById(R.id.activity_enregistrement2_barre_btn);
        barreBtn2.setOnClickListener((View.OnClickListener) v -> {
            //enlève 10 points
            malus2 -= 10;
            total2 = malus2 + bonus2;
            TextView obstacleVw = (TextView)findViewById(R.id.activity_enregistrement2_TabRes_C2_malus);
            obstacleVw.setText(String.valueOf(malus2));
            TextView totalVw = (TextView)findViewById(R.id.activity_enregistrement2_TabRes_C2_total);
            totalVw.setText(String.valueOf(total2));
        });


            finBtn = findViewById(R.id.activity_enregistrement2_Fin);
            finBtn.setOnClickListener(v -> {
            Intent recap = new Intent(CavalierSuivant.this, Recap.class);
            recap.putExtra("bonus2", bonus2);
            recap.putExtra("malus2",malus2);
            recap.putExtra("total2",total2);
            recap.putExtra("bonus", bonus);
            recap.putExtra("malus",malus);
            recap.putExtra("total",total);
            recap.putExtra("Cavalier1", Cavalier1);
            recap.putExtra("Cavalier2", Cavalier2);
            startActivity(recap);
        });

        ImageButton photoBtn2 = findViewById(R.id.activity_enregistrement2_photo_btn);
        photoBtn2.setOnClickListener(v -> {

            Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            int REQUEST_ID_IMAGE_CAPTURE = 100;
            startActivityForResult(photoIntent, REQUEST_ID_IMAGE_CAPTURE);
        });

    }
}