package com.example.mobiilisovellus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TehtavaOsioPaanakyma extends AppCompatActivity {

    TextView missionHeadline;
    //String str;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tehtava_osio_paanakyma);

        //str = getIntent().getStringExtra("msg");

        findViewById(R.id.addSubtask).setOnClickListener(buttonClickListener);
        findViewById(R.id.returnButton).setOnClickListener(buttonClickListener);
        findViewById(R.id.editButton).setOnClickListener(buttonClickListener);
        missionHeadline = findViewById(R.id.taskName);

        missionHeadline.setText(getIntent().getStringExtra("msg"));     //Tarkoitus vastaanottaa tehtävän otsikon nimi



}



public View.OnClickListener buttonClickListener = new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.subTask:
                startActivity(new Intent(TehtavaOsioPaanakyma.this, TehtavaOsio.class));
                break;
            case R.id.returnButton:
                startActivity(new Intent(TehtavaOsioPaanakyma.this, MainActivity.class));
                break;
            case R.id.editButton:
                startActivity(new Intent(TehtavaOsioPaanakyma.this, tehtavan_muokkaus.class));
                break;
            case R.id.addSubtask:
                startActivity(new Intent(TehtavaOsioPaanakyma.this, LisaaAlitehtava.class));
                break;

        }

    }
};
}

