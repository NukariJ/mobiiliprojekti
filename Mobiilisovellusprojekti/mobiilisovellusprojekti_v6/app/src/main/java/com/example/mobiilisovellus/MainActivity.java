package com.example.mobiilisovellus;


import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;


import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity implements TehtavaAjastin.tehtavaRaportti {
    private ArrayList<Tehtava> tehtavaLista;
    private ArrayList<EventDay> kalenteriLista;
    private ListView tehtavaListView;
    private TehtavaAdapter tAdapter;
    private int palautusKoodi = 2000;
    private int valinta = -1;
    private ArrayList<Tehtava> tulosLista;
    private int indexi;
    private com.applandeo.materialcalendarview.CalendarView kalenteri;

    @SuppressLint("ResourceType")
    @RequiresApi(api = Build.VERSION_CODES.O)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tehtavaLista = new ArrayList<>();
        tehtavaListView = findViewById(R.id.tLista);

        kalenteri = findViewById(R.id.kalenteriNakyma);


        Calendar k = Calendar.getInstance();


        Date date = Date.from(LocalDate.now().plusDays(2).atStartOfDay(ZoneId.systemDefault()).toInstant());

        k.setTime(date);





        kalenteriLista = new ArrayList<>();
        kalenteriLista.add(new TehtavaEvent(k,R.drawable.lamb,"Juo kaljaa"));


        kalenteri.setEvents(kalenteriLista);

        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM HH:mm");

        LocalDateTime d = LocalDateTime.now();

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm:ss 'h'");





        tehtavaLista.add(new Tehtava("Juo kalja",LocalDateTime.now().plusMinutes(1), 30));
        tehtavaLista.add(new Tehtava("Juo toinen",LocalDateTime.now().plusMinutes(2), 50));
        tehtavaLista.add(new Tehtava("Ja toinen",LocalDateTime.now().plusMinutes(3), 70));
        tehtavaLista.add(new Tehtava("Juo",LocalDateTime.now().plusMinutes(4), 79));
        tehtavaLista.add(new Tehtava("Juo lisää",LocalDateTime.now().plusMinutes(5), 74));
        tehtavaLista.add(new Tehtava("Juo vettä",LocalDateTime.now().plusMinutes(6), 55));

        tAdapter = new TehtavaAdapter(this,tehtavaLista);
        tehtavaListView.setAdapter(tAdapter);








        tehtavaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                //valitaan listalta tehtävä jota halutaan tarkastella lähemmin

                valinta = position;

                // lisää tominnallisuus tehtävien tarkasteluun
            }
        });

        kalenteri.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(final EventDay eventDay) {

                if (eventDay instanceof TehtavaEvent) {

                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TehtavaEvent a = (TehtavaEvent) eventDay;
                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("Päättyvät tehtävät " + a.getCalendar().getTime().getDay()+"."+a.getCalendar().getTime().getMonth())
                                    .setMessage(a.getTehtavaNote())

                                    .setNegativeButton(android.R.string.no, null)
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();
                        }
                    });

                }
            }
        });




        TehtavaAjastin ajastin = new TehtavaAjastin(MainActivity.this);
        ajastin.lataaTehtavat(tehtavaLista);
        ajastin.execute();



    } @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.item1:
                Toast.makeText(this, "Item 1 is selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item2:
                Toast.makeText(this, "Item 2 is selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                Toast.makeText(this, "Item 3 is selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item4:
                Toast.makeText(this, "Item 4 is selected is selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item5:
                Toast.makeText(this, "Item 5 is selected", Toast.LENGTH_SHORT).show();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }


    public void lisaaTehtava(View view) {

        // Avaa TehtäväOsioPäänäkymän

        Intent intent = new Intent(this,Lisaa_Tehtava.class);
        startActivityForResult(intent,palautusKoodi);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        //TehtväPääosionäkymässä luotu tieto lisätään listalle

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == palautusKoodi && resultCode == Activity.RESULT_OK)
        {
            Tehtava saatuTehtava = (Tehtava) data.getSerializableExtra("LisattyTehtava");
            tAdapter.add(saatuTehtava);
        }
    }


    @Override
    public void lahetaRaportti(ArrayList<Tehtava> list) {

        tulosLista = list;

        if (!list.isEmpty()) {
            for(Tehtava i: tulosLista) {
                indexi = tehtavaLista.indexOf(i);
                tehtavaLista.get(indexi).setVanhentunut(true);


                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Tehtava Vanhentunut")
                                .setMessage("Tehtava: " + tehtavaLista.get(indexi).getNimi())

                                .setNegativeButton(android.R.string.no, null)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }
                });
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tAdapter.notifyDataSetChanged();
                    Log.d("uithread", "1");
                }
            });

        }
    }




}
