package com.iata.reifly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.Arrays;

public class LetsConnect extends AppCompatActivity {

    private String[] mockData = new String[]{"gmail.com", "yahoo.com"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lets_connect);

        Button continueBtn = (Button) findViewById(R.id.continueBtn);
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LetsConnect.this, TravelProfile.class));
            }
        });
        Spinner dropdown = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,mockData);
        dropdown.setAdapter(adapter);
    }
}
