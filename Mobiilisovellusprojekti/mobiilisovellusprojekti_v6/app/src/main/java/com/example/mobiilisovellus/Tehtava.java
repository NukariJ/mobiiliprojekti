package com.example.mobiilisovellus;

import java.io.Serializable;
import java.util.ArrayList;


public class Tehtava implements Serializable {

    //Tehtäväluokka

    private String nimi;
    private String kuvaus;
    private String paivamaara;  // tehtavan päättymis päivä ja kellonaika
    private String id;          // Tehtavan tunniste tieto
    private double suoritettu;  //paljonko tehtävästä on suoritettu (päätehtävä + alitehtävät prosentteina)
    private boolean paaTehtavaSuoritettu;
    private ArrayList<Alitehtava> aliTehtava; //alitehtävät tallennetaan tänne
    private Boolean vanhentunut;  //onko tehtävä vanhentunut vai ei?

    public Tehtava(String n, String k,String d, int p) {
        this.nimi = n;
        this.kuvaus = k;
        this.paivamaara = d;
        this.suoritettu = p;
        this.vanhentunut = false;
        this.paaTehtavaSuoritettu = false;
        aliTehtava = new ArrayList<>();

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

    public void setKuvaus(String kuvaus) {this.kuvaus = kuvaus;}

    public String getKuvaus(){return kuvaus;}

    public ArrayList<Alitehtava> getAliTehtava() {
        return aliTehtava;
    }

    public void setAliTehtava(ArrayList<Alitehtava> aliTehtava) {
        this.aliTehtava = aliTehtava;
    }

    public Boolean getVanhentunut() {
        return vanhentunut;
    }

    public void setVanhentunut(Boolean vanhentunut) {
        this.vanhentunut = vanhentunut;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isPaaTehtavaSuoritettu() {
        return paaTehtavaSuoritettu;
    }

    public void setPaaTehtavaSuoritettu(boolean paaTehtavaSuoritettu) {
        this.paaTehtavaSuoritettu = paaTehtavaSuoritettu;
    }

    public void laskeSuoritusProsentti() {

        double suorituksia = 0;

        if(aliTehtava.isEmpty() == false) {

           for(Alitehtava t : aliTehtava) {

               if (t.isSuoritettu() == true) {
                    suorituksia++;
               }
           }

            if(paaTehtavaSuoritettu == true) {
                suorituksia++;
            }

            this.suoritettu = suorituksia/(aliTehtava.size());
            double roundOff = Math.round(this.suoritettu*100)/100d;
            this.suoritettu = roundOff*100;

        }else {
            if(paaTehtavaSuoritettu == true) {
                this.suoritettu = 100;
            }
        }
    }

}
