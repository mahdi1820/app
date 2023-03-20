package com.example.myapplication1;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

public abstract class HttpActivity extends AppCompatActivity {
    protected String http = "http://192.168.1.228/";

    protected String LOGIN = "login.php";

    protected String SIGNUP = "signup.php";

    protected void send(String type, Map<String, String> params) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, http + type,
                response -> ResponseReceived(response, params),
                error -> Toast.makeText(HttpActivity.this, "Error: " + error.toString(), Toast
                        .LENGTH_SHORT).show()) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);
    }

    protected abstract void ResponseReceived(String Response, Map<String, String> params);
}