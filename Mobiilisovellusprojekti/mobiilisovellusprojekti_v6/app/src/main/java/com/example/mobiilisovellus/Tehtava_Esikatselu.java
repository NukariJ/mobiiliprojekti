package com.example.mobiilisovellus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Tehtava_Esikatselu extends AppCompatActivity  {

    TextView missionHeadline;
    //Button statebtn;
    //String str;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_Tehtava_Esikatselu);

        //str = getIntent().getStringExtra("msg");

        findViewById(R.id.addSubtask).setOnClickListener(buttonClickListener);
        findViewById(R.id.returnButton).setOnClickListener(buttonClickListener);
        findViewById(R.id.editButton).setOnClickListener(buttonClickListener);
        //statebtn = findViewById(R.id.setStateButton).setOnTouchListener();
        missionHeadline = findViewById(R.id.taskName);

       // missionHeadline.setText(getIntent().getStringExtra("msg"));     //Tarkoitus vastaanottaa tehtävän otsikon nimi



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

