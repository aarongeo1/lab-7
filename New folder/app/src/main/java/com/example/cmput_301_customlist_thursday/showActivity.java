package com.example.cmput_301_customlist_thursday;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class showActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        // Get the intent that started this activity and extract the city name
        Intent intent = getIntent();
        String cityName = intent.getStringExtra("cityName");

        // Capture the layout's TextView and set the string as its text
        TextView cityTextView = findViewById(R.id.city_text);
        cityTextView.setText(cityName);

        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}
