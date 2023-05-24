package com.example.mediamaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;


// Package related to volley
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


// We must have dependency added to build.gradle file for volley [ server data handling ]
public class RetrieveData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_data);

        // Step 2: Make a Volley Request
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://localhost:80/android/remote.php";

        Log.d("Msg", "hello");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("result", response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("exception", error.toString());
                    }
                }
        );

        // Add the request to the RequestQueue.
        requestQueue.add(stringRequest);

    }
}