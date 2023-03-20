package com.example.myapplication1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;

import java.util.Map;


public class SignUpActivity extends HttpActivity {
    private EditText firstnameEditText;
    private EditText familynameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText ageEditText;
    private EditText addressEditText;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);


        firstnameEditText = findViewById(R.id.first_name);
        familynameEditText = findViewById(R.id.family_name);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        ageEditText = findViewById(R.id.age);
        addressEditText = findViewById(R.id.address);
        signUpButton = findViewById(R.id.signup_button);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstname = firstnameEditText.getText().toString();
                String familyname = familynameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String age = ageEditText.getText().toString();
                String address = addressEditText.getText().toString();
//                    signUpButton.setText(R.string.Confirm);
                Map<String, String> params = new HashMap<>();
                params.put("firstname", firstname);
                params.put("familyname", familyname);
                params.put("email", email);
                params.put("password", password);
                params.put("age", age);
                params.put("address", address);
                send(SIGNUP, params);

            }
        });
    }

    protected void ResponseReceived(String response, Map<String, String> params) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            String status = jsonObject.getString("status");
            if (status.equals("success")) {
                JSONObject userObject = jsonObject.getJSONObject("user_info");
                String family_name = userObject.getString("family_name");
                String first_name = userObject.getString("first_name");
                String email = userObject.getString("email");
                int age = userObject.getInt("age");
                String address = userObject.getString("address");
                // Save user information to shared preferences
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SignUpActivity.this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("family_name", family_name);
                editor.putString("first_name", first_name);
                editor.putString("email", email);
                editor.putInt("age", age);
                editor.putString("address", address);
                editor.apply();
                // Start HomeActivity
                Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            } else {
                String errorMessage = jsonObject.getString("message");
                Toast.makeText(SignUpActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}