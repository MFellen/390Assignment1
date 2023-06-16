package com.example.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

public class SettingsActivity extends MainActivity {

    EditText counter1ET, counter2ET, counter3ET, maxCountET;
    Button saveB;
    Settings settings;
    TextView errorTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar settingsToolbar = findViewById(R.id.settingsToolbar);
        settingsToolbar.setTitle("Settings");
        setSupportActionBar(settingsToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setupUI();
        int m = sharedPreferenceHelper.getMaxCount();
        edit(m == 0);
        Intent intent = getIntent();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.editAction) {
            edit(true);
        }
        else if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(SettingsActivity.this, MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }



    private void setupUI() {
        //filter for input
        int maxLength = 20;
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(maxLength);

        //mapping UI
        counter1ET = findViewById(R.id.counter1EditText);
        counter1ET.setFilters(FilterArray);
        counter2ET = findViewById(R.id.counter2EditText);
        counter2ET.setFilters(FilterArray);
        counter3ET = findViewById(R.id.counter3EditText);
        counter3ET.setFilters(FilterArray);
        maxCountET = findViewById(R.id.maxCountEditText);
        maxCountET.setFilters(FilterArray);
        saveB = findViewById(R.id.saveButton);
        errorTV = findViewById(R.id.errorTextView);

        saveB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int maxCount = Integer.parseInt(maxCountET.getText().toString());
                    String a = counter1ET.getText().toString();
                    String b = counter2ET.getText().toString();
                    String c = counter3ET.getText().toString();
                    if (5 <= maxCount && maxCount <= 200 && !(a.matches("") || b.matches("") || c.matches(""))) {
                        //clears preferenceHelper for new set of events and names
                        sharedPreferenceHelper.clearPreferences();
                        //saves preferences to sharedPreferenceHelper
                        settings = new Settings();
                        settings.setA(a);
                        settings.setB(b);
                        settings.setC(c);
                        settings.setMaxCount(maxCount);
                        sharedPreferenceHelper.saveSettings(settings);
                        edit(false);
                        errorTV.setText("");
                    } else {
                        errorTV.setText("ERROR");
                    }
                } catch (NumberFormatException e) {
                }


            }
        });
    }

    private void edit(boolean edit) {
        if (edit) {
            //activates in edit mode
            counter1ET.setEnabled(true);
            counter2ET.setEnabled(true);
            counter3ET.setEnabled(true);
            maxCountET.setEnabled(true);
            saveB.setEnabled(true);
        } else {
            //disbles in nonedit mode
            counter1ET.setEnabled(false);
            counter2ET.setEnabled(false);
            counter3ET.setEnabled(false);
            maxCountET.setEnabled(false);
            saveB.setEnabled(false);
        }
    }

}