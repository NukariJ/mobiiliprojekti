package com.example.mobiilisovellus;

import java.io.Serializable;
import java.util.ArrayList;


public class Tehtava implements Serializable {

    //Tehtäväluokka

    private String nimi;
    private String kuvaus;
    private String paivamaara;
    private double suoritettu;  //paljonko tehtävästä on suoritettu
    private ArrayList<String> aliTehtava; //alitehtävät tallennetaan tänne, toi <String> paikalle laitetaan alitehtävä luokka
    private Boolean vanhentunut;  //onko tehtävä vanhentunut vai ei?

    public Tehtava(String n, String k,String d, int p) {
        this.nimi = n;
        this.kuvaus = k;
        this.paivamaara = d;
        this.suoritettu = p;
        this.vanhentunut = false;
        aliTehtava = new ArrayList<>();
        aliTehtava.add("testi");
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getPaivamaara() {
        return paivamaara;
    }

    public void setPaivamaara(String paivamaara) {
        this.paivamaara = paivamaara;
    }

    public double getSuoritettu() {
        return suoritettu;
    }

    public void setSuoritettu(double suoritettu) {
        this.suoritettu = suoritettu;
    }

    public void setKuvaus(String kuvaus) {this.kuvaus = kuvaus;}                        //Tehtävä kuvauksien getit ja setit
    public String getKuvaus(){return kuvaus;}




    /*public ArrayList getAliTehtava() {
        return aliTehtava;
    }

    public void setAliTehtava(ArrayList aliTehtava) {
       this.aliTehtava = aliTehtava;
    }
    */

    public Boolean getVanhentunut() {
        return vanhentunut;
    }

    public void setVanhentunut(Boolean vanhentunut) {
        this.vanhentunut = vanhentunut;
    }
}
