package com.example.castroreyrobert.sharedpreferencesdemo;



public class SmokerModel {
    long id;
    String date;
    String sticks;

    public long getId() {
        return id;
    }

    public SmokerModel(long id, String date, String sticks) {
        this.id = id;
        this.date = date;
        this.sticks = sticks;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SmokerModel(String date, String sticks) {
        this.date = date;
        this.sticks = sticks;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSticks() {
        return sticks;
    }

    public void setSticks(String sticks) {
        this.sticks = sticks;
    }
}
