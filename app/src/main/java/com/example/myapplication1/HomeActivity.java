package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String firstName = preferences.getString("familyname", "");
        String familyName = preferences.getString("firstname", "");
        TextView welcomeTextView = findViewById(R.id.textview);
        welcomeTextView.setText("Hello " + firstName + " " + familyName);

//        Button getStartedButton = findViewById(R.id.logout);
//        getStartedButton.setOnClickListener(v -> {
//            preferences.edit().clear().apply();
//            Intent intent = new Intent(HomeActivity.this, WelcomeActivity.class);
//            startActivity(intent);
//
//        });
    }
}