package com.example.mobiilisovellus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Tehtava_Esikatselu extends AppCompatActivity {

    TextView taskName;
    TextView taskInfo;
    TextView taskDate;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tehtava_esikatselu);

        //Hakee tiedot MainActivitystä
        Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");
        String info = intent.getStringExtra("DESCRIPTION");
        String date = intent.getStringExtra("DATE");

        findViewById(R.id.addSubtask).setOnClickListener(buttonClickListener);
        findViewById(R.id.returnButton).setOnClickListener(buttonClickListener);
        findViewById(R.id.editButton).setOnClickListener(buttonClickListener);

        //Tehtävien tiedot
        taskName = findViewById(R.id.taskName);
        taskInfo = findViewById(R.id.taskDescription);
        taskDate = findViewById(R.id.timeSet);
        taskName.setText(name);
        taskInfo.setText(info);
        taskDate.setText(date);

}



public View.OnClickListener buttonClickListener = new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.subTask:
                startActivity(new Intent(Tehtava_Esikatselu.this, Alitehtava_Esikatselu.class));
                break;
            case R.id.returnButton:
                startActivity(new Intent(Tehtava_Esikatselu.this, MainActivity.class));
                break;
            case R.id.editButton:
                startActivity(new Intent(Tehtava_Esikatselu.this, Lisaa_Tehtava.class));
                break;
            case R.id.addSubtask:
                startActivity(new Intent(Tehtava_Esikatselu.this, Lisaa_Alitehtava.class));
                break;

        }

    }
};

   /*public void buttonState()            //Määrittää suoritspainikkeen värin ja tekstin
   {
       Button btn = findViewById(R.id.setStateButton);
           btn.setBackgroundColor(Color.GREEN);
           btn.setText("Valmis");
           if(btn = btn.setBackgroundColor(Color.GREEN))

   }*/


}

