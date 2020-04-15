package com.example.mobiilisovellus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Lisaa_Tehtava extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_Lisaa_Tehtava);
        findViewById(R.id.saveTask).setOnClickListener(this);
        findViewById(R.id.backButton).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {                        //Lähettää tehtävän nimen eteenpäin
        if (view.getId() == R.id.saveTask) {
            setTaskHeadline();
        }
        if(view.getId() == R.id.backButton) {
            startActivity(new Intent(Lisaa_Tehtava.this, Tehtava_Esikatselu.class));
        }
    }


    public void setTaskHeadline() {
        EditText headline = (EditText) findViewById(R.id.addTaskname);
        String headlineMessage = headline.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("LisattyTehtava", headlineMessage);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

}