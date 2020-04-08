package com.example.mobiilisovellus;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

//import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements TehtavaAjastin.tehtavaRaportti {
    private ArrayList<Tehtava> tehtavaLista;
    private ListView tehtavaListView;
    private TehtavaAdapter tAdapter;
    private int palautusKoodi = 2000;
    private int valinta = -1;
    private ArrayList<Tehtava> tulosLista;
    private int indexi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tehtavaLista = new ArrayList<>();
        tehtavaListView = findViewById(R.id.tLista);


        // nappi jolla mennään tehtävän lisäys osioon
        Button btn = (Button)findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TehtavaOsioPaanakyma.class));
            }
        });






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

        TehtavaAjastin ajastin = new TehtavaAjastin(MainActivity.this);
        ajastin.lataaTehtavat(tehtavaLista);
        ajastin.execute();



    }



    public void lisaaTehtava(View view) {

        // Avaa TehtäväOsioPäänäkymän

        Intent intent = new Intent(this,TehtavaOsioPaanakyma.class);
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
