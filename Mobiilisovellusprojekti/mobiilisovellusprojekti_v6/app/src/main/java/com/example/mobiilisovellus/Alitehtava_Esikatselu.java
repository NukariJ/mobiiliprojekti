package com.example.mobiilisovellus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
import android.widget.TextView;

public class Alitehtava_Esikatselu extends AppCompatActivity implements View.OnClickListener {

    TextView Subtaskname;
    TextView SubtaskDescription;

    Button button2;
    int buttonStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alitehtava_esikatselu);

        Intent intent = getIntent();
        String name = intent.getStringExtra("AT_NAME");
        String info = intent.getStringExtra("AT_DESCRIPTION");

        Subtaskname = findViewById(R.id.SubtaskName);
        SubtaskDescription = findViewById(R.id.SubtaskDescription);

        Subtaskname.setText(name);
        SubtaskDescription.setText(info);

        findViewById(R.id.subTaskPercent).setOnClickListener(this);
        findViewById(R.id.returnButton).setOnClickListener(this);
        findViewById(R.id.deleteTask).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.subTaskPercent) {
            buttonState();
        }
        else if (v.getId() == R.id.returnButton){
            finish();
        }
        else if(v.getId() == R.id.deleteTask){

        }
    }

    private void buttonState() {

        button2 = findViewById(R.id.subTaskPercent);
        if(buttonStatus == 0) {
            button2.setBackgroundColor(Color.GREEN);
            button2.setText("VALMIS");
            buttonStatus = 1;
        }
        else if(buttonStatus == 1) {
            button2.setBackgroundColor(Color.LTGRAY);
            button2.setText("KESKEN");
            buttonStatus = 0;
        }
    }
}