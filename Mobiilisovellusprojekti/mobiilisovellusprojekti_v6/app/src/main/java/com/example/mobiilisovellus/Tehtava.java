package com.example.mobiilisovellus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class Tehtava implements Serializable {

    private String nimi;
    private LocalDateTime paivamaara;
    private double suoritettu;
    private ArrayList aliTehtava;
    private Boolean vanhentunut;

    public Tehtava(String n, LocalDateTime d, int p) {
        this.nimi = n;
        this.paivamaara = d;
        this.suoritettu = p;
        this.vanhentunut = false;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public LocalDateTime getPaivamaara() {
        return paivamaara;
    }

    public void setPaivamaara(LocalDateTime paivamaara) {
        this.paivamaara = paivamaara;
    }

    public double getSuoritettu() {
        return suoritettu;
    }

    public void setSuoritettu(double suoritettu) {
        this.suoritettu = suoritettu;
    }

    public ArrayList getAliTehtava() {
        return aliTehtava;
    }

    public void setAliTehtava(ArrayList aliTehtava) {
        this.aliTehtava = aliTehtava;
    }

    public Boolean getVanhentunut() {
        return vanhentunut;
    }

    public void setVanhentunut(Boolean vanhentunut) {
        this.vanhentunut = vanhentunut;
    }
}
