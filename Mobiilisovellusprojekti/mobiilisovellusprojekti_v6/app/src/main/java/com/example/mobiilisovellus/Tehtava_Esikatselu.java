package com.example.mobiilisovellus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
    TextView taskPercent;

    private Tehtava tarkasteltavaTehtava;
    private static final int MY_REQUEST_CODE = 1;
    private static final int MY_REQUEST_CODE2 = 2;

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
        tarkasteltavaTehtava = (Tehtava) intent.getSerializableExtra("SiirrettavaTehtava");
        tarkasteltavaTehtava.laskeSuoritusProsentti();
        String name = tarkasteltavaTehtava.getNimi();
        String info = tarkasteltavaTehtava.getKuvaus();
        String date = tarkasteltavaTehtava.getPaivamaara();
        double taskP = tarkasteltavaTehtava.getSuoritettu();

        alitehtavaList = tarkasteltavaTehtava.getAliTehtava();

        findViewById(R.id.addSubtask).setOnClickListener(buttonClickListener);
        findViewById(R.id.returnButton).setOnClickListener(buttonClickListener);
        findViewById(R.id.editButton).setOnClickListener(buttonClickListener);

        //Tehtävien tiedot
        taskName = findViewById(R.id.taskName);
        taskInfo = findViewById(R.id.taskDescription);
        taskDate = findViewById(R.id.timeSet);
        taskPercent = findViewById(R.id.percentView);
        taskName.setText(name);
        taskInfo.setText(info);
        taskDate.setText(date);
        taskPercent.setText(taskP+" %");

        listView = findViewById(R.id.subTaskList);
        atAdapter = new AlitehtavaAdapter(this,alitehtavaList);
        listView.setAdapter(atAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l){


                Intent goToIntent = new Intent(Tehtava_Esikatselu.this,Alitehtava_Esikatselu.class);

                try {
                    goToIntent.putExtra("MOVEDSUBTASK",atAdapter.getItem(position));
                    startActivityForResult(goToIntent, MY_REQUEST_CODE2);
                }
                catch (Exception e) { e.printStackTrace(); }
            }
        });


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
            tarkasteltavaTehtava.laskeSuoritusProsentti();
            taskPercent.setText(tarkasteltavaTehtava.getSuoritettu()+" %");
        }
        if (requestCode == MY_REQUEST_CODE2 && resultCode == Activity.RESULT_OK)
        {
            Alitehtava saatuAlitehtava = (Alitehtava) data.getSerializableExtra("R_SUBTASK");
            for(Alitehtava a : alitehtavaList) {
                if(a.getAlitehtava_ID().equals(saatuAlitehtava.getAlitehtava_ID())) {
                    a.setAlitehtavannimi(saatuAlitehtava.getAlitehtavannimi());
                    a.setAlitehtavankuvaus(saatuAlitehtava.getAlitehtavankuvaus());
                    a.setSuoritettu(saatuAlitehtava.isSuoritettu());
                }
            }
            tarkasteltavaTehtava.laskeSuoritusProsentti();
            taskPercent.setText(tarkasteltavaTehtava.getSuoritettu()+" %");

        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //päivittää listanäkymään muutokset
                atAdapter.notifyDataSetChanged();
            }
        });


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
        intent.putExtra("PalautusTehtava",tarkasteltavaTehtava);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }

}

