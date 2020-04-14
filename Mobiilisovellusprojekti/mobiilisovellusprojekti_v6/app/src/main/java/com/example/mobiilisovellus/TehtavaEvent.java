package com.example.mobiilisovellus;

import com.applandeo.materialcalendarview.EventDay;

import java.util.Calendar;

public class TehtavaEvent extends EventDay {

    private String tehtavaNote;

    TehtavaEvent(Calendar day, int imageResource, String note) {
        super(day, imageResource);
        tehtavaNote = note;
    }


    public String getTehtavaNote() {
        return tehtavaNote;
    }

    public void setTehtavaNote(String tehtavaNote) {
        this.tehtavaNote = tehtavaNote;
    }
}
