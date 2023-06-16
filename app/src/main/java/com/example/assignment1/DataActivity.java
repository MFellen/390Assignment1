package com.example.assignment1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;

public class DataActivity extends MainActivity {

    private TextView numATV, numBTV, numCTV, totalTV;
    private ListView eventsLV;

    boolean names = true;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_data, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.toggleEventAction) {
            names = !names;
            toggleNames();
        } else if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(DataActivity.this, MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        Toolbar settingsToolbar = findViewById(R.id.dataToolbar);
        settingsToolbar.setTitle("Data");
        setSupportActionBar(settingsToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setupUI();
    }

    private void setupUI() {
        numATV = findViewById(R.id.eventANumTextView);
        numATV.setText(sharedPreferenceHelper.getEventA() + ": " + sharedPreferenceHelper.getCountA() + " Events");

        numBTV = findViewById(R.id.eventBNumTextView);
        numBTV.setText(sharedPreferenceHelper.getEventB() + ": " + sharedPreferenceHelper.getCountB() + " Events");

        numCTV = findViewById(R.id.eventCNumTextView);
        numCTV.setText(sharedPreferenceHelper.getEventC() + ": " + sharedPreferenceHelper.getCountC() + " Events");

        totalTV = findViewById(R.id.totalCountTextView);
        totalTV.setText("Total Events: " + sharedPreferenceHelper.getCount());

        eventsLV = findViewById(R.id.eventsListView);
       toggleNames();
    }

    public void toggleNames(){
        String events = sharedPreferenceHelper.getEvents();
        String eventA = sharedPreferenceHelper.getEventA();
        String eventB = sharedPreferenceHelper.getEventB();
        String eventC = sharedPreferenceHelper.getEventC();
        if (names == true){
            events = events.replace("1", eventA);
            events = events.replace("2", eventB);
            events = events.replace("3", eventC);
        }
        String[] eventsArr = events.split(",");
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.activity_datalistview,  eventsArr);
        eventsLV.setAdapter(adapter);
    }
}