package com.example.paulsuarez.avatellandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Header;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class API extends AppCompatActivity {
    private ImageButton home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);



        final String email = getIntent().getStringExtra("ID1");
        final String pass = getIntent().getStringExtra("ID2");

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

        // Optionally, you can just use the default CookieManager
        CookieManager manager = new CookieManager();
        CookieHandler.setDefault(manager);

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
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mTextView.setText("That didn't work! " + error.getMessage());
                    }
                }) {

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                for (Map.Entry<String, String> entry : response.headers.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    Log.d("HEADERS", "key: " + key + " value: " + value);
                }
                return super.parseNetworkResponse(response);
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36";
                params.put("User-Agent", userAgent);
                params.put("Connection", "keep-alive");
                return params;
            }

            ;
        };


        // Add the request to the RequestQueue.
        queue.add(stringRequest);

        // takes us to API results
        ImageButton submit2Result;

        submit2Result = findViewById(R.id.submit2Result);

        submit2Result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // fields to type in user login info and hit the submit button to go to api results

                EditText country = findViewById(R.id.country);
                final String storeCountry = country.getText().toString();

                EditText zip = findViewById(R.id.zip);
                final String storeZip = zip.getText().toString();

                System.out.println("THIS IS THE storeCountry VARIABLE: " + storeCountry);
                System.out.println("THIS IS THE storeZip VARIABLE: " + storeZip);

                System.out.println("THIS IS THE storeCountry VARIABLE: " + storeCountry);
                System.out.println("THIS IS THE storeZip VARIABLE: " + storeZip);
                Intent intent = new Intent(API.this, ResponseAPI.class);
                intent.putExtra("ID3", storeCountry);
                intent.putExtra("ID4", storeZip);
                intent.putExtra("ID1", email);
                intent.putExtra("ID2", pass);
                startActivity(intent);
            }
        });
    }
}
