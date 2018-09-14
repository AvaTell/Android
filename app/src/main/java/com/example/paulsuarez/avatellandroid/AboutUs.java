package com.example.paulsuarez.avatellandroid;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class AboutUs extends AppCompatActivity {


    private ImageButton home;
    private ImageView panosGitHub;
    private ImageView panosLinkedIn;
    private ImageView paulGitHub;
    private ImageView paulLinkedIn;
    private ImageView amyGitHub;
    private ImageView amyLinkedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutus);


        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutUs.this, MainActivity
                        .class);
                startActivity(intent);
            }
        });

        //Panos Navigation
        panosGitHub = findViewById(R.id.panosGitHub);
        panosGitHub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToUrl("https://github.com/spinaltaper");
            }
        });
        panosLinkedIn = findViewById(R.id.panosLinkedIn);
        panosLinkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToUrl("https://www.linkedin.com/in/panos-kumasaka/");
            }
        });

        //Paul Navigation
        paulGitHub = findViewById(R.id.paulGitHub);
        paulGitHub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToUrl("https://github.com/PaulSuarez1");
            }
        });
        paulLinkedIn = findViewById(R.id.paulLinkedIn);
        paulLinkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToUrl("https://www.linkedin.com/in/paulsuarezsales/");
            }
        });

        //Amy Navigation
        amyGitHub = findViewById(R.id.amyGitHub);
        amyGitHub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToUrl("https://github.com/AmyCohen");
            }
        });
        amyLinkedIn = findViewById(R.id.amyLinkedIn);
        amyLinkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToUrl("https://www.linkedin.com/in/amyvcohen/");
            }
        });



    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent WebView = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(WebView);
    }
}
