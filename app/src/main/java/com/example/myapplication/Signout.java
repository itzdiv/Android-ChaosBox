package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Random;

public class Signout extends AppCompatActivity {
    Button b1,b2;
    FirebaseAuth firebaseAuth;
    ImageButton i1,f1,c1;
    MediaPlayer mp;
    String[] messages = {"Meow", "Meow Again", "Meows in Agony", "MEOWWWWW", "meowww :("};


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signout);
        b1=(Button)findViewById(R.id.button_six);
        c1=(ImageButton)findViewById(R.id.imageButton3);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int index = random.nextInt(messages.length);
                String message = messages[index];
                Toast.makeText(Signout.this, message, Toast.LENGTH_SHORT).show();
                mp.start();
            }
        });

        mp=MediaPlayer.create(this,R.raw.meow);
        firebaseAuth=FirebaseAuth.getInstance();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                Intent i9=new Intent(Signout.this, first.class);
                startActivity(i9);
                finish();
                Toast.makeText(Signout.this, "Successfully Logged Out", Toast.LENGTH_SHORT).show();
            }
        });
        b2=(Button)findViewById(R.id.button7);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent f=new Intent(Signout.this,Creator.class);
                startActivity(f);
                finish();
            }
        });
        f1=(ImageButton)findViewById(R.id.imageButton);
        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m=new Intent(Signout.this,AddPlayers.class);
                startActivity(m);
                finish();

            }
        });
        i1=(ImageButton)findViewById(R.id.imageButton2);
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k=new Intent(Signout.this,Calculator.class);
                startActivity(k);
                finish();

            }
        });

    }
}