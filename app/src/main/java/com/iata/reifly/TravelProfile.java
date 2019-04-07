package com.iata.reifly;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class TravelProfile extends AppCompatActivity {

    private ArrayList<String> homeAirportsMock = new ArrayList<String>(Arrays.asList("Seattle - SEA", "Portland - PDX"));
    private ArrayList<String> dreamCities = new ArrayList<String>(Arrays.asList( "Tokyo", "Taipei", "Bali", "Paris"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_profile);

        Spinner homeAirports = (Spinner) findViewById(R.id.homeAirports);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,homeAirportsMock);
        homeAirports.setAdapter(adapter);

    }
}
