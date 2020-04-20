package com.example.mobiilisovellus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class Tehtava_Esikatselu extends AppCompatActivity  {

    private static final int MY_REQUEST_CODE = 1;

    ArrayAdapter<String> adapter;
    ArrayList<String> list = new ArrayList<>();
    ListView listView;
    TextView missionHeadline;
    //Button statebtn;
    //String str;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tehtava_esikatselu);

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
            case R.id.returnButton:
                startActivity(new Intent(Tehtava_Esikatselu.this, MainActivity.class));
                break;
            case R.id.editButton:
                startActivity(new Intent(Tehtava_Esikatselu.this, Lisaa_Tehtava.class));
                break;
            case R.id.addSubtask:
                Intent intent = new Intent(Tehtava_Esikatselu.this, Lisaa_Alitehtava.class);
                startActivityForResult(intent, MY_REQUEST_CODE);
                break;

        }

    }
};

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == MY_REQUEST_CODE){
            if (resultCode == RESULT_OK){
                String subtaskname = data.getStringExtra("keyName");

                list.add(subtaskname);
                adapter.notifyDataSetChanged();
            }
        }
    }

   /*public void buttonState()            //Määrittää suoritspainikkeen värin ja tekstin
   {
       Button btn = findViewById(R.id.setStateButton);
           btn.setBackgroundColor(Color.GREEN);
           btn.setText("Valmis");
           if(btn = btn.setBackgroundColor(Color.GREEN))

   }*/


}

