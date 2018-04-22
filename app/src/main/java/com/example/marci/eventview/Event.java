package com.example.marci.eventview;
import java.util.Date;

public class Event {

    public enum TypZadania {
    POJEDYNCZE,
    CYKLICZNE,
    NIESTANDARDOWE
    }

    public enum Priorytet{
    BARDZO_WAZNE,
    WAZNE,
    NORMALNE,
    MALO_WAZNE
    }

    public String nazwa;
    TypZadania typZadania;
    Priorytet priorytet;
    Boolean powiadomienie;
    String data;
    String poczatek_wydarzenia;
    String koniec_wydarzenia;
    Adres adres;
    String notatka;

    public Event (String nazwa, TypZadania typZadania, Priorytet priorytet, String data, String poczatek_wydarzenia, String koniec_wydarzenia, String notatka) {
        this.nazwa=nazwa;
        this.typZadania=typZadania;
        this.priorytet=priorytet;

        this.data=data;
        this.poczatek_wydarzenia=poczatek_wydarzenia;
        this.koniec_wydarzenia=koniec_wydarzenia;
        this.notatka = notatka;
    }

    public Event (String nazwa, TypZadania typZadania, Priorytet priorytet, String data, String poczatek_wydarzenia, String koniec_wydarzenia, Adres adres, String notatka) {
        this.nazwa=nazwa;
        this.typZadania=typZadania;
        this.priorytet=priorytet;

        this.data=data;
        this.adres=adres;
        this.poczatek_wydarzenia=poczatek_wydarzenia;
        this.koniec_wydarzenia=koniec_wydarzenia;
        this.notatka = notatka;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
    public String getNazwa() {
        return nazwa;
    }

    public void setPriorytet(Priorytet priorytet) {
        this.priorytet = priorytet;
    }
    public Priorytet getPriorytet() {
        return priorytet;
    }

    public void setTypZadania(TypZadania typZadania) {
        this.typZadania = typZadania;
    }
    public TypZadania getTypZadania() {
        return typZadania;
    }
}