package com.example.fitappproject;

public class Potrawa {
    private int id;
    private String nazwa;

    private int kalorie;

    public Potrawa(int id, String nazwa, int kalorie)
    {
        this.id = id;
        this.nazwa = nazwa;
        this.kalorie = kalorie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getKalorie() {
        return kalorie;
    }

    public void setKalorie(int kalorie) {
        this.kalorie = kalorie;
    }
}
