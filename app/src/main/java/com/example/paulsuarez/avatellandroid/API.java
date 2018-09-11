package com.example.paulsuarez.avatellandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.URLEncoder;

public class API extends AppCompatActivity {
    private ImageButton home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);



        String email = getIntent().getStringExtra("ID1");
        String pass = getIntent().getStringExtra("ID2");

        System.out.println("THIS IS THE EMAIL VARIABLE: " + email);
        System.out.println("THIS IS THE PASS VARIABLE: " + pass);



        // just the button for going back home

        home = findViewById(R.id.home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(API.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // displays url to activity view

        final TextView mTextView = (TextView) findViewById(R.id.text);

        RequestQueue queue = Volley.newRequestQueue(this);
//        String email = "%40";
//        String pass = "";
        String url = "http://www.avatell.net/auth?user="  + email + "&pass=" + pass;
        System.out.println("THIS IS THE URL FROM USER LOGIN " + url);

//        String encoded = URLEncoder.encode("m   e@site.com");

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        mTextView.setText("LOGIN SUCCESSFUL! Response is: "+ response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work! " + error.getMessage());
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
