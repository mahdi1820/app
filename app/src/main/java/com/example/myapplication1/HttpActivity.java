package com.example.myapplication1;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
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

    NetworkService networkService;
    boolean mBound = false;
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

    /** Defines callbacks for service
     binding, passed to bindService(). */
    protected ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,IBinder service) {
            // We've bound to NetworkService, cast
            //the IBinder and get LocalService instance.
            NetworkService.LocalBinder binder
                    = (NetworkService.LocalBinder) service;
            networkService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
}