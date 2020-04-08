package com.example.mobiilisovellus;


import android.os.AsyncTask;
import android.os.Build;


import androidx.annotation.RequiresApi;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class TehtavaAjastin extends AsyncTask <String,String,String>{

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
        while(isStarted = true) {

            try{

                if(!tehtavaLista.isEmpty()) {

                    tulosLista = new ArrayList<>();

                    for (Tehtava t : tehtavaLista) {

                        if(t.getVanhentunut() == false) {

                            LocalDateTime paivamaaraNyt = LocalDateTime.now();
                            LocalDateTime paivamaaraTehtava = t.getPaivamaara();

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

                sleep(10000);
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