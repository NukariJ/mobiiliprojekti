package com.example.mobiilisovellus;


import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;


import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity implements TehtavaAjastin.tehtavaRaportti {
    private ArrayList<Tehtava> tehtavaLista;
    private ArrayList<EventDay> kalenteriLista;
    private ListView tehtavaListView;
    private TehtavaAdapter tAdapter;
    private int palautusKoodi = 420;
    private int valinta = -1;
    private ArrayList<Tehtava> tulosLista;
    private int indexi;
    private com.applandeo.materialcalendarview.CalendarView kalenteri;
    private SharedPreferences shref;

    @SuppressLint("ResourceType")
    @RequiresApi(api = Build.VERSION_CODES.O)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lataaTehtavat();
        lataaKalenteri();



        tehtavaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                //valitaan listalta tehtävä jota halutaan tarkastella lähemmin

                valinta = position;
                Intent goToIntent = new Intent(MainActivity.this,Tehtava_Esikatselu.class);
                Bundle bundle = new Bundle();
                try {
                    //bundle.putSerializable("TehtavaTiedot",tehtavaLista.get(indexi).getNimi());
                    goToIntent.putExtra("NAME",tehtavaLista.get(position).getNimi());
                    goToIntent.putExtra("DESCRIPTION",tehtavaLista.get(position).getKuvaus());
                    goToIntent.putExtra("DATE",tehtavaLista.get(position).getPaivamaara());
                    startActivity(goToIntent);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                // lisää tominnallisuus tehtävien tarkasteluun
            }
        });



        kalenteri.setOnDayClickListener(new OnDayClickListener() {

            //toiminnallisuus kalenterin klikkausta varten

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



        //ladataan tehtävälista ajastimelle ja käynnistetään ajastin
        TehtavaAjastin ajastin = new TehtavaAjastin(MainActivity.this);
        ajastin.lataaTehtavat(tehtavaLista);
        ajastin.execute();



    } @Override
    public boolean onCreateOptionsMenu(Menu menu){

        //sivuvalikon luomis metodi

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        // toiminnallisuus sivuvalikolle
        // ihan vitun kesken vielä

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



    @RequiresApi(api = Build.VERSION_CODES.O)
    public void lisaaTehtava(View view) {

        // tällä voi lisätä tehtävän  napista testausta varten

      //  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      //  tAdapter.add(new Tehtava("Juo toinen",LocalDateTime.now().plusMinutes(1).format(formatter),60));



        // Avaa TehtäväOsioPäänäkymän

         Intent intent = new Intent(MainActivity.this,Lisaa_Tehtava.class);
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

        //ajastinluokan rajapinta metodi
        // lukee listalta vanhentuneet tehtävät ja ilmoittaa niistä käyttäjälle

        tulosLista = list;

        if (!list.isEmpty()) {
            for(Tehtava i: tulosLista) {
                indexi = tehtavaLista.indexOf(i);
                tehtavaLista.get(indexi).setVanhentunut(true);


                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //luo dialogin käyttäjää varten
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
                    //päivittää listanäkymään muutokset
                    tAdapter.notifyDataSetChanged();
                    Log.d("uithread", "1");
                }
            });

        }
    }






    @Override
    protected void onPause() {

        //Tallentaa tehtävä shared preferenceihin kun käyttäjä pysäyttää ohjelman

        super.onPause();
        String key = "Key";
        SharedPreferences.Editor editor;
        Gson gson = new Gson();
        String json = gson.toJson(tehtavaLista);
        editor = shref.edit();
        editor.remove(key).commit();
        editor.putString(key, json);
        editor.commit();

    }

    public void lataaTehtavat() {

        // Lataa tehtävät sharedpreferenceistä

        shref = MainActivity.this.getSharedPreferences("tehtavaObject", Context.MODE_PRIVATE);
        tehtavaLista = new ArrayList<>();

        try{
            String key = "Key";
            Gson gson = new Gson();
            String response=shref.getString(key , "");
            ArrayList<Tehtava> lstArrayList = gson.fromJson(response,
                    new TypeToken<List<Tehtava>>(){}.getType());

            if(lstArrayList.isEmpty() == false) {

                for(Tehtava t : lstArrayList) {
                    tehtavaLista.add(t);
                }
            }



        }catch (Exception e){
            e.printStackTrace();
        }

        tehtavaListView = findViewById(R.id.tLista);
        tAdapter = new TehtavaAdapter(this,tehtavaLista);
        tehtavaListView.setAdapter(tAdapter);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void lataaKalenteri() {

        //Lukee tehtävälistalla olevien tehätvien päivämäärät ja lisää ne kalenteriin

        kalenteri = findViewById(R.id.kalenteriNakyma);
        kalenteriLista = new ArrayList<>();

        if(!tehtavaLista.isEmpty()) {

            // luetaan tehtävälistalla olevat tehtävät

            for(Tehtava t : tehtavaLista) {

                Calendar k = Calendar.getInstance();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime paivamaaraTehtava =  LocalDateTime.parse(t.getPaivamaara(),formatter);
                Date date = Date.from(paivamaaraTehtava.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
                k.setTime(date);

                //lisätään merkintä kalenterilistalle
                //merkinnässä on tehtävän päivämäärä, tehtävän nimi, ja pieni kuva joka näkyy kalenterissa
                //se on se vitun lammas edellisestä kurssista
                //eti ite parempi

                kalenteriLista.add(new TehtavaEvent(k,R.drawable.lamb,t.getNimi()));

            }
        }

        // ladataan kalenterilista kalenteriin

        kalenteri.setEvents(kalenteriLista);

    }


}
