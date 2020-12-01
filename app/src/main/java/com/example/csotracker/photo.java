package com.example.csotracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class photo extends AppCompatActivity {

    private static final int RETOUR = 1;
    private Button prendrephoto;
    private ImageView afficher;
    private String photoPath = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        prendrePhoto();


    }

    private void prendrePhoto(){
        prendrephoto = findViewById(R.id.prendrephotoBtn);
        afficher = findViewById(R.id.affichage);
        createOnClickPrendrePhoto();
    }


    private void createOnClickPrendrePhoto(){
        prendrephoto.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                prendreUnePhoto();
            }
        });
    }
    private void prendreUnePhoto(){
        Intent photo = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(photo.resolveActivity(getPackageManager())!= null){
            String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File photoDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            try {
                File photoFile = File.createTempFile("photo"+time, ".jpg", photoDir);
                photoPath = photoFile.getAbsolutePath();
                // créer URI avec provider
                Uri photoUri = FileProvider.getUriForFile(photo.this, photo.this.getApplicationContext().getPackageName()+".provider", photoFile);
                photo.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(photo,RETOUR);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==RETOUR && resultCode==RESULT_OK){
            Bitmap image = BitmapFactory.decodeFile(photoPath);
            afficher.setImageBitmap(image);
        }
    }

}