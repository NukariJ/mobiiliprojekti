package com.example.mobiilisovellus;

import java.io.Serializable;

public class Alitehtava implements Serializable {

private String alitehtavannimi;
private String alitehtavankuvaus;
private String alitehtava_ID;
private boolean suoritettu;


public Alitehtava(String an, String ak) {

        this.alitehtavannimi = an;
        this.alitehtavankuvaus = ak;
        this.suoritettu = false;

        }

        public String getAlitehtavannimi() { return alitehtavannimi;}
        public void setAlitehtavannimi(String alitehtavannimi) { this.alitehtavannimi = alitehtavannimi;}

        public String getAlitehtavankuvaus() { return alitehtavankuvaus;}
        public void setAlitehtavankuvaus(String alitehtavankuvaus) { this.alitehtavankuvaus = alitehtavankuvaus;}

        public boolean isSuoritettu() {
                return suoritettu;
        }

        public void setSuoritettu(boolean suoritettu) {
                this.suoritettu = suoritettu;
        }

        public String getAlitehtava_ID() {
                return alitehtava_ID;
        }

        public void setAlitehtava_ID(String alitehtava_ID) {
                this.alitehtava_ID = alitehtava_ID;
        }
}
