package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import pl.droidsonroids.gif.GifImageView;


public class first extends AppCompatActivity {
    EditText e1,e2,e3;
    Button b1,b2;
    GifImageView g1,g2;
    ProgressBar p1;
    FirebaseAuth firebaseAuth;
    MediaPlayer mp;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.first);
        e1= findViewById(R.id.editTextTextPersonName1);
        e2=(EditText)findViewById(R.id.editTextTextPersonName2);
        g1=(GifImageView)findViewById(R.id.z1);
        g2=(GifImageView)findViewById(R.id.z2);
        e3=(EditText)findViewById(R.id.edit2);
        e2.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);
        mp=MediaPlayer.create(this,R.raw.rgs);
        p1=(ProgressBar)findViewById(R.id.progressBar);
        firebaseAuth=FirebaseAuth.getInstance();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=e1.getText().toString();
                String s2=e2.getText().toString();
                String s3=e3.getText().toString();
                if(s2.equals(s3)){
                if(s1.isEmpty())
                {e1.setError("This Field Can't Be Empty");
                    g2.setVisibility(View.VISIBLE);
                    return;
            }
                else
                {if(s2.isEmpty()) {
                    g2.setVisibility(View.VISIBLE);
                    e2.setError("This Field Can't Be Empty");
                    return;
                }
                else
                {p1.setVisibility(View.VISIBLE);
                    g2.setVisibility(View.INVISIBLE);

                    firebaseAuth.createUserWithEmailAndPassword(s1,s2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(first.this, "Successfully Created", Toast.LENGTH_SHORT).show();
                                p1.setVisibility(View.INVISIBLE);
                                g1.setVisibility(View.INVISIBLE);
                                mp.start();
                                Intent k=new Intent(first.this,MainActivity2.class);
                                startActivity(k);
                                finish();
                        }
                            else
                            {

                                g1.setImageResource(R.drawable.f3);
                                Toast.makeText(first.this, "Error", Toast.LENGTH_SHORT).show();

                                p1.setVisibility(View.INVISIBLE);

                            }
                    }

                });
                }
        }}
                else{
                    Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                    g1.setVisibility(View.VISIBLE);
                    g1.setImageResource(R.drawable.f3);

                }


    }
});
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(first.this,MainActivity2.class);
                startActivity(i);
                finish();
            }
        });

    ;}}