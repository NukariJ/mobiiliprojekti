package com.example.mobiilisovellus;

import java.io.Serializable;

public class Alitehtava implements Serializable {

    private String alitehtavannimi;
    private String alitehtavankuvaus;

    public Alitehtava(String alitehtavannimi, String alitehtavankuvaus) {

        this.alitehtavannimi = alitehtavannimi;
        this.alitehtavankuvaus = alitehtavankuvaus;
    }

    public String getAlitehtavannimi() { return alitehtavannimi;}
    public void setAlitehtavannimi(String name) { this.alitehtavannimi = alitehtavannimi;}

    public String getAlitehtavankuvaus() { return alitehtavankuvaus;}
    public void setAlitehtavankuvaus(String name) { this.alitehtavankuvaus = alitehtavankuvaus;}
}
