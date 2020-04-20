package com.example.mobiilisovellus;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Lisaa_Alitehtava extends AppCompatActivity implements View.OnClickListener {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_lisaa_alitehtava);

        findViewById(R.id.saveSubTask).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.saveSubTask){

            EditText editor = findViewById(R.id.SubTaskNameEditor);
            String value = editor.getText().toString();
            Intent intent = new Intent();
            intent.putExtra("keyName", value);
            setResult(RESULT_OK, intent);
            finish();
        }

    }

    }

