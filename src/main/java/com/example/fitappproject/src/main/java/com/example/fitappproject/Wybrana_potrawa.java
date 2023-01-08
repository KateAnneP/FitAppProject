package com.example.fitappproject;

public class Wybrana_potrawa {
    private int id;
    private int ilosc;
    private int kalorie;
    private String nazwa;

    public Wybrana_potrawa(int id, int ilosc, int kalorie, String nazwa)
    {
        this.id = id;
        this.ilosc = ilosc;
        this.kalorie = kalorie;
        this.nazwa = nazwa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public int getKalorie() {
        return kalorie;
    }

    public void setKalorie(int kalorie) {
        this.kalorie = kalorie;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
}
