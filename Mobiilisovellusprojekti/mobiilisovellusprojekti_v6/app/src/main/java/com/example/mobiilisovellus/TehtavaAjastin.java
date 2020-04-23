package com.example.mobiilisovellus;


import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class TehtavaAjastin extends AsyncTask <String,String,String>{

    //Ajastin luokka tarkastelee tehtävien päivämääriä ja ilmoittaa vanhentuneista tehtävistä päänäkymälle

    private boolean isStarted = false;
    private tehtavaRaportti raportti;
    private ArrayList<Tehtava> tehtavaLista;
    private ArrayList<Tehtava> tulosLista;

    public TehtavaAjastin(tehtavaRaportti t) {
        raportti = t;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected String doInBackground(String... params) {

        tulosLista = new ArrayList<>();

        while(isStarted = true) {
            Log.d("thread cycle","start");
            try{

                if(!tehtavaLista.isEmpty()) {

                    tulosLista = new ArrayList<>();

                    for (Tehtava t : tehtavaLista) {

                        if(t.getVanhentunut() == false) {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                            LocalDateTime paivamaaraNyt = LocalDateTime.now();
                            LocalDateTime paivamaaraTehtava =  LocalDateTime.parse(t.getPaivamaara(),formatter);

                            Duration erotus = Duration.between(paivamaaraNyt, paivamaaraTehtava);
                            if (erotus.isNegative()) {

                                tulosLista.add(t);

                            }
                        }
                    }
                }

                if(!tulosLista.isEmpty()) {
                    raportti.lahetaRaportti(tulosLista);
                }

                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public interface tehtavaRaportti {
        void lahetaRaportti(ArrayList<Tehtava> list);
    }

    public void lataaTehtavat(ArrayList<Tehtava> list) {
        tehtavaLista = list;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)


    public void quit() {
        this.isStarted = false;
    }

    public void setRunning() {
        this.isStarted = true;
    }

    public boolean getStarted() {
        return this.isStarted;
    }
}
