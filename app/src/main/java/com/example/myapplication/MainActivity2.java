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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity2 extends AppCompatActivity {

    EditText e3,e4;
    GifImageView n;
    Button b3,b4,a8;
    ProgressBar p2;
    FirebaseAuth firebaseAuth;
    MediaPlayer mp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main2);
        e3= findViewById(R.id.editTextTextPersonName);
        e4= findViewById(R.id.editTextTextPersonName3);
        n= findViewById(R.id.koka);
        mp=MediaPlayer.create(this,R.raw.jv);
        e4.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
        b3= findViewById(R.id.button3);
        b4= findViewById(R.id.button4);
        a8= findViewById(R.id.pswdfrgt);
        p2= findViewById(R.id.progressBar2);
        firebaseAuth=FirebaseAuth.getInstance();
        a8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                String userEmail = e3.getText().toString(); // Replace with user's email address

                auth.sendPasswordResetEmail(userEmail)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity2.this, "Password reset email sent to " + userEmail, Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity2.this, "Failed to send password reset email.", Toast.LENGTH_SHORT).show();
                                    Toast.makeText(MainActivity2.this, "MAKE SURE you have entered your email in above enter email field!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });



        b3.setOnClickListener(v -> {
            String s1=e3.getText().toString();
            String s2=e4.getText().toString();
            if(s1.isEmpty())
            {e3.setError("This Field Can't Be Empty");
                n.setImageResource(R.drawable.m3);

            }
            else
            {if(s2.isEmpty()) {
                e4.setError("This Field Can't Be Empty");
                n.setImageResource(R.drawable.m3);

            }
            else
            {p2.setVisibility(View.VISIBLE);
                firebaseAuth.signInWithEmailAndPassword(s1,s2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                        if(user.isEmailVerified()){
                        if(task.isSuccessful())
                        {
                            p2.setVisibility(View.INVISIBLE);
                            n.setVisibility(View.INVISIBLE);
                            Toast.makeText(MainActivity2.this, "Successfully Logged In...", Toast.LENGTH_SHORT).show();
                            mp.start();
                            Intent i=new Intent(MainActivity2.this,Signout.class);
                            startActivity(i);
                            finish();


                        }
                        else
                        {
                            n.setImageResource(R.drawable.m3);
                            Toast.makeText(MainActivity2.this, "Error ", Toast.LENGTH_SHORT).show();
                            Toast.makeText(MainActivity2.this, "Please Make sure you are registered!", Toast.LENGTH_SHORT).show();
                            p2.setVisibility(View.INVISIBLE);
                        }}
                        else{user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                p2.setVisibility(View.INVISIBLE);
                                n.setImageResource(R.drawable.m3);
                                Toast.makeText(MainActivity2.this, "Kindly Verify Your Email", Toast.LENGTH_SHORT).show();
                                Toast.makeText(MainActivity2.this, "Verification Link Sent To Your Email", Toast.LENGTH_SHORT).show();
                                Toast.makeText(MainActivity2.this, "Check in Spam Folder If email not found", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity2.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                        }
                    }

                });
            }
            }


        });
        b4.setOnClickListener(v -> {
            Intent i=new Intent(MainActivity2.this, first.class);
            startActivity(i);
            finish();
        });

        }}