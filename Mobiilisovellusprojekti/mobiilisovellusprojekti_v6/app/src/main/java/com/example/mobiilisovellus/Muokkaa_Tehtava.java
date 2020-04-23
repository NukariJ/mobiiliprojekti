package com.example.mobiilisovellus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Muokkaa_Tehtava extends AppCompatActivity implements View.OnClickListener {

    String name;
    String task;

    EditText editName;
    EditText editTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muokkaa__tehtava);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        task = intent.getStringExtra("kuvaus");


        editName = findViewById(R.id.addTaskname);
        editTask = findViewById(R.id.taskDescription);

        editName.setText(name);
        editTask.setText(task);


        findViewById(R.id.saveTask).setOnClickListener(this);
        findViewById(R.id.backButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.saveTask) {

            Intent resultIntent = new Intent();

            try {
                EditText aika = findViewById(R.id.taskDuration);
                int tehtavanKesto = Integer.parseInt(aika.getText().toString());

                EditText tehtavanKuvaus = findViewById(R.id.taskDescription);
                String kuvaus = tehtavanKuvaus.getText().toString();

                TextView tehtavaNimi = findViewById(R.id.addTaskname);
                String nimi = tehtavaNimi.getText().toString();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                String time2 = LocalDateTime.now().plusDays(tehtavanKesto).format(formatter);

                resultIntent.putExtra("LisattyTehtava", nimi);
                resultIntent.putExtra("LisattyKuvaus", kuvaus);
                resultIntent.putExtra("LisattyAika", time2);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();

            } catch (Exception e) {
                e.printStackTrace();

            }
            if (view.getId() == R.id.backButton) {
                finish();
            }

        }
    }
}
