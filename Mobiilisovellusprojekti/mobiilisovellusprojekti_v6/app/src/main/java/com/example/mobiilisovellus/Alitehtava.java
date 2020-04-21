package com.example.mobiilisovellus;

import java.io.Serializable;

public class Alitehtava implements Serializable {

private String alitehtavannimi;
private String alitehtavankuvaus;

public Alitehtava(String an, String ak) {

        this.alitehtavannimi = an;
        this.alitehtavankuvaus = ak;
        }

public String getAlitehtavannimi() { return alitehtavannimi;}
public void setAlitehtavannimi(String alitehtavannimi) { this.alitehtavannimi = alitehtavannimi;}

public String getAlitehtavankuvaus() { return alitehtavankuvaus;}
public void setAlitehtavankuvaus(String alitehtavankuvaus) { this.alitehtavankuvaus = alitehtavankuvaus;}
        }
