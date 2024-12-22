package com.example.plantapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        Button btnSignIn = findViewById(R.id.btnSignIn);
        Button btnSignUp = findViewById(R.id.btnSignUp);   
        Button btnInfo = findViewById(R.id.btnInfo);
        Button btnGoToReviews = findViewById(R.id.btnGoToReviews);

        btnSignIn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent signInIntent = new Intent(MainActivity.this,SignInActivity.class);
                startActivity(signInIntent);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent signUpIntent = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(signUpIntent);
            }
        });

        btnInfo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent infoIntent = new Intent(MainActivity.this,AllUsersActivity.class);
                startActivity(infoIntent);
            }
        });

        btnGoToReviews.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent revIntent = new Intent(MainActivity.this, ReviewActivity.class);
                startActivity(revIntent);
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}