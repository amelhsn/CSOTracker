package com.example.csotracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class Enregistrement extends AppCompatActivity {

    Chronometer chrono;
    Button suivantBtn, obstacleBtn, chuteBtn, refusBtn, barreBtn;
    int bonus = 0;
    int malus = 0;
    int total = 0;
    String Cavalier1, Cavalier2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enregistrement);

        Bundle bndl = getIntent().getExtras();

        //données activité précédente
        if (bndl != null) {
            Cavalier1 = bndl.getString("Cavalier1");
            Cavalier2 = bndl.getString("Cavalier2");
            TextView nomcavalier1 = (TextView)findViewById(R.id.activity_enregistrement_TabRes_Cavalier1);
            nomcavalier1.setText(String.valueOf(Cavalier1));
            TextView nomcavalier2 = (TextView)findViewById(R.id.activity_enregistrement_TabRes_Cavalier2);
            nomcavalier2.setText(String.valueOf(Cavalier2));
        }

        //chrono
        ImageButton btn_start = findViewById(R.id.activity_enregistrement_start_chrono_btn);
        btn_start.setOnClickListener(v -> chrono.start());

        ImageButton btn_stop = findViewById(R.id.activity_enregistrement_stop_chrono_btn);
        btn_stop.setOnClickListener(v -> chrono.stop());
        chrono = (Chronometer)findViewById(R.id.activity_enregistrement_chrono);
        //TextView chronoTxt = (TextView)findViewById(R.id.activity_enregistrement_TabRes_C1_chrono);
        //chronoTxt.setText(String.valueOf(chrono));

        //actualisation des scores
        obstacleBtn = findViewById(R.id.activity_enregistrement_obstacle_btn);
        obstacleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ajoute 30 points au score
               bonus += 30;
                total = malus + bonus;
                TextView obstacleVw = (TextView)findViewById(R.id.activity_enregistrement_TabRes_C1_points);
                obstacleVw.setText(String.valueOf(bonus));
                TextView totalVw = (TextView)findViewById(R.id.activity_enregistrement_TabRes_C1_Total);
                totalVw.setText(String.valueOf(total));
            }
        });

        refusBtn = findViewById(R.id.activity_enregistrement_refus_btn);
        refusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //enlève 20 points
                malus -= 20;
                total = malus + bonus;
                TextView obstacleVw = (TextView)findViewById(R.id.activity_enregistrement_TabRes_C1_malus);
                obstacleVw.setText(String.valueOf(malus));
                TextView totalVw = (TextView)findViewById(R.id.activity_enregistrement_TabRes_C1_Total);
                totalVw.setText(String.valueOf(total));
            }
        });

        chuteBtn = findViewById(R.id.activity_enregistrement_chute_btn);
        chuteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //enlève 25 points
                malus -= 25;
                total = malus + bonus;
                TextView obstacleVw = (TextView)findViewById(R.id.activity_enregistrement_TabRes_C1_malus);
                obstacleVw.setText(String.valueOf(malus));
                TextView totalVw = (TextView)findViewById(R.id.activity_enregistrement_TabRes_C1_Total);
                totalVw.setText(String.valueOf(total));
            }
        });

        barreBtn = findViewById(R.id.activity_enregistrement_barre_btn);
        barreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //enlève 10 points
                malus -= 10;
                total = malus + bonus;
                TextView obstacleVw = (TextView)findViewById(R.id.activity_enregistrement_TabRes_C1_malus);
                obstacleVw.setText(String.valueOf(malus));
                TextView totalVw = (TextView)findViewById(R.id.activity_enregistrement_TabRes_C1_Total);
                totalVw.setText(String.valueOf(total));
            }
        });


        //activité suivante
        suivantBtn = findViewById(R.id.activity_enregistrement_cavalier_suivant);
        suivantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent suivantIntent = new Intent(Enregistrement.this, CavalierSuivant.class);
                suivantIntent.putExtra("bonus", bonus);
                suivantIntent.putExtra("malus",malus);
                suivantIntent.putExtra("total",total);
                suivantIntent.putExtra("Cavalier1", Cavalier1);
                suivantIntent.putExtra("Cavalier2", Cavalier2);

                startActivity(suivantIntent);
            }
        });

        //appareil photo

        ImageButton photoBtn = findViewById(R.id.activity_enregistrement_photo_btn);
        photoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                int REQUEST_ID_IMAGE_CAPTURE = 100;
                startActivityForResult(photoIntent, REQUEST_ID_IMAGE_CAPTURE);
            }
        });




       /* // With Android Level >= 23, you have to ask the user
// for permission to read/write data on the device.
        if (android.os.Build.VERSION.SDK_INT >= 23) {

            // Check if we have read/write permission

            int readPermission = ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE);
            int writePermission = ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (writePermission != PackageManager.PERMISSION_GRANTED ||
                    readPermission != PackageManager.PERMISSION_GRANTED) {
                // If don't have permission so prompt the user.
                this.requestPermissions(
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_ID_READ_WRITE_PERMISSION
                );
            }
            // When you have the request results
            @Override
            public void onRequestPermissionsResult(int requestCode,
            String permissions[], int[] grantResults) {

                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                //
                switch (requestCode) {
                    case REQUEST_ID_READ_WRITE_PERMISSION: {

                        // Note: If request is cancelled, the result arrays are empty.
                        // Permissions granted (read/write).
                        if (grantResults.length > 1
                                && grantResults[0] == PackageManager.PERMISSION_GRANTED
                                && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                            Toast.makeText(this, "Permission granted!", Toast.LENGTH_LONG).show();

                            this.captureVideo();

                        }
                        // Cancelled or denied.
                        else {
                            Toast.makeText(this, "Permission denied!", Toast.LENGTH_LONG).show();
                        }
                        break;
                    }
                }
            }
        }*/

    }

}