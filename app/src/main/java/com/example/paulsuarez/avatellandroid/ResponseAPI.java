package com.example.paulsuarez.avatellandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.paulsuarez.avatellandroid.POJO.TaxCodeSummary;
import com.example.paulsuarez.avatellandroid.POJO.TaxRateByTaxCode;
import com.google.gson.Gson;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResponseAPI extends AppCompatActivity {

    private ImageButton home;
    private ImageButton searchAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response_api);


        String storeCountry = getIntent().getStringExtra("ID3");
        String storeZip = getIntent().getStringExtra("ID4");

        String taxCode = getIntent().getStringExtra("ID5");
        String description = getIntent().getStringExtra("ID6");
        String taxZipCode = getIntent().getStringExtra("ID7");

        String companyCode = getIntent().getStringExtra("ID8");
        String customerCode = getIntent().getStringExtra("ID9");
        String amount = getIntent().getStringExtra("ID10");
        String quantity = getIntent().getStringExtra("ID11");

        System.out.println("THIS IS THE storeCountry VARIABLE: " + storeCountry);
        System.out.println("THIS IS THE storeZip VARIABLE: " + storeZip);

//        String email = getIntent().getStringExtra("ID1");
//        String pass = getIntent().getStringExtra("ID2");
//
//        System.out.println("*!RESPONSE!* THIS IS THE EMAIL VARIABLE: " + email);
//        System.out.println("*!RESPONSE!* THIS IS THE PASS VARIABLE: " + pass);



        // just the button for going back home

        home = findViewById(R.id.home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResponseAPI.this, MainActivity
                        .class);
                startActivity(intent);
            }
        });

        searchAgain = findViewById(R.id.goBackToSearch);

        searchAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = getIntent().getStringExtra("ID1");
                String password = getIntent().getStringExtra("ID2");
                Intent intent = new Intent(ResponseAPI.this, API.class);
                intent.putExtra("ID1",email);
                intent.putExtra("ID2",password);
                startActivity(intent);
            }
        });


//        // the below is to push the auth thru again on this activity
//
//        final TextView mTextViewAuth = (TextView) findViewById(R.id.text);
//
//        // Optionally, you can just use the default CookieManager
//        CookieManager manager = new CookieManager();
//        CookieHandler.setDefault(manager);
//
//        RequestQueue queueAuth = Volley.newRequestQueue(this);
//        //        String email = "%40";
//        //        String pass = "";
//        String urlAuth = "http://www.avatell.net/auth?user="  + email + "&pass=" + pass;
//        System.out.println("THIS IS THE URL FROM USER LOGIN " + urlAuth);
//
//        //        String encoded = URLEncoder.encode("m   e@site.com");
//
//        // Request a string response from the provided URL.
//        StringRequest stringRequestAuth = new StringRequest(Request.Method.GET, urlAuth,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Display the first 500 characters of the response string.
//                        mTextViewAuth.setText("LOGIN SUCCESSFUL! Response is: "+ response.toString());
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        mTextViewAuth.setText("That didn't work! " + error.getMessage());
//                    }
//                }) {
//
//            @Override
//            protected Response<String> parseNetworkResponse(NetworkResponse response) {
//                for (Map.Entry<String, String> entry : response.headers.entrySet()) {
//                    String key = entry.getKey();
//                    String value = entry.getValue();
//                    Log.d("HEADERS", "key: " + key + " value: " + value);
//                }
//                return super.parseNetworkResponse(response);
//            }
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//
//                String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36";
//                params.put("User-Agent", userAgent);
//                params.put("Connection", "keep-alive");
//                return params;
//            }
//
//            ;
//        };
//        // above is another login request


        // displays url to activity view

        final TextView mTextView = (TextView) findViewById(R.id.mTextView);

        RequestQueue queue = Volley.newRequestQueue(this);

        // http://www.avatell.net/query/byzipcode?country=USA&zipCode=98148

        String url = "http://www.avatell.net/query/byzipcode?country="  + storeCountry + "&zipCode=" + storeZip;
        System.out.println("THIS IS THE URL FROM THE COUNTRY AND ZIP SEARCH " + url);

//        String encoded = URLEncoder.encode("m   e@site.com");

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        mTextView.setText("SEARCH SUCCESSFUL! Response is: "+ response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work! " + error.getMessage());
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);








        // THIS is where the "Long" form will respond to from API.java's form input

        // displays url to activity view

        final TextView mTextViewLong = findViewById(R.id.mTextViewLong);
        final TextView mTotalTaxResponse = findViewById(R.id.totalTaxResponse);
        final TextView mCustomerCode = findViewById(R.id.customerCode);
        final TextView mCurrencyCode = findViewById(R.id.currencyCode);
        final TextView mOrderAmount = findViewById(R.id.orderAmount);
        final TextView mExemptAmount = findViewById(R.id.exemptAmount);
        final TextView mTaxableAmount = findViewById(R.id.taxableAmount);


        RequestQueue queueLong = Volley.newRequestQueue(this);

//        http://localhost:8080/api/query/bytaxcode?taxcode=PC040100&description=clothing&taxzipcode=98026

        // http://www.avatell.net/api/query/bytaxcode?companyCode=SELFEMPLOYED&customerCode=ABC&taxzipcode=98148&taxcode=PC040100&description=clothing&amount=100&quantity=10


        String urlLong = "http://www.avatell.net/api/query/bytaxcode?companyCode=" + companyCode + "&customerCode=" + customerCode + "&taxzipcode=" + taxZipCode + "&taxcode="  + taxCode + "&description=" + description + "&amount=" + amount + "&quantity=" + quantity;
        System.out.println("THIS IS THE URL FROM THE COUNTRY AND ZIP SEARCH " + urlLong);


        // Request a string response from the provided URL.
        StringRequest stringRequestLong = new StringRequest(Request.Method.GET, urlLong,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        TaxRateByTaxCode parsedResponse = gson.fromJson(response, TaxRateByTaxCode.class);

                        double taxRate = (parsedResponse.totalTaxable/parsedResponse.totalTax);
                        String taxRateClipped = String.valueOf(taxRate).substring(0, 6);
                        // Display the first 500 characters of the response string.
                        //mTextViewLong.setText(response);
                        mTextViewLong.setText("Your total tax rate is:");
                        mTotalTaxResponse.setText(taxRateClipped + "%");

                        //Setting up Detail area of response
                        mCustomerCode.setText(parsedResponse.customerCode);
                        mCurrencyCode.setText(parsedResponse.currencyCode);
                        mOrderAmount.setText("$" + parsedResponse.totalAmount);
                        mExemptAmount.setText("$" + parsedResponse.totalExempt);
                        mTaxableAmount.setText("$" + parsedResponse.totalTaxable);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextViewLong.setText("That didn't work! " + error.getMessage());
            }
        });

        // Add the request to the RequestQueue.
        queueLong.add(stringRequestLong);




    }
}
