package com.example.mobiilisovellus;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TehtavaOsio extends AppCompatActivity {

    Button subTaskPercent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tehtava_osio);

        subTaskPercent = (Button) findViewById(R.id.subTaskPercent);

        subTaskPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subTaskPercent.setBackgroundColor(Color.GREEN);
                subTaskPercent.setText("Valmis");

            }
        });
    }

}