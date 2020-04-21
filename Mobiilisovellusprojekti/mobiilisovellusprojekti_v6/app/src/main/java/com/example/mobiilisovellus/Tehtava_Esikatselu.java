package com.example.mobiilisovellus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Tehtava_Esikatselu extends AppCompatActivity {

    TextView taskName;
    TextView taskInfo;
    TextView taskDate;
    private String date;
    private static final int MY_REQUEST_CODE = 1;

    private ArrayList<Alitehtava> alitehtavaList;
    private AlitehtavaAdapter atAdapter;
    private ListView listView;
    TextView missionHeadline;
    //Button statebtn;
    //String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tehtava_esikatselu);

        alitehtavaList = new ArrayList<>();

        //Hakee tiedot MainActivitystä
        Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");
        String info = intent.getStringExtra("DESCRIPTION");
        date = intent.getStringExtra("DATE");

        Bundle bundle;
        bundle = intent.getBundleExtra("BUNDLE");
        alitehtavaList = (ArrayList<Alitehtava>) bundle.getSerializable("SUBTASKLIST");

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

        listView = findViewById(R.id.subTaskList);
        atAdapter = new AlitehtavaAdapter(this,alitehtavaList);
        listView.setAdapter(atAdapter);

    }



    public View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.returnButton:
                    sendDataBackToMain();
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

        if (requestCode == MY_REQUEST_CODE && resultCode == Activity.RESULT_OK)
        {
            Alitehtava saatuAlitehtava = (Alitehtava) data.getSerializableExtra("key name");
            atAdapter.add(saatuAlitehtava);
        }
    }

   /*public void buttonState()            //Määrittää suoritspainikkeen värin ja tekstin
   {
       Button btn = findViewById(R.id.setStateButton);
           btn.setBackgroundColor(Color.GREEN);
           btn.setText("Valmis");
           if(btn = btn.setBackgroundColor(Color.GREEN))

   }*/


    private void sendDataBackToMain() {

        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("ReturnAlitehtava", (Serializable) alitehtavaList);
        intent.putExtra("RETURNBUNDLE", bundle);
        intent.putExtra("ID",date);
        Log.d("ID testi",date);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }

}

