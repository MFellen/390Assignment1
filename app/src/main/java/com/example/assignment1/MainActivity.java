package com.example.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //ui declarations
    protected SharedPreferenceHelper sharedPreferenceHelper;
    private Button settingsButton, eventAButton, eventBButton, eventCButton, countButton;
    private TextView countTV;

    //variable declarations
    private int countA, countB, countC;
    private int max = 0;
    private int count = 0;
    protected String events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar settingsToolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(settingsToolbar);
        if (sharedPreferenceHelper == null) {
            sharedPreferenceHelper = new SharedPreferenceHelper(MainActivity.this);
        }
        setupUI();

    }
    protected void onStart() {
        //initialize variables
        max = sharedPreferenceHelper.getMaxCount();
        events = sharedPreferenceHelper.getEvents();
        count = sharedPreferenceHelper.getCount();
        //set button names
        eventAButton.setText(sharedPreferenceHelper.getEventA());
        eventBButton.setText(sharedPreferenceHelper.getEventB());
        eventCButton.setText(sharedPreferenceHelper.getEventC());
        super.onStart();
    }

    private void setupUI() {
        settingsButton = findViewById(R.id.settingsButton);
        eventAButton = findViewById(R.id.eventAButton);
        eventBButton = findViewById(R.id.eventBButton);
        eventCButton = findViewById(R.id.eventCButton);
        countButton = findViewById(R.id.countButton);
        countTV = findViewById(R.id.countTextView);
        countTV.setText("Total Count: " + count);

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSettings();
            }
        });

        eventAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sharedPreferenceHelper.getEventA() == null) {
                    goToSettings();
                } else if (count < max) {
                    count++;
                    countA++;
                    events = events + "1,";
                    countTV.setText("Total Count: " + count);
                }
            }
        });
        eventBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sharedPreferenceHelper.getEventB() == null) {
                    goToSettings();
                } else if (count < max) {
                    count++;
                    countB++;
                    events = events + "2,";
                    countTV.setText("Total Count: " + count);
                }
            }
        });
        eventCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sharedPreferenceHelper.getEventC() == null) {
                    goToSettings();
                } else if (count < max) {
                    count++;
                    countC++;
                    events = events + "3,";
                    countTV.setText("Total Count: " + count);
                }
            }
        });

        countButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    sharedPreferenceHelper.saveCount(countA, countB, countC, count, events);
                goToData();
            }
        });
    }

    private void goToSettings() {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    private void goToData() {
        Intent intent = new Intent(MainActivity.this, DataActivity.class);
        startActivity(intent);
    }
}