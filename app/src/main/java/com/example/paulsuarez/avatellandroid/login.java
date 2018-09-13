package com.example.paulsuarez.avatellandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class login extends AppCompatActivity {
    private ImageButton home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Parser parser = new Parser();
        parser.loadList(this.getApplicationContext());
        parser.refine("a");
        parser.refine("ar");
        parser.refine("art");
        parser.backSpaceHandler(this.getApplicationContext());

        // takes us home
        home = findViewById(R.id.home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, MainActivity.class);
                startActivity(intent);
            }
        });


         // takes us to API results
        ImageButton submit;

        submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // fields to type in user login info and hit the submit button to go to api results

                // EMAIL get EditText by id and store it into "inputTxt"
                EditText inputEmail = findViewById(R.id.inputEmail);

                // EMAIL Store EditText - Input in variable
                final String email = inputEmail.getText().toString();

                // PASSWORD get EditText by id and store it into "inputTxt"
                EditText inputPassword = findViewById(R.id.inputPassword);

                // PASSWORD Store EditText - Input in variable
                final String pass = inputPassword.getText().toString();

                System.out.println("THIS IS THE EMAIL VARIABLE: " + email);
                System.out.println("THIS IS THE PASS VARIABLE: " + pass);

                System.out.println("THIS IS THE EMAIL VARIABLE: " + email);
                System.out.println("THIS IS THE PASS VARIABLE: " + pass);
                Intent intent = new Intent(login.this, API.class);
                intent.putExtra("ID1", email);
                intent.putExtra("ID2", pass);
                startActivity(intent);
            }
        });


    }
}
