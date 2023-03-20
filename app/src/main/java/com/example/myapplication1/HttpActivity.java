package com.example.myapplication1;

import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public abstract class HttpActivity extends AppCompatActivity {
    String http = "http://192.168.145.238/";

    protected void send(String type, Map<String, String> params) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, http + type,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ResponseReceived(response,params);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Error occurred, show error message
                        Toast.makeText(HttpActivity.this, "Error occurred: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        // Add request to queue
        Volley.newRequestQueue(this).add(stringRequest);

    }
    protected abstract void ResponseReceived(String response,Map<String,String>params);
}
