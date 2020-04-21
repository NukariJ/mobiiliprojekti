package com.example.mobiilisovellus;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Lisaa_Alitehtava extends AppCompatActivity implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lisaa_alitehtava);

        findViewById(R.id.saveSubTask).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.saveSubTask){

            Intent resultIntent = new Intent();

            EditText alitehtavannimi = findViewById(R.id.SubTaskNameEditor);
            String nimi = alitehtavannimi.getText().toString();

            TextView alitehtavankuvaus = findViewById(R.id.taskDescription);
            String kuvaus = alitehtavankuvaus.getText().toString();

            Alitehtava at = new Alitehtava(nimi,kuvaus);
            resultIntent.putExtra("key name", at);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        }


    }

    }

