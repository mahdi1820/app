package com.example.myapplication1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    Button getStartedButton = findViewById(R.id.get_started_button);
        getStartedButton.setOnClickListener(v -> {
            Intent intent = new Intent(WelcomeActivity.this, SignUpActivity.class);
            startActivity(intent);

        });

    Button alreadyHaveAccountButton = findViewById(R.id.already_have_account_button);
        alreadyHaveAccountButton.setOnClickListener(v -> {
            Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
            startActivity(intent);

        });
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(WelcomeActivity.this);
        String email = preferences.getString("email", "");
        if (!email.equals("")) {
            Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
}}
