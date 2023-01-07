package com.example.fitappproject;

public class Wybrana_potrawa {
    private int id;
    private int ilosc;

    public Wybrana_potrawa(int id, int ilosc)
    {
        this.id = id;
        this.ilosc = ilosc;
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
}
