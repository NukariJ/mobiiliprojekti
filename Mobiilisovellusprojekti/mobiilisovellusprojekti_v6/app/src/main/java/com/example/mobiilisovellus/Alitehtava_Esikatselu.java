package com.example.mobiilisovellus;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

public class Alitehtava_Esikatselu extends AppCompatActivity {

    Button subTaskPercent;
    int task = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alitehtava_esikatselu);

        subTaskPercent = (Button) findViewById(R.id.subTaskPercent);

        subTaskPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subTaskPercent.setBackgroundColor(Color.GREEN);
                subTaskPercent.setText("Valmis");
                subTaskPercent.setEnabled(false);
                task += 1;
            }
        });
    }

}