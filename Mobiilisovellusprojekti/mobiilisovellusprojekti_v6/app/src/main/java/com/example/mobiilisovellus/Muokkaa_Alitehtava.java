package com.example.mobiilisovellus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Muokkaa_Alitehtava extends AppCompatActivity implements View.OnClickListener {

    String name;
    String task;

    EditText editName;
    EditText editTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muokkaa__alitehtava);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        task = intent.getStringExtra("kuvaus");


        editName = findViewById(R.id.SubTaskNameEditor);
        editTask = findViewById(R.id.taskDescription);

        editName.setText(name);
        editTask.setText(task);

        findViewById(R.id.returnButton).setOnClickListener(this);
        findViewById(R.id.saveSubTask).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.returnButton) {
            finish();
        }
        if (v.getId() == R.id.saveSubTask) {

            Intent resultIntent = new Intent();

            try {
                EditText tehtavanKuvaus = findViewById(R.id.taskDescription);
                String kuvaus = tehtavanKuvaus.getText().toString();

                TextView tehtavaNimi = findViewById(R.id.SubTaskNameEditor);
                String nimi = tehtavaNimi.getText().toString();


                resultIntent.putExtra("LisattyTehtava", nimi);
                resultIntent.putExtra("LisattyKuvaus", kuvaus);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}